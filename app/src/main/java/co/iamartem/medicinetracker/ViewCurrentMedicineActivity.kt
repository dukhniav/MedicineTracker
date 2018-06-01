package co.iamartem.medicinetracker

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView

/**
 * Created by dukhnia on 5/31/18.
 *
 * This fragment will show all CURRENT medicine
 */
class ViewCurrentMedicineActivity : Fragment(){
    private var recyclerView: RecyclerView? = null

    //todo: get recycler working
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater!!.inflate(R.layout.activity_view_current_medicine, container, false)
//        recyclerView = view!!.findViewById<View>(R.id.recycler_medicine_view) as RecyclerView
//
//        // Database for recycler view
//        val dbHandler = DBHandler(activity, null, null, 1)
//        val bills : List<Medicine> = dbHandler.getAllUnpaidBills()
//
//        recyclerView!!.adapter = RecyclerAdapter(bills)
//        recyclerView!!.layoutManager = LinearLayoutManager(activity)
//
//        //emptyText =  as EditText
//
//        if (bills.size > 0) {
//            view.findViewById<android.view.View>(co.iamartem.billmanager.R.id.bill_view_nothing_show).visibility = View.INVISIBLE
//        }
//
//
//        return view
//    }
}