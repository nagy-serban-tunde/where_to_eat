package com.example.wheretoeat.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wheretoeat.Database.Entities.ProfileData
import com.example.wheretoeat.Database.ViewModels.ProfileViewModel
import com.example.wheretoeat.FileUtil
import com.example.wheretoeat.R


class UpdateProfileFragment : Fragment() {

    lateinit var buttonImage : Button
    lateinit var buttonUpdateUser : Button
    lateinit var imageViewProfileUpdate: ImageView
    lateinit var editTextNameProfileUpdate: EditText
    lateinit var editTextPhoneProfileUpdate: EditText
    lateinit var editTextAddressProfileUpdate: EditText
    lateinit var editTextEmailProfileUpdate: EditText

    private var id : Int? = null
    private var name : String? = null
    lateinit var img_url : String
    private var phone : String? = null
    private var address : String? = null
    private var email : String? = null

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_update_profile, container, false)

        buttonImage = view.findViewById(R.id.buttonProfileUpdate)
        buttonUpdateUser = view.findViewById(R.id.ButtonUpdate)

        imageViewProfileUpdate = view.findViewById(R.id.imageViewProfileUpdate)

        editTextNameProfileUpdate = view.findViewById(R.id.EditTextNameUpdate)
        editTextPhoneProfileUpdate = view.findViewById(R.id.EditTextPhoneUpdate)
        editTextAddressProfileUpdate = view.findViewById(R.id.EditTextAdressUpdate)
        editTextEmailProfileUpdate = view.findViewById(R.id.EditTextAdressEmailUpdate)

        id = arguments?.getString("id")?.toInt()
        name = arguments?.getString("name")
        img_url = arguments?.getString("img_url").toString()
        phone = arguments?.getString("phone")
        address = arguments?.getString("address")
        email = arguments?.getString("email")

        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        editTextNameProfileUpdate.setText(name)
        editTextPhoneProfileUpdate.setText(phone)
        editTextEmailProfileUpdate.setText(email)
        editTextAddressProfileUpdate.setText(address)
        Glide.with(view.context).load(img_url).into(imageViewProfileUpdate)

        buttonImage.setOnClickListener()
        {
            startActivityForResult(Intent.createChooser(Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), "Select the image"), 0)
        }

        buttonUpdateUser.setOnClickListener()
        {
            this.checkInputData()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { imageUrl ->
                this.img_url = FileUtil.from(requireActivity(), imageUrl).path
                view?.context?.let { Glide.with(it).load(imageUrl).into(imageViewProfileUpdate) }
            }
        }
    }

    private fun checkInputData()
    {
        name = editTextNameProfileUpdate.text.toString()
        phone = editTextPhoneProfileUpdate.text.toString()
        Log.i("resp","${phone!!.length}")
        address = editTextAddressProfileUpdate.text.toString()
        email = editTextEmailProfileUpdate.text.toString()
        if( !this::img_url.isInitialized || !this.inputCheckText(this.name!!, this.phone!!, this.address!!, this.img_url!!, this.email!!)
            || !this.isEmailValid(this.email!!) || this.isLength(this.name!!, this.phone!!, this.address!!, this.img_url!!, this.email!!)
            || !this.inputCheckBlank(this.name!!, this.phone!!, this.address!!, this.img_url!!, this.email!!))
        {
            Toast.makeText(context,"Wrong data!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            profileViewModel.update(ProfileData(id!!,name!!,img_url,address!!,phone!!,email!!))
            Toast.makeText(requireContext(),"Successfully updated!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.profileScreenFragment)
        }
    }
    private fun isLength(name: String, phone:String, address : String, url: String, email: String):Boolean
    {
        if (name.length != 0 && phone.length != 0 && address.length != 0 && url.length != 0 && email.length != 0)
        {
            return false
        }
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun inputCheckText(name: String, phone:String, address : String, url: String, email: String) : Boolean
    {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address) && TextUtils.isEmpty(url) && TextUtils.isEmpty(email))
    }

    private fun inputCheckBlank(name: String, phone:String, address : String, url: String, email: String) : Boolean
    {
        return !(name.isBlank() && phone.isBlank() && address.isBlank() && url.isBlank() && email.isBlank())
    }

}