package net.laurakogler.medminder

import android.widget.Button
import android.widget.TextView
import com.pawegio.kandroid.find

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest : InjectableTest() {
    lateinit var activity: MainActivity
    lateinit var mockCalendar: Calendar
    val initialTime = 1447458813490L

    @Before
    fun setUp() {
        mockCalendar = mock(Calendar::class.java)
        `when`(mockCalendar.timeInMillis).thenReturn(initialTime)
        var calendarWrapper: CalendarWrapper = kodein!!.instance()
        `when`(calendarWrapper.getCalendar()).thenReturn(mockCalendar)
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

        val timeElapsed = 63 * 60 * 1000L

        `when`(mockCalendar.timeInMillis).thenReturn(initialTime + timeElapsed)
        Robolectric.getForegroundThreadScheduler().advanceBy(timeElapsed)
        assertThat(statusText.text).isEqualTo("That was 1 hour 3 minutes ago.")

        `when`(mockCalendar.timeInMillis).thenReturn(initialTime + 2*timeElapsed)
        Robolectric.getForegroundThreadScheduler().advanceBy(timeElapsed)
        assertThat(statusText.text).isEqualTo("That was 2 hours 6 minutes ago.")
    }
}
