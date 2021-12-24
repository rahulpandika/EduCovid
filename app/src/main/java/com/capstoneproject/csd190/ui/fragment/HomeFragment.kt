package com.capstoneproject.csd190.ui.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstoneproject.csd190.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        fragmentHomeBinding.buttonToProtect.setOnClickListener {
            val url =
                "https://www.kemkes.go.id/article/view/20050400002/6-arahan-presiden-tangani-covid-19.html"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            startActivity(intent)
        }

        return fragmentHomeBinding.root
    }
}