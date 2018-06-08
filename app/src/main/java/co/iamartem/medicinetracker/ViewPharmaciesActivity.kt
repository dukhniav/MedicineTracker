package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_view_pharmacy.*

/**
 * Created by dukhnia on 6/6/18 !
 */
class ViewPharmaciesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pharmacy)

        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_pharmacy_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //Database for recycler view
        val dbHandler = MyDBHandler(this, null, null, 1)
        val phars = dbHandler.getAllPharmacies()

        val adapter = RecyclerPharmacyAdapter(phars)

        recyclerView.adapter = adapter

        view_phar_back.setOnClickListener{
            finish()
        }
        view_phar_add.setOnClickListener {
            val intent = Intent(this, PharmacyActivity::class.java)
            intent.putExtra("classFrom", ViewPharmaciesActivity::class.java.toString())
            startActivity(intent)
        }
    }
}