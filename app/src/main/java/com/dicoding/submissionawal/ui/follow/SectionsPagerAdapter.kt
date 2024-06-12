package com.dicoding.submissionawal.ui.follow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter (activity: AppCompatActivity): FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }
    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> {
                fragment = FollowersFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowersFragment.ARG_USERNAME, username)
                }
            }
            1 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowingFragment.ARG_USERNAME, username)
                }
            }
        }
        return fragment as Fragment
    }
}