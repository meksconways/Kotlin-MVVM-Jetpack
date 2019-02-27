package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryVM
import com.mek.haberlerkotlin.utils.CustomSnapHelper
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.gallery_fragment.*
import javax.inject.Inject

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private lateinit var viewModel: GalleryVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.getAppComponent(context).inject(this)
    }

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(GalleryVM::class.java)
        val vm = ViewModelProviders.of(activity!!,factory).get(MainGalleryVM::class.java)
        rv_galleryMain.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rv_galleryMain.adapter = GalleryMainAdapter(this,viewModel,this,vm)
        val snapHelper: SnapHelper = CustomSnapHelper()
        snapHelper.attachToRecyclerView(rv_galleryMain)


    }





}
