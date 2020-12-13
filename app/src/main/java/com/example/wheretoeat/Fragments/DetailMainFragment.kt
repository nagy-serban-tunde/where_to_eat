package com.example.wheretoeat.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter
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
    private var id_res : Int? = null
    private var id_user : Int = -1

    private var name : String? = null
    private var phone : String? = null
    private var city : String? = null
    private var state : String? = null
    private var area : String? = null
    private var img_url : String? = null

    private var favouriteRestaurantList: List<FavouriteRestaurantData> = emptyList()

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
        favouriteRestaurantViewModel = ViewModelProvider(requireActivity()).get(
                FavouriteRestaurantViewModel::class.java
        )
        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        profileViewModel.onLoadingFinished={
            profiles = profileViewModel.allProfile!!
        }
        profileViewModel.getProfiles()

        name = arguments?.getString("name")
        textViewRestaurantName.text = name
        phone = arguments?.getString("phone")
        textViewRestaurantPhone.text = phone
        city = arguments?.getString("city")
        textViewRestaurantCity.text = city
        state = arguments?.getString("state")
        textViewRestaurantState.text = state
        area = arguments?.getString("area")
        textViewRestaurantArea.text = area

        img_url =  arguments?.getString("img_url")
        Glide.with(view.context).load(img_url).into(imageViewRestaurant)

        setButtonFav(view)

        return view
    }

    private fun setButtonFav(view : View)
    {
        favouriteRestaurantViewModel.getIdFavouriteRestaurant(phone!!)
        var idFavorRes = favouriteRestaurantViewModel.id
        idFavorRes.observe(viewLifecycleOwner) {
            id_res = it
        }
        buttonFav.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                try{
                    this.dialog(view)
                }catch (value: Exception){
                    Toast.makeText(requireContext(), "Not user!", Toast.LENGTH_LONG).show()
                }
            }
            else{
                id_res?.let { favouriteRestaurantViewModel.deleteFavouriteRestaurant(
                        FavouriteRestaurantData(
                                it, 1, img_url!!, name!!, phone!!, city!!, area!!, state!!
                        )
                ) }
                Toast.makeText(requireContext(), "Not favourite!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun dialog(view: View)
    {
        var profilsName = profiles.map { it.nameProfile }
        var list : ArrayList<String> = ArrayList<String>()
        list.add("None")
        list.addAll(profilsName)
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, list)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val builder = AlertDialog.Builder(view.context)
        val inflater: LayoutInflater = (activity?.layoutInflater ?: null) as LayoutInflater
        val dialogview: View = inflater.inflate(R.layout.dialogrestaurant, null)
        var spin : Spinner = dialogview.findViewById(R.id.SpinnerRestaurantProfile)
        spin.adapter = dataAdapter
        setSpinnerListener(spin)
        builder.setView(dialogview)
        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
            if (id_user == -1)
            {
                Toast.makeText(requireContext(), "Not changed user!", Toast.LENGTH_LONG).show()
                buttonFav.isChecked=false
                dialog.cancel()
            }
            else
            {
                if (checkedRestaurant())
                {
                    buttonFav.isChecked=true
                    Toast.makeText(requireContext(), "This restaurant already is favourite!", Toast.LENGTH_LONG).show()
                }
                else{
                    name?.let { FavouriteRestaurantData(0, id_user , img_url!!, it, phone!!, city!!, area!!, state!!) }?.let { favouriteRestaurantViewModel.insert(it) }
                    Toast.makeText(requireContext(), "Favourite!", Toast.LENGTH_LONG).show()
                }
            }
        })
        builder.setNegativeButton("Done",DialogInterface.OnClickListener{ dialog, id ->
            buttonFav.isChecked=false
            dialog.cancel()
        })
        builder.show()
    }
    private fun checkedRestaurant() : Boolean
    {
        for (i in favouriteRestaurantList)
        {
            if(i.restaurantarea == area && i.restaurantcity == city && i.restaurantname == name && i.user_id == id_user
                    && i.restaurantphone == phone && i.restaurantstate == state)
            {
                return true
            }
        }
        return false
    }

    private fun setSpinnerListener(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                if (view != null)
                {
                    val textViewItem = view as TextView
                    if(textViewItem.text.toString() == "None") {
                        id_user = -1
                    }
                    else
                    {
                        id_user = position
                        favouriteRestaurantViewModel.getFavouriteRestaurants((position).toLong())
                        favouriteRestaurantViewModel.allFavouriteRestaurant.observe(viewLifecycleOwner){
                            favouriteRestaurantList = it
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
}
