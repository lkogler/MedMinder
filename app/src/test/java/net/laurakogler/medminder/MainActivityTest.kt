package net.laurakogler.medminder

import android.widget.Button
import android.widget.TextView
import com.pawegio.kandroid.find
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest : InjectableTest() {
    lateinit var activity: MainActivity
    val initialTime = 1447458813490L // 11/13/2015 @ 11:53:33.490pm (UTC)
    val fakeCalendar = FakeCalendar(initialTime)

    @Before
    fun setUp() {
        overrideBindings {
            bind<Calendar>() with fakeCalendar
        }

        activity = Robolectric.setupActivity(MainActivity::class.java)
    }

    @Test
    fun itShouldHaveAButtonToReportDose() {
        val reportDoseButton = activity.find<Button>(R.id.report_dose_button)
        assertThat(reportDoseButton.text).isEqualTo("Report Dose")
    }

    @Test
    fun theStatusTextShouldBeUpdated_whenTheButtonIsPressed() {
        val statusText = activity.find<TextView>(R.id.status_text)
        assertThat(statusText.text).isEqualTo("No previously recorded dose.")

        val reportDoseButton = activity.find<Button>(R.id.report_dose_button)
        reportDoseButton.performClick()
        assertThat(statusText.text).isEqualTo("Your last dose was at 3:53 PM.")
    }

    @Test
    fun restarting_remembersTheLastDoseDate() {
        val reportDoseButton = activity.find<Button>(R.id.report_dose_button)
        reportDoseButton.performClick()
        val statusTextOld = activity.find<TextView>(R.id.status_text)

        val restartedActivity = Robolectric.setupActivity(MainActivity::class.java)
        val statusTextNew = restartedActivity.find<TextView>(R.id.status_text)
        assertThat(statusTextOld.text).isEqualTo(statusTextNew.text)
    }

    @Test
    fun itShowsTimeSinceLastDose() {
        val reportDoseButton = activity.find<Button>(R.id.report_dose_button)
        reportDoseButton.performClick()

        val statusText = activity.find<TextView>(R.id.elapsed_time)

        val oneHourThreeMinutes = 63 * 60 * 1000L

        fakeCalendar.advanceTime(oneHourThreeMinutes)
        Robolectric.getForegroundThreadScheduler().advanceToNextPostedRunnable()
        assertThat(statusText.text).isEqualTo("That was 1 hour 3 minutes ago.")

        fakeCalendar.advanceTime(oneHourThreeMinutes)
        Robolectric.getForegroundThreadScheduler().advanceToNextPostedRunnable()
        assertThat(statusText.text).isEqualTo("That was 2 hours 6 minutes ago.")
    }
}
