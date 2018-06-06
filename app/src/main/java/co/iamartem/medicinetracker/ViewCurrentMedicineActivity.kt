package co.iamartem.medicinetracker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Artem Dukhnitskiy on 5/31/18.
 *
 * This fragment will show all CURRENT medicine
 */
class ViewCurrentMedicineActivity : Fragment(){
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.activity_view_current_medicine, container, false)
        recyclerView = view!!.findViewById<View>(R.id.recycler_medicine_view) as RecyclerView

        // Database for recycler view
        val dbHandler = MyDBHandler(activity, null, null, 1)
        val meds : List<Medicine> = dbHandler.getAllCurrentMedicine()

        recyclerView!!.adapter = RecyclerAdapter(meds)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        //emptyText =  as EditText

        if (meds.size > 0) {
            view.findViewById<android.view.View>(co.iamartem.medicinetracker.R.id.med_show_nothing).visibility = View.INVISIBLE
        }


        return view
    }
}