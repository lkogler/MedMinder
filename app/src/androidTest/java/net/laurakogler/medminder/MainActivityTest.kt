package net.laurakogler.medminder

import android.app.Activity
import android.test.ActivityInstrumentationTestCase2
import android.widget.TextView
import junit.framework.Assert

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {
    private var mainActivity: Activity? = null


    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        mainActivity = activity
    }

    fun test_itDisplaysHelloWorld() {
        val textView = mainActivity!!.find<TextView>(R.id.main_text)
        val actual = textView.text.toString()
        Assert.assertEquals("Hello World!", actual)
    }
}
