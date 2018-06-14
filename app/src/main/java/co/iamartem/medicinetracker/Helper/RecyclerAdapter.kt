package co.iamartem.medicinetracker.Helper

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.iamartem.medicinetracker.Object.Medicine
import co.iamartem.medicinetracker.R
import co.iamartem.medicinetracker.UpdatePrescriptionActivity
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*


/**
 * Created by dukhnia on 5/31/18.
 */
class RecyclerAdapter(val medicine: List<Medicine>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    override fun getItemCount() = medicine.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.recyclerview_item_row, parent, false)

        return RecyclerHolder(cellRow)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val meds = medicine.get(position)

        // Name
        holder.view.row_name.text = (meds.medName)
        // QTY Remainding
        holder.view.row_remainding.text = ("QTY: ${meds.medQtyRemaining.toString()}")
        // Reminder
        holder.view.row_next.text = ("Refills: ${meds.medRefillQty.toString()}")

        // Update medicine item listener
        holder.view.full_row_id.setOnClickListener{
            val intent = Intent(holder.view.full_row_id.context, UpdatePrescriptionActivity::class.java)
            intent.putExtra("id", meds.id)

            val bun = Bundle()
            bun.putBoolean("isActive", true)
            intent.putExtras(bun)

            holder.view.full_row_id.getContext().startActivity(intent)
        }
    }

    inner class RecyclerHolder(val view : View) : RecyclerView.ViewHolder(view) {
    }
}