package net.laurakogler.medminder;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleRobolectricTest {

    @Test
    public void itShouldDisplayHelloWorld() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        TextView textView = (TextView) activity.findViewById(R.id.main_text);

        assertThat(textView.getText()).isEqualTo("Hello World!");
    }
}
