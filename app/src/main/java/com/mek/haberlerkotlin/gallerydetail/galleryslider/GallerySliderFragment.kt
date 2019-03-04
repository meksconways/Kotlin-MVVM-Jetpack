package com.mek.haberlerkotlin.gallerydetail.galleryslider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.utils.fromChildFragment
import kotlinx.android.synthetic.main.gallery_slider_fragment.*

class GallerySliderFragment : Fragment() {

    private lateinit var viewModel: GallerySliderVM
    private lateinit var mainActivityVM: MainActivityVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_slider_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(GallerySliderVM::class.java)
        mainActivityVM = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainActivityVM.setTitle("Galeri")
        mainActivityVM.setHasBackButton(true)
        mainActivityVM.setBottomBarBehavior(false)
        fromChildFragment = true
        rv_gallerySlider.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_gallerySlider.adapter = GallerySliderAdapter(viewModel, this)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_gallerySlider)
        viewModel.getPosition().observe(this, Observer { pos ->
            rv_gallerySlider.scrollToPosition(pos)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityVM.setBottomBarBehavior(true)
        if (fromChildFragment) fromChildFragment = false
    }

}
