package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import co.iamartem.medicinetracker.Helper.MyDBHandler
import co.iamartem.medicinetracker.Object.User
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Artem Dukhnitskiy on 6/13/18 !
 */
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val dbHandler = MyDBHandler(this, null, null, 1)

        reg_b_submit.setOnClickListener {
            if (TextUtils.isEmpty(retPin.text.toString()))
                retPin.setError("Required")
            else if (TextUtils.isEmpty(retPinConfirm.text.toString()))
                retPinConfirm.setError("Required")
            else {

                val pin = Integer.parseInt(retPin.text.toString())
                val pinConfirm = Integer.parseInt(retPinConfirm.text.toString())

                if (pin.equals(pinConfirm)) {
                    val permUser = User(pin)
                    dbHandler.addUser(permUser)

                    val doneIntent = Intent(this, LoginActivity::class.java)
                    startActivity(doneIntent)
                    finish()
                } else {
                    no_match.visibility = View.VISIBLE
                }
            }
        }
        reg_cancel.setOnClickListener {
            val cancelIntent = Intent(this, LoginActivity::class.java)
            startActivity(cancelIntent)
            finish()
        }
    }
}