package com.example.submision1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submision1.data.response.ItemsItem
import com.example.submision1.databinding.ActivityMainBinding
import com.example.submision1.ui.ListUserAdapter

class SectionsPagerAdapter(activity: AppCompatActivity, ) : FragmentStateAdapter(activity) {

    var username: String = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {


            val fragment = FollowFragment()
            fragment.arguments = Bundle().apply {
                putInt(FollowFragment.ARG_PARAM1, position + 1)
                putString(FollowFragment.ARG_PARAM2, username)
            }
            return fragment
        }
}


