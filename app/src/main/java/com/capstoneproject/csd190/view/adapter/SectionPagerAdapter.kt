package com.capstoneproject.csd190.view.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.capstoneproject.csd190.R
import com.capstoneproject.csd190.view.loginregister.LoginFragment
import com.capstoneproject.csd190.view.loginregister.SignUpFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> LoginFragment()
            1 -> SignUpFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.login, R.string.register)
    }
}