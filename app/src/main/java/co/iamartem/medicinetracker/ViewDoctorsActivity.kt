package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import co.iamartem.medicinetracker.Helper.MyDBHandler
import co.iamartem.medicinetracker.Helper.RecyclerDoctorAdapter
import kotlinx.android.synthetic.main.activity_view_doctor.*

/**
 * Created by dukhnia on 6/6/18 !
 */
class ViewDoctorsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_doctor)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_doctor_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)
        val docs = dbHandler.getAllDoctors()

        val adapter = RecyclerDoctorAdapter(docs)

        recyclerView.adapter = adapter

        view_doc_back.setOnClickListener{
            finish()
        }
        view_doc_add.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            intent.putExtra("classFrom", ViewDoctorsActivity::class.java.toString())
            startActivity(intent)
        }
    }
}