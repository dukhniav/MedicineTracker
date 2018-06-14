package co.iamartem.medicinetracker.Helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import co.iamartem.medicinetracker.ViewCurrentMedicineActivity
import co.iamartem.medicinetracker.ViewPastMedicineActivity

/**
 * Created by dukhnia on 6/1/18.
 */
class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                ViewCurrentMedicineActivity()
            }
            1 -> ViewPastMedicineActivity()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Current"
            else -> {
                return "Past"
            }
        }
    }
}