package com.example.mobile_ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout

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
