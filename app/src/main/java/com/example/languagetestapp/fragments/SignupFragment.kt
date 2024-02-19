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
import com.example.languagetestapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding: FragmentSignupBinding
    private lateinit var navControl: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignupBinding.inflate(inflater, container, false)
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
        binding.signin.setOnClickListener {
            navControl.navigate(R.id.action_signupFragment_to_signinFragment)
        }

        binding.next.setOnClickListener {
            val email=binding.emailInput.text.toString().trim()
            val pass=binding.passw.text.toString().trim()
            val reppass=binding.reppassw.text.toString().trim()
            if (email.isNotEmpty()&&pass.isNotEmpty()&&reppass.isNotEmpty()){
                if(pass==reppass){
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener({
                        if (it.isSuccessful){
                            Toast.makeText(context, "Зарегистрирован успешно",Toast.LENGTH_LONG).show()
                            navControl.navigate(R.id.action_signupFragment_to_homeFragment)
                        }else{
                            Toast.makeText(context, it.exception?.message,Toast.LENGTH_LONG).show()
                        }

                    })
                }else{
                    Toast.makeText(context, "Пароли не совпадают",Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}