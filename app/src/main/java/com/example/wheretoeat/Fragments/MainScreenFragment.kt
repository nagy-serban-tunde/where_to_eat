package com.example.wheretoeat.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Adapters.RestaurantAdapter
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel
import com.example.wheretoeat.R
import com.example.wheretoeat.SplashActivity


class MainScreenFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    private lateinit var recyclerViewRestaurant: RecyclerView
    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var restaurantNestedScroll: NestedScrollView
    var adapter : RestaurantAdapter= RestaurantAdapter(this.context,this)
    var l = SplashActivity.restaurantDataMemory.get("res")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_main_screen, container, false)
        recyclerViewRestaurant = view.findViewById(R.id.restaurantRecycler)
        restaurantNestedScroll = view.findViewById(R.id.scroll_view)
        this.recrecyclerViewRestaurantInit(view)
//        this.setRecyclerViewScrollListener(recyclerViewRestaurant.layoutManager as LinearLayoutManager)
        recyclerViewRestaurant.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        return view
    }
    private fun recrecyclerViewRestaurantInit(view: View)
    {
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(view.context)
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        adapter.cont=context
        recyclerViewRestaurant.adapter = adapter
        adapter.setFirstData()
    }

    override fun onItemClick(position: Int)
    {
        Toast.makeText(context,"Item $position",Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("name" to "alma")
        findNavController().navigate(R.id.deatilFragmentMain,bundle)
    }

    private fun setRecyclerViewScrollListener(linearLayoutManager:LinearLayoutManager) {

        recyclerViewRestaurant.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                Log.i("resp","firstVisibleItemPosition: $firstVisibleItemPosition")
                Log.i("resp","totalItemCount :$totalItemCount")
                Log.i("resp","visibleItemCount :$visibleItemCount")
                if(visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0)
                {
                    var adapter = RestaurantAdapter(context,this@MainScreenFragment)
                    recyclerViewRestaurant.adapter = adapter
                    restaurantViewModel.getRestaurants.observe(viewLifecycleOwner, { resList ->
                            adapter.setData(resList)
                    })
                }

            }
        })
    }

}



