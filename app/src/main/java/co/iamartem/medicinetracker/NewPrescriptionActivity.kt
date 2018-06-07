package co.iamartem.medicinetracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_prescription.*
import java.lang.Integer.parseInt

/**
* Created by Artem Dukhnitskiy on 6/1/18 !
*
* Class to add prescriptions to app,
* will add to DB from here.
*/

class NewPrescriptionActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}

    var DOCARRAY = ArrayList<Doctor>()
    var PHARARRAY = ArrayList<Pharmacy>()


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("Tag", " NewPrescription -> Entered activity")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_prescription)

        val dbHandler = MyDBHandler(this, null, null, 1)


        // doc and pharmacy arrays
        refreshDoc(dbHandler)
        refreshPhar(dbHandler)
        var tempArray = PHARARRAY
        

        //---------------------------- initialize spinners ---------------------------------------//
        // Doctor spinner
        new_med_doctor_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DOCARRAY)
        new_med_doctor_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@NewPrescriptionActivity, DOCARRAY[position].docName, Toast.LENGTH_LONG).show()
            }
        }

        // Pharmacy spin
        val pharAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PHARARRAY)
        new_med_pharmacy_spinner.setAdapter(pharAdapter)
        new_med_pharmacy_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                pharAdapter.notifyDataSetChanged()
            }
        }


        // Listener for submit button
        new_submit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            // Initiate doctor drop-down
            // TODO: Check if DOCARRAY is empty
            val docSpinnerPos = new_med_doctor_spinner.selectedItemPosition
            val docId = Integer.valueOf(DOCARRAY[docSpinnerPos].docId)
            // Initiate pharmacy drop-down
            //TODO: Check if PHARARRAY is empty
            if(PHARARRAY.isEmpty()){

            }


            val pharSpinnerPos = new_med_pharmacy_spinner.selectedItemPosition
            val pharId = Integer.valueOf(PHARARRAY[pharSpinnerPos].pharId)

            // Get medicine fields
            if(TextUtils.isEmpty(new_med_name.text.toString()))
                new_med_name.setError("Medicine name is required")
            else {
                val med = newPrescription()

                // Query fields to Medicine DB
                dbHandler.addMed(med, intArrayOf(docId), intArrayOf(pharId))

                // Return to Main
                startActivity(intent)
            }
        }

        // Listener for cancel button
        new_cancel.setOnClickListener {
            Log.v("Tag", " NewPrescription -> Cancel button clicked")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Listenters for Add new doctor/pharmacy buttons
        new_med_add_doc.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            startActivity(intent)
        }

        new_med_add_phar.setOnClickListener {
            val intent = Intent(this, PharmacyActivity::class.java)
            startActivity(intent)
            Log.e("DB", "after start activity")
        }
    }

//    fun setSpinnerError(spinner: Spinner, msg: String){
//        val selectedView: View = spinner.selectedView
//        if(selectedView != null && selectedView is TextView){
//            spinner.requestFocus()
//            val selectedTextView: TextView = selectedView as TextView
//            selectedTextView.setError(msg)
//            selectedTextView.setTextColor(Color.RED)
//            spinner.performClick()
//        }
//    }
    fun refreshDoc(dbHandler : MyDBHandler){
        DOCARRAY =  dbHandler.getAllDoctors()
    }
    fun refreshPhar(dbHandler: MyDBHandler){
        PHARARRAY = dbHandler.getAllPharmacies()
    }

    @SuppressLint("SetTextI18n")
    private fun newPrescription() : Medicine {
        if (TextUtils.isEmpty(new_med_date_fill.text.toString()))
            new_med_date_fill.setText(getString(R.string.empty_date))
        if (TextUtils.isEmpty(new_med_dosage.text.toString()))
            new_med_dosage.setText("0")
        if (TextUtils.isEmpty(new_med_qty.text.toString()))
            new_med_qty.setText("0")
        if (TextUtils.isEmpty(new_med_refill_qty.text.toString()))
            new_med_refill_qty.setText("0")

        val med = Medicine(
                new_med_name.text.toString(),
                parseInt(new_med_qty.text.toString()),
                new_med_date_fill.text.toString(),
                parseInt(new_med_dosage.text.toString()),
                parseInt(new_med_refill_qty.text.toString()))

        new_med_name.setText("")
        new_med_date_fill.setText("")
        new_med_qty.setText("")
        new_med_dosage.setText("")
        new_med_refill_qty.setText("")

        return med
    }
}

