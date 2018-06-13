package co.iamartem.medicinetracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

/**
* Created by Artem Dukhnitskiy on 5/31/18 !
*/
class RecyclerDoctorAdapter(val doc: List<Doctor>) : RecyclerView.Adapter<RecyclerDoctorAdapter.RecyclerHolder>() {

    override fun getItemCount() = doc.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.recyclerview_item_row, parent, false)

        return RecyclerHolder(cellRow)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val doctors = doc.get(position)

        // Name
        holder.view.row_name.text = ("Dr. ${doctors.docName}")

        // Doc phone #
        holder.view.row_remainding.text = ("Phone #: ${doctors.docPhone}")


        // Hospital/ place of business name
        holder.view.row_next.text = (doctors.docBus)


        // Start dialing doctor phone number
        holder.view.row_remainding.setOnClickListener {
            val phoneNo = doctors.docPhone.toString().replace("-", "")
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNo"))
            holder.view.row_remainding.context.startActivity(callIntent)
        }

        // Update medicine item listener
        holder.view.full_row_id.setOnClickListener{
            val intent = Intent(holder.view.full_row_id.context, UpdateDoctorActivity::class.java)

            intent.putExtra("id", doctors.docId)

            val bun = Bundle()
            bun.putBoolean("isActive", true)
            intent.putExtras(bun)

            holder.view.full_row_id.getContext().startActivity(intent)
        }
    }

    inner class RecyclerHolder(val view : View) : RecyclerView.ViewHolder(view) {}
}