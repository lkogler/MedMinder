package net.laurakogler.medminder

import com.github.salomonbrys.kodein.Factory
import com.github.salomonbrys.kodein.Kodein
import org.mockito.Mockito
import java.lang.reflect.Type
import java.util.*

class FakeCalendar(time: Long) : Factory<Unit, Calendar> {
    override val argType: Type
        get() = Unit.javaClass

    override fun getInstance(kodein: Kodein, arg: Unit): Calendar {
        return provider(kodein)
    }

    override fun scopeName(): String {
        return toString()
    }

    var now: Long = time

    val provider: Kodein.() -> Calendar = {
        val calendar = Mockito.mock(Calendar::class.java)
        Mockito.`when`(calendar.timeInMillis).thenReturn(now)
        calendar
    }

    fun advanceTime(time: Long) {
        now += time
    }
}

