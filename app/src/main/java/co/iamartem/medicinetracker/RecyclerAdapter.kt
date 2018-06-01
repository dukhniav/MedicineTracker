package co.iamartem.medicinetracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

/**
 * Created by dukhnia on 5/31/18.
 */
class RecyclerAdapter(val medicine: List<Medicine>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    override fun getItemCount() = medicine.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder{
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.recyclerview_item_row, parent, false)

        return RecyclerHolder(cellRow)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val meds = medicine.get(position)

        holder.view.row_name.text = meds.medName

        holder.view.row_remainding.text = meds.medQtyRemainding.toString()

        //todo: onclick listener to go to the individual medication view activity
//        holder.view.full_row_id.setOnClickListener {
//            Log.i("Tag","RecyclerView: Company A -> ${bill.company.toString()}")
//            Log.i("Tag","RecyclerView: ExtraString A -> ${bill.id.toString()}")
//
//
//            //fun onClick (view : View) {
//            val intent = Intent(holder.view.full_row_id.getContext(), UpdateBill::class.java)
//            intent.putExtra("id", bill.id.toString())
//
//            var bun = Bundle()
//            bun.putBoolean("isActive", true)
//            intent.putExtras(bun)
//
//            holder.view.full_row_id.getContext().startActivity(intent)
//            //}
////
////            val intent = Intent(this, UpdateBill::class.java)
////            startActivity(intent)
////            //UpdateBill().loadBill(bill.id.toString())
//
//            Log.i("Tag","RecyclerView: Company B -> ${bill.company.toString()}")
    }

    inner class RecyclerHolder(val view : View) : RecyclerView.ViewHolder(view) {
    }
}