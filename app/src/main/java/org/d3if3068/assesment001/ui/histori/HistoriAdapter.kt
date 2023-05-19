package org.d3if3068.assesment001.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3068.assesment001.Model.hitungConverter
import org.d3if3068.assesment001.databinding.FragmentHistoriBinding
import org.d3if3068.assesment001.databinding.ItemHistoriBinding
import org.d3if3068.assesment001.db.ConverterEntity

class HistoriAdapter : ListAdapter<ConverterEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ConverterEntity>() {
            override fun areItemsTheSame(
                oldItem: ConverterEntity, newItem: ConverterEntity
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ConverterEntity, newItem: ConverterEntity
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoriAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ConverterEntity) = with(binding) {
            val suhu = item.hitungConverter()
            val fahrenheit = item.fahrenheit
            val celcius = item.celcius

            celciusTxt.text = "input = $celcius"
            fahrenheitTxt.text = "input = $fahrenheit"
        }
    }
}

















//    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ConverterEntity>() {
//        override fun areItemsTheSame(oldItem: ConverterEntity, newItem: ConverterEntity): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: ConverterEntity, newItem: ConverterEntity): Boolean {
//            return oldItem == newItem
//        }
//    }
//    class  (private val binding : ItemHistoriBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind (item: ConverterEntity) = with(binding) {
//            val celcius = item.hitungConverter()
//
//            val dataConverter = ConverterEntity(
//                fahrenheit = (celcius * 9 / 5) + 32
//            )
//
//            celciusTxt.text = dataConverter.celcius.toString()
//            fahrenheitTxt.text = dataConverter.fahrenheit.toString()
//
//        }
//    }
//}