package com.capstoneproject.csd190.ui.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstoneproject.csd190.databinding.FragmentPreventionBinding

class PreventionFragment : Fragment() {
    private lateinit var fragmentPreventionBinding: FragmentPreventionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentPreventionBinding =
            FragmentPreventionBinding.inflate(layoutInflater, container, false)

        return fragmentPreventionBinding.root
    }
}