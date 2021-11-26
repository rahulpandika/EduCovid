package com.example.educovid.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.educovid.MainActivity
import com.example.educovid.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        activityHomeBinding.btnLogoit.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
            activityHomeBinding.gmail.text = email
        }
        else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}