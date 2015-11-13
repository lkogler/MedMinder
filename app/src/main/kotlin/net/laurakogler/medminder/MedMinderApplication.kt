package net.laurakogler.medminder

import android.app.Application
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.KodeinApplication

public class MedMinderApplication : Application(), KodeinApplication {

    override val kodein = Kodein {
        bind<CalendarWrapper>() with instance(CalendarWrapper())
    }


    override fun onCreate() {
        super.onCreate()
    }
}