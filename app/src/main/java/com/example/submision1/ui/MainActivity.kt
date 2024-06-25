package com.example.submision1.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submision1.R
import com.example.submision1.data.response.ItemsItem
import com.example.submision1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel    by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    mainViewModel.findUser(searchView.text.toString())
                    false
                }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.show.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.show.addItemDecoration(itemDecoration)



        mainViewModel.listUser.observe(this) { itemsItem ->
            setUserData(itemsItem)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }


    }
    private fun setUserData(itemsItem: List<ItemsItem?>) {
        val adapter = ListUserAdapter()
        adapter.submitList(itemsItem)
        binding.show.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}