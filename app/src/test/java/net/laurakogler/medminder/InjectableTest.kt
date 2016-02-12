package net.laurakogler.medminder

import com.github.salomonbrys.kodein.Kodein
import org.robolectric.RuntimeEnvironment

open class InjectableTest {

    fun overrideBindings(init: Kodein.Builder.() -> Unit) {
        val application = RuntimeEnvironment.application as TestMedMinderApplication
        val kodein = Kodein {
            extend(application.kodein)
            init()
        }
        application.setKodein(kodein)
    }
}