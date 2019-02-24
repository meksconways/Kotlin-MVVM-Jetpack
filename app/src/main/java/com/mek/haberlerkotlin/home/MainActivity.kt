package com.mek.haberlerkotlin.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
        when(item.itemId){
            R.id.act1 -> viewmodel.setTitle("Hürriyet Haber")
        }
        return true
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private external fun stringFromJNI(): String

    private external fun simpleSum(i: Int, i1: Int): Int


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }


    private lateinit var viewmodel: MainActivityVM


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
        // Toast.makeText(this, simpleSum(3, 4).toString(), Toast.LENGTH_LONG).show()

//        val tv = TypedValue()
//
//        if (theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
//            bbHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics).toFloat()
//        }

        viewmodel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        observeViewModel()
        viewmodel.setBottomBarBehavior(true)


    }


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
