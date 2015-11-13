package net.laurakogler.medminder

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val application: Application) {
    @Provides
    internal fun provideCalendarWrapper(): CalendarWrapper {
        return CalendarWrapper()
    }
}