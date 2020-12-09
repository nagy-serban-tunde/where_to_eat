package com.example.wheretoeat.Fragments

import android.content.Context
import android.media.Ringtone
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.ProfileData
import com.example.wheretoeat.Database.ViewModels.ProfileViewModel
import com.example.wheretoeat.R

class ProfileScreenFragment : Fragment() {
    lateinit var imageViewProfileProfile : ImageView
    lateinit var textViewNameProfile : TextView
    lateinit var textViewPhoneProfile : TextView
    lateinit var textViewAddressProfile : TextView
    lateinit var textViewEmailProfile : TextView
    lateinit var spinnerProfile : Spinner

    private lateinit var profileViewModel: ProfileViewModel

    lateinit var profiles : List<ProfileData>


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

        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        profileViewModel.onLoadingFinished={
            profiles = profileViewModel.allProfile
            setSpinner(view.context)
        }
        return view
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
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
            {
                val textViewItem = view as TextView
                if(textViewItem.text.toString() == "None") { }
                else
                {
                    textViewNameProfile.text = profiles[position-1].nameProfile
                    textViewPhoneProfile.text = profiles[position-1].phoneProfle
                    textViewEmailProfile.text = profiles[position-1].emailProfile
                    textViewAddressProfile.text = profiles[position-1].addressProfile
                    Glide.with(context).load(profiles[position-1].picture).into(imageViewProfileProfile)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

}