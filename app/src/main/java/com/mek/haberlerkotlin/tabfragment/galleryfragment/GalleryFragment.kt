package com.mek.haberlerkotlin.tabfragment.galleryfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.utils.CustomSnapHelper
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private lateinit var viewModel: GalleryVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GalleryVM::class.java)

        rv_galleryMain.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rv_galleryMain.adapter = GalleryMainAdapter()
        val snapHelper: SnapHelper = CustomSnapHelper()
        snapHelper.attachToRecyclerView(rv_galleryMain)

    }

}
