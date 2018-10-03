package com.example.mobile_ui.browse

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.mobile_ui.R

import kotlinx.android.synthetic.main.activity_main.*

class BrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBrowseRecycler()

    }

    private fun setupBrowseRecycler() {
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

}
