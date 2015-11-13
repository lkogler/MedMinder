package net.laurakogler.medminder

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(application: MedMinderApplication)
    fun inject(mainActivity: MainActivity)
}