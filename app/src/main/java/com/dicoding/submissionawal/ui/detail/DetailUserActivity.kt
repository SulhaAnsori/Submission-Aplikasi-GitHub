package com.dicoding.submissionawal.ui.detail

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.submissionawal.R
import com.dicoding.submissionawal.databinding.ActivityDetailUserBinding
import com.dicoding.submissionawal.ui.follow.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailUserViewModel::class.java]

        val dataUser = intent.getStringExtra(USERNAME)


        viewModel.getDetailProfile(dataUser!!)

        viewModel.isProfile.observe(this) {
            binding.apply {
                tvUsername.text = it.login
                tvName.text = it.name
                Glide.with(this@DetailUserActivity).load(it.avatarUrl)
                    .centerCrop()
                    .circleCrop()
                    .into(ivProfile)

                val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailUserActivity)
                sectionsPagerAdapter.username = intent.getStringExtra(USERNAME).toString()
                val viewPager: ViewPager2 = findViewById(R.id.view_pager)
                viewPager.adapter = sectionsPagerAdapter
                val tabs: TabLayout = findViewById(R.id.tabs)
                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    if (position == 1) {
                        tab.text = getString(TAB_TITLES[position], it.following)
                    } else {
                        tab.text = getString(TAB_TITLES[position], it.followers)
                    }

                }.attach()

            }
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val USERNAME = "username"
    }
}