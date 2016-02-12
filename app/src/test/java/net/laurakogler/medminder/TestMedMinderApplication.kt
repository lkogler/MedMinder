package net.laurakogler.medminder

import com.github.salomonbrys.kodein.Kodein
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

class TestMedMinderApplication : MedMinderApplication(), TestLifecycleApplication {

    fun setKodein(kodein: Kodein) {
        _kodein = kodein
    }

    override fun prepareTest(test: Any?) {
    }

    override fun afterTest(method: Method?) {
    }

    override fun beforeTest(method: Method?) {
    }

    override fun onCreate() {
        super.onCreate()
    }
}