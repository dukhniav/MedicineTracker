package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_pharmacy.*

/**
 * Created by dukhnia on 5/30/18.
 */

// Part 2 of 2 of set up for initial set-up of app, will only show up once, can be accessed through
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
            val intent = Intent(this, NewPrescriptionActivity::class.java)

            addPharmacy()
            startActivity(intent)
        }

        pharmacy_cancel.setOnClickListener{
            finish()
        }
    }

    private fun addPharmacy() {
        // Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)

        if(TextUtils.isEmpty(pharmacy_name.text.toString())){
            pharmacy_name.error = "Pharmacy name is required"
        } else {
            if (TextUtils.isEmpty(pharmacy_street.text.toString()))
                pharmacy_street.setText(getString(R.string.not_entered))
            if (TextUtils.isEmpty(pharmacy_city.text.toString()))
                pharmacy_city.setText(R.string.not_entered)
            if (TextUtils.isEmpty(pharmacy_state.text.toString()))
                pharmacy_state.setText(R.string.not_entered)
            if (TextUtils.isEmpty(pharmacy_phone.text.toString()))
                pharmacy_phone.setText(R.string.not_entered)

            val pharmacy = Pharmacy(
                    pharmacy_name.text.toString(),
                    pharmacy_street.text.toString(),
                    pharmacy_city.text.toString(),
                    pharmacy_state.text.toString(),
                    pharmacy_phone.text.toString())

            dbHandler.addPharmacy(pharmacy)

            pharmacy_name.setText("")
            pharmacy_street.setText("")
            pharmacy_city.setText("")
            pharmacy_state.setText("")
            pharmacy_phone.setText("")
        }
    }
}