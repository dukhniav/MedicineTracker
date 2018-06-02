package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_new_prescription.*

/**
 * Created by dukhnia on 6/1/18.
 */

/**
 * Class to add prescriptions to app,
 * will add to DB from here.
 */
class NewPrescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_prescription)

        // doc and pharmacy arrays
        val docArray  = intent.getParcelableArrayListExtra<Doctor>("doctor")
        val pharArray = intent.getParcelableArrayListExtra<Pharmacy>("pharmacy")


        initializeSpinner(docArray, pharArray)

        //val arrayAdapter: ArrayAdapter<Doctor> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, docArray)
//        if(docSpinner != null) {
//            docSpinner!!.onItemSelectedListener(this)
//            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, docArray){
//
//            }
//        }

        // Listener for submit button
        new_submit.setOnClickListener {

        }

        // Listener for cancel button
        new_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun initializeSpinner(docArray: ArrayList<Doctor>, pharArray: ArrayList<Pharmacy>){
        // set up spinners
        val docSpinner = new_med_doctor_spinner
        val pharSpinner = new_med_pharmacy_spinner

        // Doctor spinner
        val arrayAdapterDoc: ArrayAdapter<Doctor> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, docArray)
//        for(i in 0 ..docArray.size){
//
//        }
        //TODO: init Pharmacy spinner
    }

    fun newPrescription() {
        val dbHandler = MyDBHandler(this, null, null, 1)

        //TODO: Do text entry checks here
//        val med = Medicine(new_med_name.text.toString(),
//                new_med_qty.text.toString() as Int,
//                new_med_date_fill.text.toString(),
//                new_med_dosage.text.toString() as Int,
//                new_med_refill_qty.text.toString() as Int,
//                new_med_doctor.dropDownVerticalOffset.,
//                new_med_pharmacy.dropDownVerticalOffset)
    }
}

