package com.example.wheretoeat.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wheretoeat.Adapters.FavouriteRestaurantAdapter
import com.example.wheretoeat.Database.Entities.ProfileData
import com.example.wheretoeat.Database.ViewModels.FavouriteRestaurantViewModel
import com.example.wheretoeat.Database.ViewModels.ProfileViewModel
import com.example.wheretoeat.R

class ProfileScreenFragment : Fragment() {
    lateinit var imageViewProfileProfile : ImageView
    lateinit var textViewNameProfile : TextView
    lateinit var textViewPhoneProfile : TextView
    lateinit var textViewAddressProfile : TextView
    lateinit var textViewEmailProfile : TextView
    lateinit var spinnerProfile : Spinner
    lateinit var buttonUpdate : Button

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var favouriteRestaurantViewModel: FavouriteRestaurantViewModel
    private lateinit var recyclerViewFavouriteRestaurant: RecyclerView

    lateinit var profiles : List<ProfileData>

    var id_res : Int = -1

    var img_url : String? = null

    var adapter : FavouriteRestaurantAdapter= FavouriteRestaurantAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        imageViewProfileProfile = view.findViewById(R.id.imageViewProfileProfile)
        textViewNameProfile = view.findViewById(R.id.textViewNameProfile)
        textViewPhoneProfile = view.findViewById(R.id.textViewPhoneProfile)
        textViewAddressProfile = view.findViewById(R.id.textViewAddressProfile)
        textViewEmailProfile = view.findViewById(R.id.textViewEmailProfile)
        spinnerProfile = view.findViewById(R.id.SpinnerProfile)
        recyclerViewFavouriteRestaurant = view.findViewById(R.id.favouriteRestaurantRecycler)
        buttonUpdate = view.findViewById(R.id.ButtonUpdate)

        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        profileViewModel.onLoadingFinished={
            profiles = profileViewModel.allProfile!!
            setSpinner(view.context)
        }
        profileViewModel.getProfiles()

        this.initRecyclerView(view.context)

        buttonUpdate.setOnClickListener()
        {
            if (id_res != -1)
            {
                val bundle = bundleOf("id" to "$id_res","name" to textViewNameProfile.text,
                    "img_url" to img_url, "phone" to textViewPhoneProfile.text, "address" to textViewAddressProfile.text,
                    "email" to textViewEmailProfile.text)
                findNavController().navigate(R.id.updateProfileFragment,bundle)
            }
            else{
                Toast.makeText(requireContext(),"Not changed user!", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun initRecyclerView(context: Context)
    {
        recyclerViewFavouriteRestaurant.layoutManager = LinearLayoutManager(context)
        favouriteRestaurantViewModel = ViewModelProvider(requireActivity()).get(FavouriteRestaurantViewModel::class.java)
        recyclerViewFavouriteRestaurant.adapter = adapter

    }

    private fun setSpinner(context: Context) {
        var profilsName = profiles.map { it.nameProfile }
        var list : ArrayList<String> = ArrayList<String>()
        list.add("None")
        list.addAll(profilsName)
        var adap: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
        spinnerProfile.adapter = adap
        setSpinnerListener(context)
    }

    private fun setSpinnerListener(context: Context) {
        spinnerProfile.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                if (view != null)
                {
                    val textViewItem = view as TextView
                    if(textViewItem.text.toString() == "None") { }
                    else
                    {
                        id_res = position
                        textViewNameProfile.text = profiles[position-1].nameProfile
                        textViewPhoneProfile.text = profiles[position-1].phoneProfle
                        textViewEmailProfile.text = profiles[position-1].emailProfile
                        textViewAddressProfile.text = profiles[position-1].addressProfile
                        Glide.with(context).load(profiles[position-1].picture).into(imageViewProfileProfile)
                        img_url = profiles[position-1].picture
                        favouriteRestaurantViewModel.getFavouriteRestaurants((position).toLong())
                        favouriteRestaurantViewModel.allFavouriteRestaurant.observe(viewLifecycleOwner){
                            adapter.setData(it)
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
}