package co.iamartem.medicinetracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_prescription)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Hide auto keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        val dbHandler = MyDBHandler(this, null, null, 1)

        // doc and pharmacy arrays
        refreshDoc(dbHandler)
        refreshPhar(dbHandler)

        //---------------------------- initialize spinners ---------------------------------------//
        // Doctor spinner
        new_med_doctor_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DOCARRAY)
        new_med_doctor_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
        }

        // Pharmacy spin
        val pharAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PHARARRAY)
        new_med_pharmacy_spinner.adapter = pharAdapter
        new_med_pharmacy_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pharAdapter.notifyDataSetChanged()
            }
        }


        // Listener for submit button
        new_submit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            // Initiate doctor drop-down
            val docSpinnerPos = new_med_doctor_spinner.selectedItemPosition
            val docId = Integer.valueOf(DOCARRAY[docSpinnerPos].docId)
            // Initiate pharmacy drop-down
            val pharSpinnerPos = new_med_pharmacy_spinner.selectedItemPosition
            val pharId = Integer.valueOf(PHARARRAY[pharSpinnerPos].pharId)

            // Get medicine fields
            if(TextUtils.isEmpty(new_med_name.text.toString()))
                new_med_name.error = "Medicine name is required"
            else {
                val med = newPrescription()

                // Query fields to Medicine DB
                dbHandler.addMed(med, intArrayOf(docId), intArrayOf(pharId))

                // Return to Main
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }

        // Listener for cancel button
        new_cancel.setOnClickListener {
            Log.v("Tag", " NewPrescription -> Cancel button clicked")

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        // Listenters for Add new doctor/pharmacy buttons
        new_med_add_doc.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            intent.putExtra("classFrom", NewPrescriptionActivity::class.java.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        new_med_add_phar.setOnClickListener {
            val intent = Intent(this, PharmacyActivity::class.java)
            intent.putExtra("classFrom", NewPrescriptionActivity::class.java.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

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

    // MenuAb
//    public override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
//        when (menuItem.itemId) {
//            android.R.id.home -> {
//                // ProjectsActivity is my 'home' activity
//                startActivityAfterCleanup(MainActivity::class.java)
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(menuItem)
//    }
//
//    private fun startActivityAfterCleanup(cls: Class<*>) {
//        if (MedicineTracker != null) projectsDao.close()
//        val intent = Intent(applicationContext, cls)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//    }
}

