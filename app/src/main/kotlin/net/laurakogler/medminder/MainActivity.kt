package net.laurakogler.medminder

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import com.pawegio.kandroid.find
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        val LAST_DOSE_TIME = "LAST_DOSE_TIME"
    }

    private val injector = KodeinInjector()
    val calendarWrapper: CalendarWrapper by injector.instance()
    val doseRepository: DoseRepository by injector.instance()
    lateinit private var handler: Handler
    lateinit private var updateUiRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(appKodein())
        setContentView(R.layout.activity_main)
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        handler = Handler()
        updateUiRunnable = Runnable {
            showLastDoseTime(doseRepository.getDose())
            handler.postDelayed(updateUiRunnable, 1000)
        }

        val reportDoseButton = find<Button>(R.id.report_dose_button)
        reportDoseButton.setOnClickListener({
            val now = calendarWrapper.getCalendar().time
            doseRepository.setDose(now.time)
            showLastDoseTime(now.time)
        })

        updateUiRunnable.run()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showLastDoseTime(lastDoseTime: Long) {
        if (lastDoseTime > 0) {
            val date = Date(lastDoseTime)
            val now = calendarWrapper.getCalendar().time
            val statusText = find<TextView>(R.id.status_text)
            val elapsedTimeText = find<TextView>(R.id.elapsed_time)
            val dateFormat = SimpleDateFormat("h:mm a");
            statusText.text = "Your last dose was at ${dateFormat.format(date)}."
            val p = PrettyTime(now)
            val durations = p.calculatePreciseDuration(date)
            val timeString = p.format(durations)
            elapsedTimeText.text = "That was $timeString."
        }
    }
}
