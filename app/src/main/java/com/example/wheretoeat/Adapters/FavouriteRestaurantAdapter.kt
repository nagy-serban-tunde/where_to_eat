package com.example.wheretoeat.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Fragments.ProfileScreenFragment

class FavouriteRestaurantAdapter( private val listener: ProfileScreenFragment) : RecyclerView.Adapter<FavouriteRestaurantAdapter.ViewHolder>() {
    var favouriteRestaurantList: List<FavouriteRestaurantData> = emptyList()
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
        val currentItem = favouriteRestaurantList[position]
        holder.textViewName.text = currentItem.restaurantname
        holder.textViewPhone.text = currentItem.restaurantphone
        Glide.with(holder.imageViewRestaurant).load(currentItem.restaurantpicture).into(holder.imageViewRestaurant)
    }

    override fun getItemCount(): Int {
        return favouriteRestaurantList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textViewName: TextView = view.findViewById(com.example.wheretoeat.R.id.textViewRestaurantName)
        val textViewPhone: TextView = view.findViewById(com.example.wheretoeat.R.id.textViewRestaurantPhone)
        val imageViewRestaurant: ImageView = view.findViewById(com.example.wheretoeat.R.id.imageViewRestaurant)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(view: View) {
//            val data = restaurantList[adapterPosition]
//            listener.onItemClick(data)
        }
    }
    interface OnItemClickListener{
        fun onItemClick(rest : RestaurantData)
    }
    fun setData(l: List<FavouriteRestaurantData>)
    {
        this.favouriteRestaurantList = l
        notifyDataSetChanged()
    }

}