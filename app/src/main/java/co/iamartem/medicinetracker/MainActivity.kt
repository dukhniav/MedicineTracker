package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity(), Serializable {

//    var DOC_ARRAY = ArrayList<Doctor>()
//    var PHARM_ARRAY = ArrayList<Pharmacy>()

    val GET_DOC_REQUEST = 1
    val GET_PHARM_REQUEST = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Starts the initial set-up fields, if not used before.
        // TODO: if user later deletes doctor or pharmacy, need to make sure not to initialize
        //  these methods again

        val dbHandler = MyDBHandler(this, null, null, 1)

        Log.v("Tag", " Main -> about to check if pharmacy and doctor fields are empty")
        if(dbHandler.isPharEmpty())
            Log.v("Tag", " Main -> ENTERING: getPharmacy()")
            getPharmacyObj()
        if(dbHandler.isDocEmpty())
            Log.v("Tag", " Main -> ENTERING: getDoctor()")
            getDoctorObj()

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

    //TODO: check if table doctor and pharmacy is empty
    // Part of initial set up, get doctor information from user
    fun getDoctorObj() {
        val intent = Intent(this, DoctorActivity::class.java)
        Log.v("Tag", " Main -> Starting DoctorActivity")
        startActivity(intent)
    }
    // Part of initial set up, get pharmacy information from user
    fun getPharmacyObj() {
        val intent = Intent(this, PharmacyActivity::class.java)
        Log.v("Tag", " Main -> Starting PharmacyActivity")
        startActivity(intent)//, GET_PHARM_REQUEST)
    }
}
