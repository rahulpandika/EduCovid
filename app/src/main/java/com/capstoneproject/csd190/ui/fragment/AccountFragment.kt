package com.capstoneproject.csd190.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstoneproject.csd190.databinding.FragmentAccountBinding
import com.capstoneproject.csd190.ui.activity.LoginRegisterActivity
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private lateinit var accountBinding: FragmentAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        accountBinding = FragmentAccountBinding.inflate(layoutInflater, container, false)

        return accountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        accountBinding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            val email = firebaseUser.email
            accountBinding.gmail.text = email
        } else {
            startActivity(Intent(activity, LoginRegisterActivity::class.java))
            activity?.finish()
        }
    }
}