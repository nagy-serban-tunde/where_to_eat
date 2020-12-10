package com.example.wheretoeat.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Entities.ProfileData
import com.example.wheretoeat.Database.ViewModels.FavouriteRestaurantViewModel
import com.example.wheretoeat.Database.ViewModels.ProfileViewModel
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
    private lateinit var profileViewModel: ProfileViewModel

    lateinit var profiles : List<ProfileData>
    var id_res : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(context, "YES", Toast.LENGTH_SHORT).show()
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
        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        profileViewModel.onLoadingFinished={
            profiles = profileViewModel.allProfile!!
        }

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

        favouriteRestaurantViewModel.getIdFavouriteRestaurant(phone!!)
        var id = favouriteRestaurantViewModel.id
        id.observe(viewLifecycleOwner) {
            Log.i("resp","${it}")
            id_res = it
        }

        buttonFav.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                try{
                    name?.let { FavouriteRestaurantData(0, 1, img_url!!, it, phone!!, city!!, area!!) }?.let { favouriteRestaurantViewModel.insert(it) }
                }catch (value :Exception){
                    Toast.makeText(requireContext(), "Not user!", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(requireContext(), "Favourite!", Toast.LENGTH_LONG).show()
            }
            else{
                id_res?.let {
                    favouriteRestaurantViewModel.deleteFavouriteRestaurant(FavouriteRestaurantData(it,1,img_url!!,name!!,phone!!,city!!,area!!))
                }
//                favouriteRestaurantViewModel.deleteAll()
                Toast.makeText(requireContext(), "Not favourite!", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}