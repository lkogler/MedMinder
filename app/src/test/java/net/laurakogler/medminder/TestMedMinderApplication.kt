package net.laurakogler.medminder

import android.app.Application
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.KodeinApplication
import org.mockito.Mockito
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

public class TestMedMinderApplication : Application(), KodeinApplication, TestLifecycleApplication {

    override val kodein = Kodein {
        bind<CalendarWrapper>() with singleton { Mockito.mock(CalendarWrapper::class.java) }
    }

    override fun prepareTest(test: Any?) {
        if(test is InjectableTest) {
            test.inject(kodein)
        }
    }

    override fun afterTest(method: Method?) {
    }

    override fun beforeTest(method: Method?) {
    }

    override fun onCreate() {
        super.onCreate()
    }
}