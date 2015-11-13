package net.laurakogler.medminder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var calendarWrapper: CalendarWrapper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MedMinderApplication.graph.inject(this)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val reportDoseButton = findViewById(R.id.report_dose_button) as Button
        reportDoseButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val statusText = findViewById(R.id.status_text) as TextView
                val dateFormat = SimpleDateFormat("h:mm a");
                statusText.setText("Your last dose was at ${dateFormat.format(calendarWrapper.getCalendar().getTime())}")
            }
        })
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
}
