package com.example.wheretoeat.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.RestaurantData

class RestaurantAdapter(context: Context?,
                        private val listener:OnItemClickListener) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    var restaurantList: List<RestaurantData> = emptyList()
    var cont : Context? = context
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
        holder.textViewName.text = currentItem.name
        holder.textViewPhone.text = currentItem.phone
        cont?.let { Glide.with(it).load(currentItem.image_url).into(holder.imageViewRestaurant) }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textViewName: TextView = view.findViewById(com.example.wheretoeat.R.id.textViewRestaurantName)
        val textViewPhone: TextView = view.findViewById(com.example.wheretoeat.R.id.textViewRestaurantPhone)
        val imageViewRestaurant: ImageView = view.findViewById(com.example.wheretoeat.R.id.imageViewRestaurant)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            val data = restaurantList[adapterPosition]
            listener.onItemClick(data)
        }
    }
    interface OnItemClickListener{
        fun onItemClick(rest : RestaurantData)
    }
    fun setData(l: List<RestaurantData>)
    {
        this.restaurantList = l
        notifyDataSetChanged()
    }
}