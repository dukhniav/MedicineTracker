package co.iamartem.medicinetracker.Helper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.iamartem.medicinetracker.Object.Pharmacy
import co.iamartem.medicinetracker.R
import co.iamartem.medicinetracker.UpdatePharmacyActivity
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

/**
 * Created by Artem Dukhnitskiy on 5/31/18
 */
class RecyclerPharmacyAdapter(val phar: List<Pharmacy>) : RecyclerView.Adapter<RecyclerPharmacyAdapter.RecyclerHolder>() {

    override fun getItemCount() = phar.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.recyclerview_item_row, parent, false)

        return RecyclerHolder(cellRow)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val pharmacy = this.phar.get(position)

        // Name
        holder.view.row_name.text = pharmacy.pharName

        // Pharmacy Phone Number
        holder.view.row_remainding.text = ("Phone #: ${pharmacy.pharPhone}")

        // Start dialing pharmacy phone number
        holder.view.row_remainding.setOnClickListener {
            val phoneNo = pharmacy.pharPhone.replace("-", "")
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNo"))
            holder.view.row_remainding.context.startActivity(callIntent)
        }

        // Update pharmacy item listener
        holder.view.full_row_id.setOnClickListener{
            val intent = Intent(holder.view.full_row_id.context, UpdatePharmacyActivity::class.java)

            intent.putExtra("id", pharmacy.pharId)

            val bun = Bundle()
            bun.putBoolean("isActive", true)

            intent.putExtra("classFrom", RecyclerPharmacyAdapter::class.java.toString())

            intent.putExtras(bun)

            holder.view.full_row_id.context.startActivity(intent)
        }
    }

    inner class RecyclerHolder(val view : View) : RecyclerView.ViewHolder(view)
}