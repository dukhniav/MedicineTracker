package co.iamartem.medicinetracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log


class MainActivity : AppCompatActivity() {

    var DOC_ARRAY = ArrayList<Doctor>()
    val GET_DOC_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Tag", "Main A -> onCreate -> BEFORE getDoctorObj() call")
        getDoctorObj()
        Log.e("Tag", "Main A -> onCreate -> AFTER getDoctorObj() call")

    }

    fun getDoctorObj() {
        Log.e("Tag", "Main A -> getDoctorObj")
        if(DOC_ARRAY.size  == 0) {
            Log.e("Tag", "Main A -> getDoctroObj -> DOC_ARRAY.size is 0")

            val intent = Intent(this, DoctorActivity::class.java)
            startActivityForResult(intent, GET_DOC_REQUEST)

            //val doctor: Doctor = intent?.getSerializableExtra("doctor") as Doctor
            //DOC_ARRAY.add(doctor)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Log.e("Tag", "Main A -> onActivityResult")
        if (requestCode == GET_DOC_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                val doctor = data.getSerializableExtra("doctor") as Doctor
                DOC_ARRAY.add(doctor)
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
