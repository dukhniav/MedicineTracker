package co.iamartem.medicinetracker

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by dukhnia on 4/30/18.
 */

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
//            0 -> return ViewCurrentMedicine()
//            1 -> return ViewPastMedicine()
            else -> return null
        }
    }
    override fun getCount(): Int {
        return tabCount
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