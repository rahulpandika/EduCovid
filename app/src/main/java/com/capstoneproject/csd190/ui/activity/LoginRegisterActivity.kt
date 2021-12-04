package com.capstoneproject.csd190.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.capstoneproject.csd190.databinding.ActivityLoginRegisterBinding
import com.capstoneproject.csd190.viewAdapter.SectionPagerAdapter

class LoginRegisterActivity : AppCompatActivity() {
    private lateinit var activityLoginRegisterBinding: ActivityLoginRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginRegisterBinding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(activityLoginRegisterBinding.root)

        val window = this.window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        window.statusBarColor = Color.TRANSPARENT

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)

        activityLoginRegisterBinding.viewpager.adapter = sectionPagerAdapter

        activityLoginRegisterBinding.tabs.setupWithViewPager(activityLoginRegisterBinding.viewpager)

        supportActionBar?.elevation = 0f
    }
}