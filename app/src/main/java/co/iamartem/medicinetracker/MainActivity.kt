package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity(), Serializable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Starts the initial set-up fields, if not used before.
        // TODO: if user later deletes the LAST doctor or pharmacy in DB, need to make sure not to
        // TODO: initialize these methods again

        val dbHandler = MyDBHandler(this, null, null, 1)

        if(dbHandler.isPharEmpty()) {
            getPharmacyObj()
        }
        if(dbHandler.isDocEmpty()) {
            getDoctorObj()
        }

        //Tabs
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        pager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(pager_main)

        // Add new medicine
        add_new_medicine.setOnClickListener{
            val intent = Intent(this, NewPrescriptionActivity::class.java)
            startActivity(intent)
        }
    }

    // Part of initial set up, get doctor information from user
    private fun getDoctorObj() {
        val intent = Intent(this, DoctorActivity::class.java)
        Log.v("Tag", " Main -> Starting DoctorActivity")
        startActivity(intent)
    }
    // Part of initial set up, get pharmacy information from user
    private fun getPharmacyObj() {
        val intent = Intent(this, PharmacyActivity::class.java)
        Log.v("Tag", " Main -> Starting PharmacyActivity")
        startActivity(intent)
    }
}
