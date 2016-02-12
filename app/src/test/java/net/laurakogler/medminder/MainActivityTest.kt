package net.laurakogler.medminder

import android.widget.Button
import android.widget.TextView

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
    private var activity: MainActivity? = null

    @Before
    fun setUp() {
        val mockCalendar = mock(Calendar::class.java)
        `when`(mockCalendar.getTimeInMillis()).thenReturn(1447458813490L)
        var calendarWrapper: CalendarWrapper = kodein!!.instance()
        `when`(calendarWrapper.getCalendar()).thenReturn(mockCalendar)
        activity = Robolectric.setupActivity(MainActivity::class.java)
    }

    @Test
    fun itShouldHaveAButtonToReportDose() {
        val reportDoseButton = activity?.findViewById(R.id.report_dose_button) as Button
        assertThat(reportDoseButton.text).isEqualTo("Report Dose")
    }

    @Test
    fun theStatusTextShouldBeUpdated_whenTheButtonIsPressed() {
        val statusText = activity?.findViewById(R.id.status_text) as TextView
        assertThat(statusText.text).isEqualTo("No previously recorded dose.")

        val reportDoseButton = activity?.findViewById(R.id.report_dose_button) as Button
        reportDoseButton.performClick()
        assertThat(statusText.text).isEqualTo("Your last dose was at 3:53 PM")
    }

    @Test
    fun restarting_remembersTheLastDoseDate() {
        val reportDoseButton = activity?.findViewById(R.id.report_dose_button) as Button
        reportDoseButton.performClick()
        val statusTextOld = activity?.findViewById(R.id.status_text) as TextView

        val restartedActivity = Robolectric.setupActivity(MainActivity::class.java)
        val statusTextNew = restartedActivity?.findViewById(R.id.status_text) as TextView
        assertThat(statusTextOld.text).isEqualTo(statusTextNew.text)
    }
}
