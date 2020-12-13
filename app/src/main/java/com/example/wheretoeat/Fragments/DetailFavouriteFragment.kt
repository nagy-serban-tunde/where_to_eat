package com.example.wheretoeat.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.ViewModels.FavouriteRestaurantViewModel
import com.example.wheretoeat.R

class DetailFavouriteFragment : Fragment() {

    private lateinit var textViewRestaurantNameFavourite: TextView
    private lateinit var textViewRestaurantPhoneFavourite: TextView
    private lateinit var textViewRestaurantCityFavourite: TextView
    private lateinit var textViewRestaurantStateFavourite: TextView
    private lateinit var textViewRestaurantAreaFavourite: TextView
    private lateinit var imageViewRestaurantFavourite: ImageView

    private lateinit var buttonFavFavourite : CheckBox

    var name : String? = null
    var phone : String? = null
    var city : String? = null
    var state : String? = null
    var area : String? = null
    var img_url : String? = null

    var idUser : Int = -1

    var id_res : Int? = null

    private lateinit var favouriteRestaurantViewModel: FavouriteRestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_detail_favourite, container, false)
        textViewRestaurantNameFavourite = view.findViewById(R.id.textViewRestaurantNameFavourite)
        textViewRestaurantPhoneFavourite = view.findViewById(R.id.textViewRestaurantPhoneFavourite)
        textViewRestaurantCityFavourite = view.findViewById(R.id.textViewRestaurantCityFavourite)
        textViewRestaurantStateFavourite = view.findViewById(R.id.textViewRestaurantStateFavourite)
        textViewRestaurantAreaFavourite = view.findViewById(R.id.textViewRestaurantAreaFavourite)
        imageViewRestaurantFavourite  = view.findViewById(R.id.imageViewRestaurantFavourite)
        buttonFavFavourite = view.findViewById(R.id.favbuttonFavourite)

        idUser = arguments?.getString("id")?.toInt()!!
        name = arguments?.getString("name")
        textViewRestaurantNameFavourite.text = name
        phone = arguments?.getString("phone")
        textViewRestaurantPhoneFavourite.text = phone
        city = arguments?.getString("city")
        textViewRestaurantCityFavourite.text = city
        state = arguments?.getString("state")
        textViewRestaurantStateFavourite.text = state
        area = arguments?.getString("area")
        textViewRestaurantAreaFavourite.text = area

        img_url =  arguments?.getString("img_url")
        Glide.with(view.context).load(img_url).into(imageViewRestaurantFavourite)

        buttonFavFavourite.isChecked=true

        favouriteRestaurantViewModel = ViewModelProvider(requireActivity()).get(
                FavouriteRestaurantViewModel::class.java
        )

        this.setButtonFav()

        return view
    }

    private fun setButtonFav()
    {
        favouriteRestaurantViewModel.getIdFavouriteRestaurant(phone!!)
        var idFavorRes = favouriteRestaurantViewModel.id
        idFavorRes.observe(viewLifecycleOwner) {
            id_res = it
        }
        buttonFavFavourite.setOnCheckedChangeListener { _, isChecked ->
                id_res?.let { favouriteRestaurantViewModel.deleteFavouriteRestaurant(
                        FavouriteRestaurantData(
                                it, idUser, img_url!!, name!!, phone!!, city!!, area!!, state!!
                        )
                ) }
                Toast.makeText(requireContext(), "Not favourite!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.profileScreenFragment)
        }
    }

}