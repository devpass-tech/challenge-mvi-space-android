package com.devpass.spaceapp.presentation.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.devpass.spaceapp.databinding.ActivityTabBinding
import com.devpass.spaceapp.launchList.data.LaunchModel
import com.devpass.spaceapp.presentation.FragmentDetails
import com.devpass.spaceapp.presentation.FragmentLaunchpad
import com.devpass.spaceapp.presentation.RocketFragment
import com.devpass.spaceapp.presentation.ViewPagerAdapter

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabBinding
    private val launchItem by lazy {
        intent.getParcelableExtra<LaunchModel>("LAUNCH")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    private fun bindViews() {
        val fragments = listOf(FragmentDetails(), RocketFragment(), FragmentLaunchpad.newInstance(launchItem!!.id))
        val fragmentsPageTitle = listOf("Details", "Rocket", "Launchpad")
        val viewPagerAdapter = ViewPagerAdapter(
            fragments = fragments,
            fragmentManager = supportFragmentManager,
            tittles = fragmentsPageTitle
        )

        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        launchItem?.let {
            binding.ivImageSpace.load(it.image)
            binding.tvTittle.text = it.name
            binding.tvDate.text = it.date
            binding.tvStatus.text = it.status
        }
        binding.btRunBack.setOnClickListener {
            finish()
        }
    }
}