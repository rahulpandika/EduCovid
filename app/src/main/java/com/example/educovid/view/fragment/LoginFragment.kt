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
import com.example.educovid.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var fragmentLoginBinding: FragmentLoginBinding

    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        val view = fragmentLoginBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Loading
        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Silahkan Tunggu")
        progressDialog.setCanceledOnTouchOutside(false)

        //firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


        fragmentLoginBinding.btnLogin.setOnClickListener {
            checkData()
        }

    }

    private fun checkData() {
        email = fragmentLoginBinding.etUsername.text.toString().trim()
        password = fragmentLoginBinding.etPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            fragmentLoginBinding.etUsername.setError("Masukkan email dengan benar!!")
        } else if (TextUtils.isEmpty(password)) {
            fragmentLoginBinding.etPassword.setError("Masukkan Password!!")
        } else {
            loginFirebase()
        }
    }

    private fun loginFirebase() {
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()

                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(activity, "Masuk sebagai $email", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)

            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(activity, "Login Gagal ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        val user = firebaseAuth.currentUser
        if (user != null) {
            startActivity(Intent(activity, HomeActivity::class.java))
        }
    }

}
