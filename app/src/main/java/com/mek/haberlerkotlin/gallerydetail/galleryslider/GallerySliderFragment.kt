package com.mek.haberlerkotlin.gallerydetail.galleryslider

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivityVM

class GallerySliderFragment : Fragment() {

    companion object {
        fun newInstance() = GallerySliderFragment()
    }

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
        viewModel = ViewModelProviders.of(this).get(GallerySliderVM::class.java)
        mainActivityVM = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainActivityVM.setTitle("Galeri")
        mainActivityVM.setHasBackButton(true)

    }



}
