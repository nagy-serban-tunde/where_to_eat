package com.example.wheretoeat.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.wheretoeat.R


class AddProfileFragment : Fragment() {

    lateinit var buttonImage : Button
    lateinit var imageViewProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_profile, container, false)
        buttonImage = view.findViewById(R.id.buttonProfileAdd)
        imageViewProfile = view.findViewById(R.id.imageViewProfileAdd)

        buttonImage.setOnClickListener()
        {
            Log.i("resp","itt vagyok")
            startActivityForResult(Intent.createChooser(Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), "Select the image"), 0)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == RESULT_OK) {
            data?.dataString?.let { imageUrl ->
                view?.context?.let { Glide.with(it).load(imageUrl).into(imageViewProfile) }
            }
        }
    }
}