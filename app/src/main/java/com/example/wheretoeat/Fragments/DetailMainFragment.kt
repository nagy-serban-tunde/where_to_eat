package com.example.wheretoeat.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.ViewModels.FavouriteRestaurantViewModel
import com.example.wheretoeat.R


class DetailMainFragment : Fragment() {

    private lateinit var textViewRestaurantName: TextView
    private lateinit var textViewRestaurantPhone: TextView
    private lateinit var textViewRestaurantCity: TextView
    private lateinit var textViewRestaurantState: TextView
    private lateinit var textViewRestaurantArea: TextView
    private lateinit var imageViewRestaurant: ImageView

    private lateinit var buttonFav : CheckBox

    private lateinit var favouriteRestaurantViewModel: FavouriteRestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_detail_main, container, false)
        textViewRestaurantName = view.findViewById(R.id.textViewRestaurantNameDetail)
        textViewRestaurantPhone = view.findViewById(R.id.textViewRestaurantPhoneDetail)
        textViewRestaurantCity = view.findViewById(R.id.textViewRestaurantCityDetail)
        textViewRestaurantState = view.findViewById(R.id.textViewRestaurantStateDetail)
        textViewRestaurantArea = view.findViewById(R.id.textViewRestaurantAreaDetail)
        imageViewRestaurant  = view.findViewById(R.id.imageViewRestaurant)
        buttonFav = view.findViewById(R.id.favbutton)
        favouriteRestaurantViewModel = ViewModelProvider(requireActivity()).get(FavouriteRestaurantViewModel::class.java)

        var name = arguments?.getString("name")
        textViewRestaurantName.text = name
        var phone = arguments?.getString("phone")
        textViewRestaurantPhone.text = phone
        textViewRestaurantCity.text = arguments?.getString("city")
        var city = arguments?.getString("state")
        textViewRestaurantState.text = city
        var area = arguments?.getString("area")
        textViewRestaurantArea.text = area

        var img_url =  arguments?.getString("img_url")
        Glide.with(view.context).load(img_url).into(imageViewRestaurant)

        buttonFav.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                name?.let { FavouriteRestaurantData(0, 1, img_url!!, it, phone!!, city!!, area!!) }?.let { favouriteRestaurantViewModel.insert(it) }
                Toast.makeText(requireContext(), "Favourite!", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireContext(), "Not favourite!", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

}