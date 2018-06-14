package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import co.iamartem.medicinetracker.Helper.MyDBHandler
import co.iamartem.medicinetracker.Helper.RecyclerPharmacyAdapter
import co.iamartem.medicinetracker.Object.Pharmacy
import kotlinx.android.synthetic.main.activity_update_pharmacy.*


/**
 * Created by Artem Dukhnitskiy.
 */

class UpdatePharmacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pharmacy)

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
        loadPharmacy(idExtra)
        /**
         * if submit button is clicked, pass all fields as Doctor objects back to main,
         * otherwise cancel button will cancel activity
         */
        up_phar_submit.setOnClickListener{
            // Deciding where to send intent
            val intent: Intent
            val bundle: Bundle = getIntent().extras

            if(bundle.getString("classFrom").equals(ViewPharmaciesActivity::class.java.toString()) ||
                    bundle.getString("classFrom").equals(RecyclerPharmacyAdapter::class.java.toString()))
                intent = Intent(this, ViewPharmaciesActivity::class.java)
            else
                intent = Intent(this, NewPrescriptionActivity::class.java)

            //TODO: chekc if fields are empty
            val phar = getPharmacy()
            phar.pharId = idExtra

            dbHandler.updatePhar(phar)
            startActivity(intent)
        }

        up_phar_cancel.setOnClickListener{
            finish()
        }

        up_phar_delete.setOnClickListener{
            dbHandler.deletePharmacy(idExtra)
            val intent = Intent(this, ViewPharmaciesActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadPharmacy(id: Int){
        val dbHandler = MyDBHandler(this, null, null, 1)
        Log.e("DB", "pharmacy id is : -> $id")
        val phar: Pharmacy = dbHandler.getPharmacy(id)

        // Set fields in activity
        up_phar_name.setText(    phar.pharName)
        up_phar_street.setText(  phar.pharStreet)
        up_phar_city.setText(    phar.pharStreet)
        up_phar_state.setText(   phar.pharState)
        up_phar_phone.setText(   phar.pharPhone)
    }

    fun getPharmacy(): Pharmacy {
        // Error checks
        if(TextUtils.isEmpty(up_phar_name.text.toString()))
            up_phar_name.error = R.string.required.toString()
        if(TextUtils.isEmpty(up_phar_street.text.toString()))
            up_phar_street.setText(R.string.not_entered)
        if(TextUtils.isEmpty(up_phar_city.text.toString()))
            up_phar_city.setText(R.string.not_entered)
        if(TextUtils.isEmpty(up_phar_state.text.toString()))
            up_phar_state.setText(R.string.not_entered)
        if(TextUtils.isEmpty(up_phar_phone.text.toString()))
            up_phar_phone.setText(R.string.not_entered)

        val pharmacy = Pharmacy(
                up_phar_name.text.toString(),
                up_phar_street.text.toString(),
                up_phar_city.text.toString(),
                up_phar_state.text.toString(),
                up_phar_phone.text.toString())

        up_phar_name.setText("")
        up_phar_street.setText("")
        up_phar_city.setText("")
        up_phar_state.setText("")
        up_phar_phone.setText("")

        return pharmacy
    }
}