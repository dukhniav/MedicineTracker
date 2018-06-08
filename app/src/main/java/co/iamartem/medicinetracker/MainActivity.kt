package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity(), Serializable {

    // Login
//    val login: CardView = login_button
//    val pin : EditText = login_pin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Login


        //Toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

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


    // MenuAb
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.action_edit_doc -> {
                val intent = Intent(this, ViewDoctorsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_edit_phar -> {
                val intent = Intent(this, ViewPharmaciesActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
