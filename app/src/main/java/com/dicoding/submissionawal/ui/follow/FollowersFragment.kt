package com.dicoding.submissionawal.ui.follow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissionawal.databinding.FragmentFollowersBinding
import com.dicoding.submissionawal.ui.MainViewModel
import com.dicoding.submissionawal.ui.UserAdapter

class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
        adapter = UserAdapter()

        arguments.let { val username = it?.getString(ARG_USERNAME)
            Log.d(TAG, "onViewCreated: $username")
            mainViewModel.apply {
                getFollowers(username!!)
                listFollower.observe(viewLifecycleOwner){
                    adapter.submitList(it)
                    binding.rvFollow.adapter = adapter
                    val layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvFollow.layoutManager = layoutManager
                    val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
                    binding.rvFollow.addItemDecoration(itemDecoration)
                }

                isLoading.observe(viewLifecycleOwner){
                    showLoading(it)
                }
            }
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
        const val TAG="FRAGMENT FOLLOWER"
        const val ARG_USERNAME = "login"
    }

}