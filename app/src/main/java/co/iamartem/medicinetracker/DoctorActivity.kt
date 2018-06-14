package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.WindowManager
import co.iamartem.medicinetracker.Helper.MyDBHandler
import co.iamartem.medicinetracker.Object.Doctor
import kotlinx.android.synthetic.main.activity_doctor.*

/**
 * Created by Artem Dukhnitskiy on 5/30/18
 */

// can be accessed through settings in main activity later.
class DoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Hide auto keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        // Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)

        doc_submit.setOnClickListener{
            val intent: Intent
            val bundle: Bundle = getIntent().extras

            if(bundle.getString("classFrom").equals(ViewDoctorsActivity::class.java.toString()))
                intent = Intent(this, ViewDoctorsActivity::class.java)
            else
                intent = Intent(this, NewPrescriptionActivity::class.java)

            // Get/check doctor fields
            if(TextUtils.isEmpty(doc_name.text.toString()))
                doc_name.error = "Doctor name is required"
            else {
                val doc = getDoctor()
                dbHandler.addDoctor(doc)
                startActivity(intent)
            }
        }

        doc_cancel.setOnClickListener{
            finish()
        }
    }

    private fun getDoctor(): Doctor {
        // Error checks
        if(TextUtils.isEmpty(doc_business.text.toString()))
            doc_business.setText(R.string.not_entered)
        if(TextUtils.isEmpty(doc_street.text.toString()))
            doc_street.setText(R.string.not_entered)
        if(TextUtils.isEmpty(doc_city.text.toString()))
            doc_city.setText(R.string.not_entered)
        if(TextUtils.isEmpty(doc_state.text.toString()))
            doc_state.setText(R.string.not_entered)
        if(TextUtils.isEmpty(doc_phone.text.toString()))
            doc_phone.setText(R.string.not_entered)

        val doctor = Doctor(
                doc_name.text.toString(),
                doc_business.text.toString(),
                doc_street.text.toString(),
                doc_city.text.toString(),
                doc_state.text.toString(),
                doc_phone.text.toString())

        doc_name.setText("")
        doc_business.setText("")
        doc_street.setText("")
        doc_city.setText("")
        doc_state.setText("")
        doc_phone.setText("")

        return doctor
    }
}