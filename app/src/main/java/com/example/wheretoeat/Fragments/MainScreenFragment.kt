package com.example.wheretoeat.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Adapters.RestaurantAdapter1
import com.example.wheretoeat.Database.Entities.RestaurantData
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel
import com.example.wheretoeat.Database.ViewModels.ViewModelRes
import com.example.wheretoeat.R


class MainScreenFragment : Fragment() {

    private lateinit var recyclerViewRestaurant: RecyclerView
//    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var restaurantViewModel: ViewModelRes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_main_screen, container, false)
//
//        recyclerViewRestaurant = view.findViewById(R.id.restaurantRecycler)
//        recyclerViewRestaurant.layoutManager = LinearLayoutManager(view.context)
//
//        var adapter = RestaurantAdapter()
//        recyclerViewRestaurant.adapter = adapter
//
//        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
//        restaurantViewModel.getRestaurants()
//            ?.observe(viewLifecycleOwner, Observer<List<RestaurantDataClass?>?> { restaurant ->
//                if (restaurant == null) {
//                    adapter.setData(restaurant)
//                }
//            })
//
//        recyclerViewRestaurant.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        recyclerViewRestaurant = view.findViewById(R.id.restaurantRecycler)
        recyclerViewRestaurant.setHasFixedSize(true)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(view.context)

        restaurantViewModel = ViewModelProvider(this).get(ViewModelRes::class.java)
        restaurantViewModel.getRestaurants.observe(viewLifecycleOwner, { resList ->
            var adapter = RestaurantAdapter1(resList)
            recyclerViewRestaurant.adapter = adapter
        })
        recyclerViewRestaurant.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        return view
    }
}


