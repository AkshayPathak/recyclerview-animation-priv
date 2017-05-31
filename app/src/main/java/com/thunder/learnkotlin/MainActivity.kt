package com.thunder.learnkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerViewAdapter
    var doneMenu: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager: LinearLayoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter()

        recyclerview.layoutManager = manager
        recyclerview.adapter = adapter
        recyclerview.hasFixedSize()

        fab.setOnClickListener({
            adapter.triggerSelectionMode()
            doneMenu?.isVisible = true
            fab.visibility = View.INVISIBLE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        doneMenu = menu?.findItem(R.id.menu_done)
        doneMenu?.isVisible = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_done -> {

                adapter.triggerSelectionMode()

                fab.visibility = View.VISIBLE
                doneMenu?.isVisible = false
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}