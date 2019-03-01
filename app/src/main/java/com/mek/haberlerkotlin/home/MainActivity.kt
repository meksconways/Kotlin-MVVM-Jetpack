package com.mek.haberlerkotlin.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.navigation.BottomNavController
import com.mek.haberlerkotlin.navigation.setUpNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainActivityVM

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        Navigation.findNavController(this, R.id.container)
    }

    private val bottomNavController by lazy(LazyThreadSafetyMode.NONE) {
        BottomNavController(this, R.id.container, R.id.home1)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            bottomNavController.onNavigationItemSelected()
        }

        bottom_nav.setUpNavigation(bottomNavController)

        viewmodel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        observeViewModel()
        viewmodel.setBottomBarBehavior(true)


    }


    override fun onSupportNavigateUp(): Boolean = navController
        .navigateUp()

    override fun onBackPressed() = bottomNavController.onBackPressed()


    private fun slideUp(child: BottomNavigationView) {
        child.clearAnimation()
        child.animate().translationY(0F).duration = 200
        child.visibility = View.VISIBLE
    }

    private fun slideDown(child: BottomNavigationView) {
        child.clearAnimation()
        child.animate().translationY(bottom_nav.height.toFloat()).duration = 200
        child.visibility = View.GONE
    }


    private fun observeViewModel() {
        viewmodel.getBottomBarBehavior().observe(this, Observer { value ->
            if (value) {
                slideUp(bottom_nav)
            } else {
                slideDown(bottom_nav)
            }
        })
        viewmodel.getTitle().observe(this, Observer<String> {
            supportActionBar?.run {
                title = it
            }
        })
        viewmodel.getHasBackButton().observe(this, Observer<Boolean> {
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(it)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }


}
