package com.example.submision1


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submision1.databinding.FragmentFollowBinding
import com.example.submision1.ui.ListUserAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_POSITION = "param1"
//private const val ARG_USERNAME = "param2"
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"


class FollowFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var position: Int? = null
    private var username: String? = null
    private lateinit var followAdapter: FollowAdapter
    private val followModel by viewModels<FollowModel>()
    private lateinit var binding: FragmentFollowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            username = it.getString(ARG_PARAM2)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        followAdapter = FollowAdapter()
        binding.rvFollow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = followAdapter
        }
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvFollow.addItemDecoration(itemDecoration)

        arguments?.let {
            position = it.getInt(ARG_PARAM1)
        }
        if (position == 1) {
            followModel.loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            followModel.findFollowingUser(username = username ?:"user.name")
            followModel.followerList.observe(viewLifecycleOwner) { followerList ->
                followAdapter.submitList(followerList)
            }

        } else {
            followModel.loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            followModel.findFollowerUser(username = username ?:"user.name")
            followModel.followingList.observe(viewLifecycleOwner) { followingList ->
                followAdapter.submitList(followingList)
            }

        }
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            username = it.getString(ARG_PARAM2)
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
       const val ARG_PARAM1 ="arg_position"
        const val ARG_PARAM2 = "arg_username"

    }
}