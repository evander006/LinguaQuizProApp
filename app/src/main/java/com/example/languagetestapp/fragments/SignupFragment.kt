package com.example.languagetestapp.fragments

import android.content.Intent
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignupFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding: FragmentSignupBinding
    private lateinit var navControl: NavController
    companion object {
        private const val RC_SIGN_IN = 9001
    }
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
        binding.googleAuth.setOnClickListener {
            signIn()
        }
    }

    private fun init(view: View){
        navControl=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }

    private fun signIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(context, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(context, "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    navControl.navigate(R.id.action_signupFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
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