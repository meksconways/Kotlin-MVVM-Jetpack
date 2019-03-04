package com.mek.haberlerkotlin.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.ui.ScreenNavigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private lateinit var viewmodel: MainActivityVM

    @Inject
    lateinit var screenNavigator: ScreenNavigator


    private val navController by lazy {
        Navigation.findNavController(this, R.id.container_navhost)
    }

    override fun onSupportNavigateUp(): Boolean = navController
        .navigateUp()


    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.elevation = 0f
        viewmodel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        viewmodel.setBottomBarBehavior(false)
        observeViewModel()


        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.home1,
                R.id.home2,
                R.id.home3)
            .build()

        setupActionBarWithNavController(this, navController, appBarConfiguration)
        bottom_nav.setupWithNavController(navController)


        bottom_nav.setOnNavigationItemSelectedListener { item ->

            onNavDestinationSelected(item, navController)
        }


    }


    private fun slideUp(child: BottomNavigationView) {
        child.clearAnimation()
        child.visibility = View.VISIBLE
        child.animate().translationY(0F).duration = 200

    }

    private fun slideDown(child: BottomNavigationView) {
        child.clearAnimation()
        child.animate()
            .translationY(child.height.toFloat())
            .setDuration(200)
            .withEndAction {
                child.visibility = View.GONE
            }

    }


    @SuppressLint("ResourceAsColor")
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
//        viewmodel.getHasBackButton().observe(this, Observer<Boolean> {
//            supportActionBar?.run {
//                setDisplayHomeAsUpEnabled(it)
//            }
//        })

    }



}
