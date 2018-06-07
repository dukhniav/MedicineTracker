package co.iamartem.medicinetracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout

/**
 * Created by dukhnia on 6/6/18 !
 */
class ViewDoctorsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_doctor)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_doctor_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)
        val docs = dbHandler.getAllDoctors()
        Log.e("DB: VIEW TEST", "doc [1] id is: ${docs[1].docId}")

        val adapter = RecyclerDoctorAdapter(docs)

        recyclerView.adapter = adapter




//        //val view = inflater!!.inflate(R.layout.activity_view_doctor, container, false)
//        //recyclerView = view!!.findViewById<View>(R.id.recycler_doctor_view) as RecyclerView
//
//        // Database for recycler view
//        val dbHandler = MyDBHandler(this, null, null, 1)
//        val docs : List<Doctor> = dbHandler.getAllDoctors()
//        Log.e("DB: ViewDoctorsActivity", "doc size is ${docs.size}")
//
//        rv = findViewById<RecyclerView>(R.id.recycler_doctor_view) as RecyclerView
//
//        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//
//
//        val adapter = RecyclerDoctorAdapter(docs)
//        rv.adapter = adapter
////        docs.layoutManager = LinearLayoutManager(this)
////        docs.adapter = RecyclerDoctorAdapter(docs)
//
//
//        //emptyText =  as EditText
//
////        if (docs.size > 0) {
////            rv.findViewById<android.view.View>(co.iamartem.medicinetracker.R.id.doc_show_nothing).visibility = View.INVISIBLE
////        }

    }
}