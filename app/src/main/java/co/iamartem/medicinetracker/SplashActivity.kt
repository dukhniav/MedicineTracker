package co.iamartem.medicinetracker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle


class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // decide here whether to navigate to Login or Main Activity

        val pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE)

        if (pref.getBoolean("ACTIVITY_EXECUTED", false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

//    override fun appIsLoggedIn(){
//        return pref.getBoolean("activity_executed", false)
//    }

}