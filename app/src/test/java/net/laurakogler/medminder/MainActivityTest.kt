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

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest {

    private var activity: MainActivity? = null

    @Before
    fun setUp() {
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
        assertThat(statusText.text).isEqualTo("Your last dose was at 3:07 PM")
    }


}
