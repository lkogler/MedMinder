package net.laurakogler.medminder

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.KodeinApplication
import java.util.*

open class MedMinderApplication : Application(), KodeinApplication {

    var createdInstance: MedMinderApplication? = null

    protected var _kodein = Kodein {
        bind<Calendar>() with provider { Calendar.getInstance() }
        bind<DoseRepository>() with singleton { SharedPreferencesDoseRepository(instance()) }
        bind<SharedPreferences>() with singleton { PreferenceManager.getDefaultSharedPreferences(instance("applicationContext")) }
        bind<Context>("applicationContext") with singleton {
            if (createdInstance == null) {
                throw RuntimeException("applicationContext required for injection before Application.onCreate was called")
            } else {
                createdInstance!!
            }
        }
    }

    override val kodein: Kodein
        get() = _kodein

    override fun onCreate() {
        super.onCreate()
        createdInstance = this
    }
}