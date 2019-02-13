package com.mek.haberlerkotlin.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.navigation.TabManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    override fun onNavigationItemReselected(item: MenuItem) {
        tabManager.clearTabStack()
    }

    private val tabManager: TabManager by lazy { TabManager(this) }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        tabManager.switchTab(item.itemId)
        return true
    }

    private lateinit var viewmodel:MainActivityVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.getAppComponent(this).inject(this)
        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.setOnNavigationItemReselectedListener(this)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            tabManager.switchTab(R.id.act1)
        }
        viewmodel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        observeViewModel()

    }

    private fun observeViewModel() {
        viewmodel.getTitle().observe(this, Observer<String> {
            actionBar?.run {
                title = it
            }
        })
        viewmodel.getHasBackButton().observe(this, Observer<Boolean> {
            actionBar?.run {
                setDisplayHomeAsUpEnabled(it)
            }
        })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tabManager.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        tabManager.onRestoreInstanceState(savedInstanceState)
    }

    override fun onBackPressed() {
        tabManager.onBackPressed()
    }


    override fun supportNavigateUpTo(upIntent: Intent) {
        tabManager.supportNavigateUpTo(upIntent)
    }


}




