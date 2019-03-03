package com.mek.haberlerkotlin.navigation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivityVM

class BottomNavController(
    val context: Context,
    @IdRes val containerId: Int,
    @IdRes val appStartDestinationId: Int
) {
    private val navigationBackStack = mutableListOf(appStartDestinationId)
    lateinit var activity: Activity
    lateinit var fragmentManager: FragmentManager
    private var listener: OnNavigationItemChanged? = null

    interface OnNavigationItemChanged {
        fun onItemChanged(itemId: Int)
    }

    init {
        var ctx = context
        while (ctx is ContextWrapper) {
            if (ctx is Activity) {
                activity = ctx
                fragmentManager = (activity as FragmentActivity).supportFragmentManager
                break
            }
            ctx = ctx.baseContext
        }
    }

    fun setOnItemNavigationChanged(listener: (itemId: Int) -> Unit) {
        this.listener = object : OnNavigationItemChanged {
            override fun onItemChanged(itemId: Int) {
                listener.invoke(itemId)
            }
        }
    }

    fun onNavigationItemReselected(item: MenuItem) {
        // If the user press a second time the navigation button, we pop the back stack to the root
        activity.findNavController(containerId).popBackStack(item.itemId, false)
    }

    @NavigationRes
    fun getNavGraphId(itemId: Int) = when (itemId) {
        R.id.home1 -> R.navigation.nav_newsfeed
        R.id.home2 -> R.navigation.nav_gallery
        R.id.home3 -> R.navigation.nav_column
        else -> throw RuntimeException("ID ler graphlarla aynı olmalı")
    }

    fun onNavigationItemSelected(itemId: Int = navigationBackStack.last()): Boolean {

        // Replace fragment representing a navigation item
        val fragment = fragmentManager.findFragmentByTag(itemId.toString())
            ?: NavHostFragment.create(getNavGraphId(itemId))

        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.nav_default_enter_anim,
                R.anim.nav_default_exit_anim,
                R.anim.nav_default_pop_enter_anim,
                R.anim.nav_default_pop_exit_anim
            )
            .replace(containerId, fragment, itemId.toString())
            .addToBackStack(null)
            .commit()

        // Add to back stack
        navigationBackStack.moveLast(itemId)

        listener?.onItemChanged(itemId)

        return true
    }

    fun onBackPressed() {

        // Get the active fragment
        val fragment = fragmentManager.findFragmentById(containerId)
        when {
            // We should always try to go back on the child fragment manager stack before going to
            // the navigation stack
            fragment?.findNavController()?.popBackStack() == true -> return
            // Fragment back stack is empty so try to go back on the navigation stack
            navigationBackStack.size > 1 -> {
                // Remove last item from back stack
                navigationBackStack.removeLast()

                // Update the container with new fragment
                onNavigationItemSelected()
            }
            // If the stack has only one and it not the navigation home we should
            // ensure that the application always leave from startDestination
            navigationBackStack.last() != appStartDestinationId -> {
                navigationBackStack.removeLast()
                navigationBackStack.add(0, appStartDestinationId)
                onNavigationItemSelected()
            }
            // Navigation stack is empty, so finish the activity
            else -> activity.finish()
        }
    }
}

fun <E> MutableList<E>.removeLast() = removeAt(size - 1)

fun <E> MutableList<E>.moveLast(item: E) {
    this.remove(item)
    this.add(item)
}

fun BottomNavigationView.setUpNavigation(bottomNavController: BottomNavController,viewModel: MainActivityVM) {
    setOnNavigationItemSelectedListener {
        bottomNavController.onNavigationItemSelected(it.itemId)
    }
    setOnNavigationItemReselectedListener {
        bottomNavController.onNavigationItemReselected(it)


    }
    bottomNavController.setOnItemNavigationChanged { itemId ->
        menu.findItem(itemId).isChecked = true
    }
}
