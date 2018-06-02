package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_prescription.*

/**
 * Created by dukhnia on 6/1/18.
 */

/**
 * Class to add prescriptions to app,
 * will add to DB from here.
 */
class NewPrescriptionActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("Tag", " NewPrescription -> Entered activity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_prescription)

        val dbHandler = MyDBHandler(this, null, null, 1)

        // doc and pharmacy arrays
        val docArray  = dbHandler.getDoctors()
        val pharArray = dbHandler.getPharmacies()

        //----------------------------------------------------------------------------------------//
        // initialize spinners
        // Doctor spinner
        new_med_doctor_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, docArray)
        new_med_doctor_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@NewPrescriptionActivity, docArray[position].docName.toString(), Toast.LENGTH_LONG).show()
            }
        }

        // Pharmacy spinner
        new_med_pharmacy_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pharArray)
        new_med_pharmacy_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@NewPrescriptionActivity, pharArray[position].pharName.toString(), Toast.LENGTH_LONG).show()
            }
        }



        // Listener for submit button
        new_submit.setOnClickListener {
        //TODO: What happens when submit is clicked
        }

        // Listener for cancel button
        new_cancel.setOnClickListener {
            Log.v("Tag", " NewPrescription -> Cancel button clicked")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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

