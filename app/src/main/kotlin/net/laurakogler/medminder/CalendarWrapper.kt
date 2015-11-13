package net.laurakogler.medminder

import java.util.*

open class CalendarWrapper {
    open fun getCalendar(): Calendar {
        return Calendar.getInstance()
    }
}