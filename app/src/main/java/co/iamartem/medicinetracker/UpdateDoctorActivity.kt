package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_update_doctor.*


/**
 * Created by dukhnia on 5/30/18.
 */

// Part 1 of 2 of set up for initial set-up of app, will only show up once, can be accessed through
// settings in main activity later.
class UpdateDoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_doctor)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)

        val bun = intent.extras
        val idExtra : Int

        idExtra = bun.getInt("id")
        loadDoctor(idExtra)
        /**
         * if submit button is clicked, pass all fields as Doctor objects back to main,
         * otherwise cancel button will cancel activity
         */
        up_doc_submit.setOnClickListener{
            val intent = Intent(this, NewPrescriptionActivity::class.java)

            //TODO: chekc if fields are empty
            dbHandler.addDoctor(updateDoctor())
            startActivity(intent)
        }

        up_doc_cancel.setOnClickListener{
            finish()
        }

        up_doc_delete.setOnClickListener{
            dbHandler.deleteDoctor(idExtra)
            val intent = Intent(this, ViewDoctorsActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadDoctor(id: Int){
        val dbHandler = MyDBHandler(this, null, null, 1)
        Log.e("DB", "doctor id is : -> $id")
        val doc: Doctor = dbHandler.getDoctor(id)

        // Set fields in activity
        up_doc_name.setText(    doc.docName)
        up_doc_business.setText(doc.docBus)
        up_doc_street.setText(  doc.docStreet)
        up_doc_city.setText(    doc.docStreet)
        up_doc_state.setText(   doc.docState)
        up_doc_phone.setText(   doc.docPhone)
    }

    fun updateDoctor(): Doctor {
        //TODO: check for right entries, if any main fields null, show REQUIRED
        val doctor = Doctor(
                up_doc_name.text.toString(),
                up_doc_business.text.toString(),
                up_doc_street.text.toString(),
                up_doc_city.text.toString(),
                up_doc_state.text.toString(),
                up_doc_phone.text.toString())

        return doctor
    }
}