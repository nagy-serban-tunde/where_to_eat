package com.example.wheretoeat.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.SplashActivity

class RestaurantAdapter() : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    var restaurantList: List<RestaurantData> = emptyList()

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
            textView = view.findViewById(com.example.wheretoeat.R.id.TextViewRestaurantName)

        }
    }
    fun setData(restaList: List<RestaurantData>)
    {
        var l = SplashActivity.restaurantDataMemory.get("res")
        if (l == null)
        {
            SplashActivity.restaurantDataMemory.put("res",restaList)
            this.restaurantList=restaList
        }
        else
        {
            l = l + restaList
            SplashActivity.restaurantDataMemory.put("res",l)
            this.restaurantList = l
        }
        notifyDataSetChanged()
    }
}