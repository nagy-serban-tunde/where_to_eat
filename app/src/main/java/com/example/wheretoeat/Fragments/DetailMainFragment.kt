package com.example.wheretoeat.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wheretoeat.MainActivity
import com.example.wheretoeat.R


class DetailMainFragment : Fragment() {

    private lateinit var textViewRestaurantName: TextView
    private lateinit var textViewRestaurantPhone: TextView
    private lateinit var textViewRestaurantCity: TextView
    private lateinit var textViewRestaurantState: TextView
    private lateinit var textViewRestaurantArea: TextView
    private lateinit var imageViewRestaurant: ImageView

    private var mToolbar: Toolbar? = null

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

        textViewRestaurantName.text = arguments?.getString("name")
        textViewRestaurantPhone.text = arguments?.getString("phone")
        textViewRestaurantCity.text = arguments?.getString("city")
        textViewRestaurantState.text = arguments?.getString("state")
        textViewRestaurantArea.text = arguments?.getString("area")

        var img_url =  arguments?.getString("img_url")
        Glide.with(view.context).load(img_url).into(imageViewRestaurant)

        return view
    }
}