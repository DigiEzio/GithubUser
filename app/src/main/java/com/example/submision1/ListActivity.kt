package com.example.submision1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submision1.databinding.ActivityListBinding
import com.example.submision1.databinding.ActivityMainBinding
import com.example.submision1.ui.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityListBinding

    private val viewListModel by viewModels<ListViewModel>()

    companion object {
        const val EXTRA_UNAME = "user.name"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getStringExtra(EXTRA_UNAME) != null) {
            val username = intent.getStringExtra(EXTRA_UNAME)
            viewListModel.getDetailUser(username.toString())
            viewListModel.listDetailUser.observe(this) { user ->
                Glide.with(this)
                    .load(user?.avatarUrl)
                    .into(binding.imgProfile)
                binding.name.text = user?.name.toString()
                binding.username.text = user?.login.toString()
                binding.tvFollowers.text = "${user?.followers.toString()} Followers"
                binding.tvFollowing.text = "${user?.following.toString()} Following"
            }

            val sectionsPagerAdapter = SectionsPagerAdapter(this)
            sectionsPagerAdapter.username = "user.name"
            binding.viewPager.adapter = sectionsPagerAdapter
            val viewPager: ViewPager2 = findViewById(R.id.view_pager)
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
            supportActionBar?.elevation = 0f

            viewListModel.loading.observe(this) {
                showLoading(it)
            }

        }

    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
        binding.progressBar.visibility = View.VISIBLE
        }else {
            binding.progressBar.visibility = View.GONE
        }
    }


}
