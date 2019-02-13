package com.mek.haberlerkotlin.navigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class TabManager(private val mainActivity: MainActivity) {


    private val startDestinations = mapOf(
        R.id.act1 to R.id.listNewsFragment2,
        R.id.act2 to R.id.galleryFragment,
        R.id.act3 to R.id.columnistsFragment,
        R.id.act4 to R.id.writerFragment,
        R.id.act5 to R.id.readLaterFragment
        )

    private var currentTabId: Int = R.id.act1
    var currentController: NavController? = null

   // private var tabHistory = TabHistory().apply { push(R.id.nav_newsfeed) }
    private var tabHistory = TabHistory()

    private val navNewsFeedController: NavController by lazy {
        mainActivity.findNavController(R.id.newsTab).apply {
            graph = navInflater.inflate(R.navigation.nav_newsfeed).apply {
                startDestination = startDestinations.getValue(R.id.act1)
            }
        }
    }
    private val navGalleryController: NavController by lazy {
        mainActivity.findNavController(R.id.galleryTab).apply {
            graph = navInflater.inflate(R.navigation.nav_gallery).apply {
                startDestination = startDestinations.getValue(R.id.act2)
            }
        }
    }
    private val navColumnistController: NavController by lazy {
        mainActivity.findNavController(R.id.columnistTab).apply {
            graph = navInflater.inflate(R.navigation.nav_columnist).apply {
                startDestination = startDestinations.getValue(R.id.act3)
            }
        }
    }
    private val navWriterController: NavController by lazy {
        mainActivity.findNavController(R.id.writerTab).apply {
            graph = navInflater.inflate(R.navigation.nav_writers).apply {
                startDestination = startDestinations.getValue(R.id.act4)
            }
        }
    }
    private val navReadLaterController: NavController by lazy {
        mainActivity.findNavController(R.id.readlaterTab).apply {
            graph = navInflater.inflate(R.navigation.nav_readlater).apply {
                startDestination = startDestinations.getValue(R.id.act5)
            }
        }
    }

    private val newsFeedTabContainer: View by lazy { mainActivity.container_news }
    private val galleryTabContainer: View by lazy { mainActivity.container_gallery }
    private val columninstTabContainer: View by lazy { mainActivity.container_columnist }
    private val writerTabContainer: View by lazy { mainActivity.container_writer }
    private val readLaterTabContainer: View by lazy { mainActivity.container_readlater }

    fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(KEY_TAB_HISTORY, tabHistory)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            tabHistory = it.getSerializable(KEY_TAB_HISTORY) as TabHistory
            switchTab(mainActivity.bottom_nav.selectedItemId, false)
        }
    }
    fun supportNavigateUpTo(upIntent: Intent) {
        currentController?.navigateUp()
    }
    fun onBackPressed() {
        currentController?.let {
            if (it.currentDestination == null || it.currentDestination?.id == startDestinations.getValue(currentTabId)) {
                if (tabHistory.size > 1) {
                    val tabId = tabHistory.popPrevious()
                    switchTab(tabId, false)
                    mainActivity.bottom_nav.menu.findItem(tabId)?.isChecked = true
                } else {
                    mainActivity.finish()
                }
            }
            it.popBackStack()
        } ?: run {
            mainActivity.finish()
        }
    }


    fun switchTab(tabId: Int, addToHistory: Boolean = true) {
        currentTabId = tabId

        when (tabId) {
            R.id.act1 -> {
                currentController = navNewsFeedController
                invisibleTabContainerExcept(newsFeedTabContainer)
            }
            R.id.act2 -> {
               currentController = navGalleryController
               invisibleTabContainerExcept(galleryTabContainer)
            }
            R.id.act3 -> {
                currentController = navColumnistController
                invisibleTabContainerExcept(columninstTabContainer)
            }
            R.id.act4 -> {
                currentController = navWriterController
                invisibleTabContainerExcept(writerTabContainer)
            }
            R.id.act5 -> {
                currentController = navReadLaterController
                invisibleTabContainerExcept(readLaterTabContainer)
            }
        }
        if (addToHistory) {
            tabHistory.push(tabId)
        }
    }


    private fun invisibleTabContainerExcept(container: View) {
        newsFeedTabContainer.isVisible = false
        galleryTabContainer.isVisible = false
        columninstTabContainer.isVisible = false
        writerTabContainer.isVisible = false
        readLaterTabContainer.isVisible = false


        container.isVisible = true
    }

    fun clearTabStack() {
        currentController?.navigateUp()
    }


    companion object {
        private const val KEY_TAB_HISTORY = "key_tab_history"
    }




}