package org.d3if3068.assesment001

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3068.assesment001.Model.Termometer

class TermometerAdapter : RecyclerView.Adapter<TermometerAdapter.TermometerViewHolder>() {
    private val termometerList: MutableList<Termometer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermometerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listrumus, parent, false)
        return TermometerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TermometerViewHolder, position: Int) {
        val termometer = termometerList[position]
        holder.bind(termometer)
    }

    override fun getItemCount(): Int {
        return termometerList.size
    }

    fun setData(data: List<Termometer>) {
        termometerList.clear()
        termometerList.addAll(data)
        notifyDataSetChanged()
    }

    inner class TermometerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val judulTextView: TextView = itemView.findViewById(R.id.judul)
        private val rumusTextView: TextView = itemView.findViewById(R.id.rumus)
        private val gambarImageView: ImageView = itemView.findViewById(R.id.gambar)

        fun bind(termometer: Termometer) {
            judulTextView.text = termometer.judul
            rumusTextView.text = termometer.rumus
            Glide.with(itemView.context).load(termometer.gambar).error(R.drawable.baseline_broken_image_24).into(gambarImageView)
        }
    }
}
