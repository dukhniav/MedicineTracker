package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.WindowManager
import co.iamartem.medicinetracker.R.string.not_entered
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
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Hide auto keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

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
            // Deciding where to send intent
            val intent: Intent
            val bundle: Bundle = getIntent().extras

            if(bundle.getString("classFrom").equals(ViewDoctorsActivity::class.java.toString()))
                intent = Intent(this, ViewDoctorsActivity::class.java)
            else
                intent = Intent(this, NewPrescriptionActivity::class.java)

            //TODO: chekc if fields are empty
            val doc = getDoc()
            doc.docId = idExtra

            dbHandler.updateDoc(doc)
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

    private fun loadDoctor(id: Int){
        val dbHandler = MyDBHandler(this, null, null, 1)
        val doc: Doctor = dbHandler.getDoctor(id)

        // Set fields in activity
        up_doc_name.setText(    doc.docName)
        up_doc_business.setText(doc.docBus)
        up_doc_street.setText(  doc.docStreet)
        up_doc_city.setText(    doc.docStreet)
        up_doc_state.setText(   doc.docState)
        up_doc_phone.setText(   doc.docPhone)
    }

    private fun getDoc(): Doctor {
        // Error checks
        if(TextUtils.isEmpty(up_doc_name.text.toString()))
            up_doc_name.error = R.string.required.toString()
        if(TextUtils.isEmpty(up_doc_business.text.toString()))
            up_doc_business.setText(not_entered)
        if(TextUtils.isEmpty(up_doc_street.text.toString()))
            up_doc_street.setText(not_entered)
        if(TextUtils.isEmpty(up_doc_city.text.toString()))
            up_doc_city.setText(not_entered)
        if(TextUtils.isEmpty(up_doc_state.text.toString()))
            up_doc_state.setText(not_entered)
        if(TextUtils.isEmpty(up_doc_phone.text.toString()))
            up_doc_phone.setText(not_entered)

        val doctor = Doctor(

                up_doc_name.text.toString(),
                up_doc_business.text.toString(),
                up_doc_street.text.toString(),
                up_doc_city.text.toString(),
                up_doc_state.text.toString(),
                up_doc_phone.text.toString())

        up_doc_name.setText("")
        up_doc_business.setText("")
        up_doc_street.setText("")
        up_doc_city.setText("")
        up_doc_state.setText("")
        up_doc_phone.setText("")

        return doctor
    }
}