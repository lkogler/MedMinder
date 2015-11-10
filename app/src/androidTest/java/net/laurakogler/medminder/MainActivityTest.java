package net.laurakogler.medminder;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    private Activity mainActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
    }

    public void test_itDisplaysHelloWorld() {
        TextView textView = (TextView) mainActivity.findViewById(R.id.main_text);
        final String actual = textView.getText().toString();
        assertEquals("Hello World!", actual);
    }
}
