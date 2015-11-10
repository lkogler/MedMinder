package net.laurakogler.medminder

import android.widget.TextView

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

import org.assertj.core.api.Assertions.assertThat

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
class ExampleRobolectricTest {

    @Test
    fun itShouldDisplayHelloWorld() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val textView = activity.findViewById(R.id.main_text) as TextView

        assertThat(textView.text).isEqualTo("Hello World!")
    }
}
