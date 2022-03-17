package ru.headgrass.currency_monitor.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.headgrass.currency_monitor.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var currency: List<Currency> = listOf()
    var listener: OnItemClick? = null

    fun setCurrency(data: List<Currency>) {
        currency = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_currency_rv,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currency[position])
        val currentItem = currency[position]
        holder.nominal.text = currentItem.Nominal.toString()
        holder.name.text = currentItem.Name
        holder.value.text = currentItem.Value.toString()

    }

    override fun getItemCount(): Int {
        return currency.size
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nominal: TextView = itemView.findViewById(R.id.item_currency_nominal)
        val name: TextView = itemView.findViewById(R.id.item_currency_name)
        val value: TextView = itemView.findViewById(R.id.item_currency_value)

        fun bind(currency: Currency) {
            itemView.setOnClickListener {
                listener?.OnClick(currency)
            }
        }
    }

    fun interface OnItemClick {
        fun OnClick(currency: Currency)
    }
}