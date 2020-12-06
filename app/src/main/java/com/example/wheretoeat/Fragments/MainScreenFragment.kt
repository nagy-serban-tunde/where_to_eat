package com.example.wheretoeat.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Adapters.RestaurantAdapter
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel
import com.example.wheretoeat.R
import com.example.wheretoeat.SplashActivity


class MainScreenFragment : Fragment() {

    private lateinit var recyclerViewRestaurant: RecyclerView
    private lateinit var restaurantViewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_main_screen, container, false)
        recyclerViewRestaurant = view.findViewById(R.id.restaurantRecycler)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(view.context)
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        var adapter = RestaurantAdapter()
        recyclerViewRestaurant.adapter = adapter
        restaurantViewModel.getRestaurants.observe(viewLifecycleOwner, { resList ->
            adapter.setData(resList)
        })
        recyclerViewRestaurant.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        return view
    }
}



