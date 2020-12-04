package com.example.wheretoeat.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Database.Entities.RestaurantDataClass
import com.example.wheretoeat.R

class RestaurantAdapter( ) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    private var restaurantList = emptyList<RestaurantDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowItem: View = LayoutInflater.from(parent.context).inflate(
            R.layout.one_restaurant,
            parent,
            false
        )
        return ViewHolder(rowItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = restaurantList[position]
        holder.textView.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView

        override fun onClick(view: View) {

        }
        init {
            view.setOnClickListener(this)
            textView = view.findViewById(R.id.TextViewRestaurantName)
        }
    }
    fun setData(restaurants: List<RestaurantDataClass>?)
    {
        if (restaurants != null)
        {
            this.restaurantList = restaurants
        }

        notifyDataSetChanged()
    }
}