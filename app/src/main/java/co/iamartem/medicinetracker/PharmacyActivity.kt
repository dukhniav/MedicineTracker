package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_pharmacy.*
import java.io.Serializable

/**
 * Created by dukhnia on 5/30/18.
 */

// Part 1 of 2 of set up for initial set-up of app, will only show up once, can be accessed through
// settings in main activity later.
class PharmacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacy)

        /**
         * if submit button is clicked, pass all fields as Doctor objects back to main,
         * otherwise cancel button will cancel activity
         */
        pharmacy_submit.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("pharmacy", getPharmacy())
            setResult(RESULT_OK, intent)
            finish()
        }

        pharmacy_cancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }

    fun getPharmacy(): Serializable {
        Log.e("Tag", "Pharmacy A -> getPharmacy()")
        val pharmacy = Pharmacy(pharmacy_name.text.toString(), pharmacy_street.text.toString(),
                pharmacy_city.text.toString(), pharmacy_state.text.toString(), pharmacy_phone.text.toString())

        return pharmacy
    }
}