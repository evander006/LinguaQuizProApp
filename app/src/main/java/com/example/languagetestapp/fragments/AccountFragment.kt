package com.example.languagetestapp.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentAccountBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage
import com.squareup.picasso.Picasso


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var navControl: NavController
    private lateinit var imageUri: Uri
    private lateinit var storageReference: StorageReference
    private var savedImageUri: Parcelable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        storageReference = FirebaseStorage.getInstance().reference

        binding.materialButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val savedImageUri = sharedPref?.getString("savedImageUri", null)
        if (savedImageUri != null) {
            Picasso.get().load(savedImageUri).into(binding.profileImage)
        }
        binding.arrowback.setOnClickListener {
            navControl.navigate(R.id.action_accountFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControl= Navigation.findNavController(view)
        getString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data!!

            val imageRef = storageReference.child("images/image.jpg")

            imageRef.putFile(imageUri)
                .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    imageRef.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { downloadUri ->
                        // Save image Uri to SharedPreferences
                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@OnSuccessListener
                        with (sharedPref.edit()) {
                            putString("savedImageUri", downloadUri.toString())
                            apply()
                        }

                        Picasso.get().load(downloadUri).into(binding.profileImage)

                    })
                })
                .addOnFailureListener(OnFailureListener { e ->
                })
        }

    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private fun getString(){
        val sharedPrefForName = activity?.getPreferences(Context.MODE_PRIVATE)
        val sharedPrefForSurname = activity?.getPreferences(Context.MODE_PRIVATE)
        val sharedPrefForGoal = activity?.getPreferences(Context.MODE_PRIVATE)

        binding.editTextName.setText(sharedPrefForName?.getString("savedText",""))
        binding.editTextName.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                with (sharedPrefForName?.edit()) {
                    this?.putString("savedText", binding.editTextName.text.toString())
                    this?.apply()
                }
            }
        }

        binding.editTextSurname.setText(sharedPrefForSurname?.getString("savedText2",""))
        binding.editTextSurname.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                with (sharedPrefForSurname?.edit()) {
                    this?.putString("savedText2", binding.editTextSurname.text.toString())
                    this?.apply()
                }
            }
        }

        binding.goalText.setText(sharedPrefForGoal?.getString("savedText3",""))
        binding.goalText.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                with (sharedPrefForGoal?.edit()) {
                    this?.putString("savedText3", binding.goalText.text.toString())
                    this?.apply()
                }
            }
        }
    }
}
