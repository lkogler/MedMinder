package net.laurakogler.medminder

import com.github.salomonbrys.kodein.Kodein

open class InjectableTest {
    protected var kodein: Kodein? = null

    fun inject(kodeinBindings: Kodein) {
        kodein = kodeinBindings
    }
}