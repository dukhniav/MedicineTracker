package co.iamartem.medicinetracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import co.iamartem.medicinetracker.Helper.MyDBHandler
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by dukhnia on 6/6/18 !
 */
class LoginActivity : AppCompatActivity() {

    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val dbHandler = MyDBHandler(this, null, null, 1)

        login_button.setOnClickListener {
            if (TextUtils.isEmpty(login_pin.text.toString()))
                retPin.error = "Required"
            else {
                val verifiedPin = Integer.parseInt(login_pin.text.toString())

                val dbPin: Boolean = dbHandler.checkUser(verifiedPin)
                if (dbPin) {
                    val validIntent = Intent(this, MainActivity::class.java)

                    startActivity(validIntent)

                } else
                    wrong_pin.visibility = View.VISIBLE
            }
        }

        login_setup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }


    }
}

