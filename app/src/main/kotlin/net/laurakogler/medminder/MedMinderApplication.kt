package net.laurakogler.medminder

import android.app.Application

public class MedMinderApplication : Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit public var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        graph.inject(this)
    }
}