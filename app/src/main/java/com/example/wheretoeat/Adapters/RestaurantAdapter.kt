package com.example.wheretoeat.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Database.Entities.RestaurantData

class RestaurantAdapter(restaList: List<RestaurantData>) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    var restList: List<RestaurantData> = restaList

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowItem: View = LayoutInflater.from(parent.context).inflate(
                com.example.wheretoeat.R.layout.one_restaurant,
                parent,
                false
        )
        return ViewHolder(rowItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = restList[position]
        holder.textView.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return restList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView

        override fun onClick(view: View) {
        }
        init {
            textView = view.findViewById(com.example.wheretoeat.R.id.TextViewRestaurantName)
        }
    }
}