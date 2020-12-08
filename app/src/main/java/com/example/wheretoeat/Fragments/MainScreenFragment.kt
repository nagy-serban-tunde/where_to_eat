package com.example.wheretoeat.Fragments

import android.content.Context
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
        this.initRecyclerView(view.context)

//        this.setRecyclerViewScrollListener(recyclerViewRestaurant.layoutManager as LinearLayoutManager)

//        spinner
        spinner = view.findViewById(R.id.SpinnerCountries)
        this.setSpinner(view.context)
        this.setSpinnerListener()

        recyclerViewRestaurant.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        return view
    }

    private fun initRecyclerView(context: Context)
    {
        Log.i("resp", "initbe vagyok")
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(context)
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        adapter.cont=context
        recyclerViewRestaurant.adapter = adapter
        l?.let { adapter.setData(it) }
        textViewCountry.text = SplashActivity.coutryType    }

    override fun onItemClick(position: Int)
    {
        Toast.makeText(context, "Item $position", Toast.LENGTH_SHORT).show()
        var name = l?.get(position)?.name
        var phone = l?.get(position)?.phone
        var city =l?.get(position)?.city
        var img_url = l?.get(position)?.image_url
        var state = l?.get(position)?.state
        var area = l?.get(position)?.area

        val bundle = bundleOf("name" to name, "img_url" to img_url, "phone" to phone, "city" to city, "state" to state, "area" to area)
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
            var list : ArrayList<String> = ArrayList<String>()
            list.add("None")
            list.addAll(l)
            var adap: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
            spinner.adapter = adap
            textViewCountry.text = SplashActivity.coutryType
        }
    }

     private fun setSpinnerListener() {
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
            {
                val textViewItem = view as TextView
                if(textViewItem.text.toString() == "None") { }
                else
                {
                    SplashActivity.restaurantDataMemory.remove("res")
                    SplashActivity.coutryType = textViewItem.text.toString()

                    SplashActivity.num_current_page = 1
                    restaurantViewModel.getRestaurants.observe(viewLifecycleOwner, { reslist -> })
                    l?.get(0)?.name?.let { Log.i("resp", it) }
                    adapter.setData(l!!)
                    textViewCountry.text = SplashActivity.coutryType
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
}





