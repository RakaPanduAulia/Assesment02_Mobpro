package org.d3if3068.assesment001

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.d3if3068.assesment001.CustomAdapter.MyViewHolder

class CustomAdapter internal constructor(
    private val context: Context,
    private val history_id: ArrayList<*>,
    private val history_celcius: ArrayList<*>,
    private val history_fahrenheit: ArrayList<*>
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.history_id_txt.text = history_id[position].toString()
        holder.history_celcius_txt.text = history_celcius[position].toString()
        holder.history_fahrenheit_txt.text = history_fahrenheit[position].toString()
    }

    override fun getItemCount(): Int {
        return history_id.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var history_id_txt: TextView
        var history_celcius_txt: TextView
        var history_fahrenheit_txt: TextView

        init {
            history_id_txt = itemView.findViewById(R.id.history_id_txt)
            history_celcius_txt = itemView.findViewById(R.id.history_celcius_txt)
            history_fahrenheit_txt = itemView.findViewById(R.id.history_fahrenheit_txt)
        }
    }
}