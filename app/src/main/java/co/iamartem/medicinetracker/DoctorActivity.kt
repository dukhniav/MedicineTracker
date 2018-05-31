package co.iamartem.medicinetracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_doctor.*
import java.io.Serializable

/**
 * Created by dukhnia on 5/30/18.
 */

// Part 1 of 2 of set up for initial set-up of app, will only show up once, can be accessed through
// settings in main activity later.
class DoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        /**
         * if submit button is clicked, pass all fields as Doctor objects back to main,
         * otherwise cancel button will cancel activity
         */
        doc_submit.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("doctor", getDoctor())
            setResult(RESULT_OK, intent)
            finish()
        }

        doc_cancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    fun getDoctor(): Serializable {
        Log.e("Tag", "Doctor A -> getDoctor()")
        val doctor = Doctor(doc_name.toString(), doc_street.toString(),
                doc_city.toString(), doc_state.toString(), doc_phone.toString())

        return doctor
    }
}