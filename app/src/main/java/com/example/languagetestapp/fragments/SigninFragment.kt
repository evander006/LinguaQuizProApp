package com.example.languagetestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentSigninBinding
import com.google.firebase.auth.FirebaseAuth

class SigninFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding: FragmentSigninBinding
    private lateinit var navControl: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSigninBinding.inflate(inflater, container, false)
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        regUser()
    }

    private fun init(view: View){
        navControl=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }

    private fun regUser(){
        binding.signup.setOnClickListener {
            navControl.navigate(R.id.action_signinFragment_to_signupFragment)
        }

        binding.next.setOnClickListener {
            val email=binding.emailInput.text.toString().trim()
            val pass=binding.passw.text.toString().trim()
            if (email.isNotEmpty()&&pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener({
                    if (it.isSuccessful){
                        Toast.makeText(context, "Успешный вход", Toast.LENGTH_LONG).show()
                        navControl.navigate(R.id.action_signinFragment_to_homeFragment)
                    }else{
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                })
            }else{
                Toast.makeText(context, "Поле email или password пустое", Toast.LENGTH_LONG).show()
            }

        }
    }
}