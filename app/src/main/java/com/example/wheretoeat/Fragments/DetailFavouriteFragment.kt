package com.example.wheretoeat.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.FavouriteRestaurantData
import com.example.wheretoeat.Database.Entities.RestaurantPicturesData
import com.example.wheretoeat.Database.ViewModels.FavouriteRestaurantViewModel
import com.example.wheretoeat.Database.ViewModels.RestaurantPicturesViewModel
import com.example.wheretoeat.FileUtil
import com.example.wheretoeat.R

class DetailFavouriteFragment : Fragment() {

    private lateinit var textViewRestaurantNameFavourite: TextView
    private lateinit var textViewRestaurantPhoneFavourite: TextView
    private lateinit var textViewRestaurantCityFavourite: TextView
    private lateinit var textViewRestaurantStateFavourite: TextView
    private lateinit var textViewRestaurantAreaFavourite: TextView
    private lateinit var imageViewRestaurantFavourite: ImageView

    private lateinit var imageSwitcher: ImageSwitcher

    private lateinit var buttonAddImage : Button
    private lateinit var buttonPrevious : Button
    private lateinit var buttonNext : Button

    private lateinit var buttonFavFavourite : CheckBox

    private var name : String? = null
    private var phone : String? = null
    private var city : String? = null
    private var state : String? = null
    private var area : String? = null
    private var img_url : String? = null

    private var idUser : Int = -1

    private var id_res : Int? = null

    private lateinit var favouriteRestaurantViewModel: FavouriteRestaurantViewModel
    private lateinit var restaurantPicturesViewModel: RestaurantPicturesViewModel

    private lateinit var url : String
    private var positionSwitcher : Int = 0

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
        buttonAddImage = view.findViewById(R.id.ButtonAddImage)
        imageSwitcher = view.findViewById(R.id.imageSwitcherRestaurant)
        imageSwitcher.setFactory { ImageView(view.context) }
        buttonNext = view.findViewById(R.id.ButtonNext)
        buttonPrevious = view.findViewById(R.id.ButtonPrevious)

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
//        Glide.with(view.context).load(img_url).into(imageViewRestaurantFavourite)

        buttonFavFavourite.isChecked=true

        favouriteRestaurantViewModel = ViewModelProvider(requireActivity()).get(FavouriteRestaurantViewModel::class.java)
        restaurantPicturesViewModel = ViewModelProvider(requireActivity()).get(RestaurantPicturesViewModel::class.java)

        restaurantPicturesViewModel.getRestaurantPicturesPhone(phone!!)
        restaurantPicturesViewModel.oneRestaurantPictures?.observe(viewLifecycleOwner){
            var restaurantsPictureList = it.map { it.new_picture_string }
            setImageSwitcher(restaurantsPictureList,view)
        }

        this.setButtonFav()
        setButtonAddImage()

        return view
    }

    private fun setImageSwitcher(restaurantsPictureList : List<String>,view: View){
        if(!restaurantsPictureList.isEmpty())
        {
            imageSwitcher.setImageURI(restaurantsPictureList[0].toUri())
        }
        else
        {
            imageSwitcher.setImageURI(this.img_url?.toUri())
        }

        buttonNext.setOnClickListener{
            if (positionSwitcher < restaurantsPictureList.size-1)
            {
                buttonPrevious.isEnabled = true
                positionSwitcher++
                imageSwitcher.setImageURI(restaurantsPictureList[positionSwitcher].toUri())
            }
            else
            {
                buttonNext.isEnabled = false
                buttonPrevious.isEnabled = true
            }
        }

        buttonPrevious.setOnClickListener{
            if(positionSwitcher > 0 )
            {
                buttonNext.isEnabled = true
                positionSwitcher--
                imageSwitcher.setImageURI(restaurantsPictureList[positionSwitcher].toUri())
            }
            else{
                buttonNext.isEnabled = true
                buttonPrevious.isEnabled = false
            }
        }

    }

    private fun setButtonAddImage()
    {
        buttonAddImage.setOnClickListener{
            startActivityForResult(Intent.createChooser(Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), "Select the image"), 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { imageUrl ->
                this.url = FileUtil.from(requireActivity(), imageUrl).path
                restaurantPicturesViewModel.insert(RestaurantPicturesData(0,phone!!,this.url))
                positionSwitcher = 0
            }
        }
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