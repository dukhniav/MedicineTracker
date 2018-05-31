package co.iamartem.medicinetracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity(), Serializable {

    var DOC_ARRAY = ArrayList<Doctor>()
    var PHARM_ARRAY = ArrayList<Pharmacy>()

    val GET_DOC_REQUEST = 1
    val GET_PHARM_REQUEST = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Starts the initial set-up fields, if not used before.
        // TODO: if user later deletes doctor or pharmacy, need to make sure not to initialize
        //  these methods again
        getPharmacyObj()
        getDoctorObj()

    }

    // Part of initial set up, get doctor information from user
    fun getDoctorObj() {
        if(DOC_ARRAY.size  == 0) {
            val intent = Intent(this, DoctorActivity::class.java)
            startActivityForResult(intent, GET_DOC_REQUEST)
        }
    }
    // Part of initial set up, get pharmacy information from user
    fun getPharmacyObj() {
        if(PHARM_ARRAY.size == 0) {
            val intent = Intent(this, PharmacyActivity::class.java)
            startActivityForResult(intent, GET_PHARM_REQUEST)
        }
    }

    // Checks what intent came from what activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        // DOCTOR
        if (requestCode == GET_DOC_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                val doctor = data.getSerializableExtra("doctor") as Doctor
                DOC_ARRAY.add(doctor)
                //TODO: Run a DOC_ARRAY.size, to check if more than one entry exists for later purposes
                main_doctor.setText(DOC_ARRAY.get(0).docName)
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //TODO: if canceled, give user option to opt out of choosing doctor
                //Write your code if there's no result
            }
        // PHARMACY
        }else if (requestCode == GET_PHARM_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                val pharmacy = data.getSerializableExtra("pharmacy") as Pharmacy
                PHARM_ARRAY.add(pharmacy)
                main_pharmacy.setText(PHARM_ARRAY.get(0).pharName)
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //TODO: if canceled, give user option to opt out of choosing pharmacy
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
