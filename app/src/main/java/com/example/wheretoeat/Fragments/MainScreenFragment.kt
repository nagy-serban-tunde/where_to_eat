package com.example.wheretoeat.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.Adapters.RestaurantAdapter
import com.example.wheretoeat.Database.ViewModels.RestaurantViewModel
import com.example.wheretoeat.MainActivity
import com.example.wheretoeat.R
import com.example.wheretoeat.SplashActivity


class MainScreenFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    private lateinit var recyclerViewRestaurant: RecyclerView
    private lateinit var restaurantViewModel: RestaurantViewModel
    var adapter : RestaurantAdapter= RestaurantAdapter(this.context, this)
    var l = SplashActivity.restaurantDataMemory.get("res")

    private lateinit var  spinner: Spinner
    private lateinit var textViewCountry : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_main_screen, container, false)
//        init element
        recyclerViewRestaurant = view.findViewById(R.id.restaurantRecycler)
        textViewCountry = view.findViewById(R.id.textviewCountry)

//        recyclerView and adapter
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(view.context)
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        adapter.cont=context
        recyclerViewRestaurant.adapter = adapter
        l?.let { adapter.setData(it) }
        textViewCountry.text = SplashActivity.coutryType

//        this.setRecyclerViewScrollListener(recyclerViewRestaurant.layoutManager as LinearLayoutManager)

//        spinner
        spinner = view.findViewById(R.id.SpinnerCountries)
        context?.let { this.setSpinner(it) }

        recyclerViewRestaurant.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        return view
    }

    override fun onItemClick(position: Int)
    {
        Toast.makeText(context, "Item $position", Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("name" to "alma")
        findNavController().navigate(R.id.deatilFragmentMain, bundle)
    }

    private fun setRecyclerViewScrollListener(linearLayoutManager: LinearLayoutManager) {

        recyclerViewRestaurant.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                Log.i("resp", "firstVisibleItemPosition: $firstVisibleItemPosition")
                Log.i("resp", "totalItemCount :$totalItemCount")
                Log.i("resp", "visibleItemCount :$visibleItemCount")
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    restaurantViewModel.getRestaurants.observe(viewLifecycleOwner, { })
                }

            }
        })
    }

    private fun setSpinner(context: Context) {
        var l = SplashActivity.countriesDataMemory.get("countries")
        if (l != null) {
            var adap: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_spinner_item, l)
            spinner.adapter = adap
        }

        val onItemSelectedListener: OnItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(aAdapter: AdapterView<*>?, aView: View, arg2: Int, arg3: Long) {
                val textViewItem = aView as TextView
                textViewCountry.text = textViewItem.text.toString()
                SplashActivity.coutryType = textViewItem.text.toString()
                Log.i("resp",SplashActivity.coutryType)
                SplashActivity.restaurantDataMemory.remove("res")

                SplashActivity.num_current_page = 1
                restaurantViewModel.getRestaurants.observe(viewLifecycleOwner, { reslist-> })
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
        spinner.onItemSelectedListener = onItemSelectedListener

        var lspin = SplashActivity.restaurantDataMemory.get("res")
        if (lspin == null)
        {
            Log.i("resp","nincs lista")
//          Log.i("resp",l[0].name)
//           adapter.setData(l)
        }
    }

}




