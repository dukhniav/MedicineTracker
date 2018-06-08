package co.iamartem.medicinetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

/**
 * Created by dukhnia on 5/31/18.
 */
class RecyclerPharmacyAdapter(val phar: List<Pharmacy>) : RecyclerView.Adapter<RecyclerPharmacyAdapter.RecyclerHolder>() {

    override fun getItemCount() = phar.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.recyclerview_item_row, parent, false)

        return RecyclerHolder(cellRow)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val pharmacy = phar.get(position)
        Log.e("DB: PHAR RECYCLER", " PHARMACY size is ${phar.size}")

        // Name
        holder.view.row_name.text = (pharmacy.pharName)

        // QTY Remainding
        holder.view.row_remainding.text = ("Phone #: ${pharmacy.pharPhone}")
        // Reminder TODO: Change
        holder.view.row_next.text = ("ID: ${pharmacy.pharId}")


        // Update pharmacy item listener
        holder.view.full_row_id.setOnClickListener{
            val intent = Intent(holder.view.full_row_id.context, UpdatePharmacyActivity::class.java)

            Log.e("DB: pharmacy RECYCLER", " IN ON CLICK LISTENER pharmacy size is ${phar.size}")

            Log.e("DB: Recyclepharmacy", "pharmacy ID is : ${pharmacy.pharId}")
            intent.putExtra("id", pharmacy.pharId)

            val bun = Bundle()
            bun.putBoolean("isActive", true)

            intent.putExtra("classFrom", RecyclerPharmacyAdapter::class.java.toString())

            intent.putExtras(bun)

            holder.view.full_row_id.getContext().startActivity(intent)
        }
    }

    inner class RecyclerHolder(val view : View) : RecyclerView.ViewHolder(view) {
    }
}