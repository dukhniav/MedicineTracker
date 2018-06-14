package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import co.iamartem.medicinetracker.Helper.MyDBHandler
import co.iamartem.medicinetracker.Object.Medicine
import kotlinx.android.synthetic.main.activity_update_prescription.*

/**
 * Created by Artem Dukhnitskiy on 6/5/18 !
 *
 * Update current prescription
 */
class UpdatePrescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_prescription)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        // Hide auto keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        val dbHandler = MyDBHandler(this, null, null, 1)

        // doc and pharmacy arrays
        val docArray  = dbHandler.getAllDoctors()
        val pharArray = dbHandler.getAllPharmacies()

        //---------------------------- initialize spinners ---------------------------------------//
        // Doctor spinner
        up_med_doctor_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, docArray)
        up_med_doctor_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
        }

        // Pharmacy spinner
        up_med_pharmacy_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pharArray)
        up_med_pharmacy_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
        }

        val bun = intent.extras
        val idExtra : Int

        idExtra = bun.getInt("id")
        loadMedicine(idExtra)

        // Submit button
        up_submit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            // Initiate doctor drop-down
            // TODO: Check if docArray is empty
            val docSpinnerPos = up_med_doctor_spinner.selectedItemPosition
            val docId = Integer.valueOf(docArray[docSpinnerPos].docId)
            // Initiate pharmacy drop-down
            //TODO: Check if pharArray is empty
            val pharSpinnerPos = up_med_pharmacy_spinner.selectedItemPosition
            val pharId = Integer.valueOf(pharArray[pharSpinnerPos].pharId)

            // Check for required fields
            if (TextUtils.isEmpty(up_med_name.text.toString()))
                up_med_name.setError("Medicine name is required")
            else {
                val med = updatePrescription()
                med.id = idExtra  // set ID of medicine

                // Query fields to Medicine DB
                dbHandler.updateMed(med)

                startActivity(intent)

            }
        }

        // Cancel and go back to Main
        up_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Delete button and go back to Main
        up_delete.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            // Query delete medicine ID
            dbHandler.deleteMedicine(idExtra)
            startActivity(intent)
        }

    }

    // Load medicine fields
    private fun loadMedicine(id: Int) {
        val dbHandler = MyDBHandler(this, null, null, 1)
        val med: Medicine? = dbHandler.getMedicine(id)
        
        // Set fields in activity
        up_med_name.setText(med?.medName)
        up_med_date_fill.setText(med?.medDateFilled)
        up_med_dosage.setText(med?.medDosage.toString())
        up_med_qty.setText(med?.medQtyRemaining.toString())
        up_med_refill_qty.setText(med?.medRefillQty.toString())
    }
    
    private fun updatePrescription() : Medicine {
        if (TextUtils.isEmpty(up_med_date_fill.text.toString()))
            up_med_date_fill.setText(R.string.empty_date)
        if (TextUtils.isEmpty(up_med_dosage.text.toString()))
            up_med_dosage.setText("0")
        if (TextUtils.isEmpty(up_med_qty.text.toString()))
            up_med_qty.setText("0")
        if (TextUtils.isEmpty(up_med_refill_qty.text.toString()))
            up_med_refill_qty.setText("0")

        val med = Medicine(
                up_med_name.text.toString(),
                Integer.parseInt(up_med_qty.text.toString()),
                up_med_date_fill.text.toString(),
                Integer.parseInt(up_med_dosage.text.toString()),
                Integer.parseInt(up_med_refill_qty.text.toString()))

        up_med_name.setText("")
        up_med_date_fill.setText("")
        up_med_qty.setText("")
        up_med_dosage.setText("")
        up_med_refill_qty.setText("")

        return med
    }
}