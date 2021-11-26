package com.example.educovid.view.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.educovid.view.HomeActivity
import com.example.educovid.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        val view = signUpBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Silahkan Tunggu")
        progressDialog.setMessage("Membuat Akun...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        signUpBinding.btnSignup.setOnClickListener() {
            checkData()
        }
    }
    private fun checkData() {

        email = signUpBinding.etUsername.text.toString().trim()
        password = signUpBinding.etPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpBinding.etUsername.setError("Masukkan email dengan benar!!")
        }
        else if (TextUtils.isEmpty(password)){
            signUpBinding.etPassword.setError("Masukkan Password!!")
        }
        else if (password.length<6){
            signUpBinding.etPassword.error = "Password minimal 8 Karakter"
        }
        else{
            signUpFirebase()
        }
    }

    private fun signUpFirebase() {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            val firebaseUser = firebaseAuth.currentUser
            val email =  firebaseUser!!.email
            Toast.makeText(activity,"Mendaftar sebagai $email", Toast.LENGTH_SHORT).show()

            startActivity(Intent(activity, HomeActivity::class.java))
            activity?.finish()

        }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(activity,"Gagal Mendaftar ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}