package com.example.wheretoeat.Fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
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


class AddProfileFragment : Fragment() {

    lateinit var buttonImage : Button
    lateinit var buttonAddUser : Button
    lateinit var imageViewProfileAdd: ImageView
    lateinit var editTextNameProfileAdd: EditText
    lateinit var editTextPhoneProfileAdd: EditText
    lateinit var editTextAddressProfileAdd: EditText
    lateinit var editTextEmailProfileAdd: EditText

    private lateinit var url : String

    private lateinit var profileViewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_profile, container, false)
        buttonImage = view.findViewById(R.id.buttonProfileAdd)
        buttonAddUser = view.findViewById(R.id.ButtonAdd)
        imageViewProfileAdd = view.findViewById(R.id.imageViewProfileAdd)

        editTextNameProfileAdd = view.findViewById(R.id.EditTextNameAdd)
        editTextPhoneProfileAdd = view.findViewById(R.id.EditTextPhoneAdd)
        editTextAddressProfileAdd = view.findViewById(R.id.EditTextAdressAdd)
        editTextEmailProfileAdd = view.findViewById(R.id.EditTextAdressEmailAdd)

        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        buttonImage.setOnClickListener()
        {
            startActivityForResult(Intent.createChooser(Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), "Select the image"), 0)
        }

        buttonAddUser.setOnClickListener()
        {
            checkInputData()
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK) {
            data?.data?.let { imageUrl ->
                this.url = FileUtil.from(requireActivity(), imageUrl).path
                view?.context?.let { Glide.with(it).load(imageUrl).into(imageViewProfileAdd) }
            }
        }
    }

    private fun checkInputData()
    {
        val name: String = this.editTextNameProfileAdd.text.toString()
        val phone:String = this.editTextPhoneProfileAdd.text.toString()
        val address : String = this.editTextAddressProfileAdd.text.toString()
        val email: String = this.editTextEmailProfileAdd.text.toString()

        if( !this::url.isInitialized || !this.inputCheckText(name, phone, address, this.url, email) || !this.isEmailValid(email)
            || this.isLength(name, phone, address, this.url, email) || !this.isEmailValid(email)
            || !this.inputCheckBlank(name, phone, address, this.url, email) || !this.isEmailValid(email) )
        {
            Toast.makeText(context,"Wrong data!",Toast.LENGTH_SHORT).show()
        }
        else
        {
            val profileItem = ProfileData(0,name,url,address,phone,email)
            profileViewModel.insert(profileItem)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
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

    fun isEmailValid(email: String): Boolean {
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