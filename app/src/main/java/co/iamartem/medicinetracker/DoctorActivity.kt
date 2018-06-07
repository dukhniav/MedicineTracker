package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_doctor.*

/**
 * Created by dukhnia on 5/30/18.
 */

// Part 1 of 2 of set up for initial set-up of app, will only show up once, can be accessed through
// settings in main activity later.
class DoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)

        doc_submit.setOnClickListener{
            val intent = Intent(this, NewPrescriptionActivity::class.java)

            //TODO: chekc if fields are empty
            val doc = getDoctor()
            val docId = dbHandler.addDoctor(doc)
            startActivity(intent)
        }

        doc_cancel.setOnClickListener{
            finish()
        }
    }

    fun getDoctor(): Doctor {
        //TODO: check for right entries, if any main fields null, show REQUIRED
        val doctor = Doctor(
                doc_name.text.toString(),
                doc_business.text.toString(),
                doc_street.text.toString(),
                doc_city.text.toString(),
                doc_state.text.toString(),
                doc_phone.text.toString())

        return doctor
    }
}