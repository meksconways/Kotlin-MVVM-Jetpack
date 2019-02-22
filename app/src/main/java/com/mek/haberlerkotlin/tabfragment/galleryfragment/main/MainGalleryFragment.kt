package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.main_gallery_fragment.*
import javax.inject.Inject

class MainGalleryFragment : Fragment() {

    companion object {
        fun newInstance() = MainGalleryFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.getAppComponent(context).inject(this)
    }

    @Inject lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainGalleryVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(MainGalleryVM::class.java)
        observeViewModel()

        rv_gallery.layoutManager = GridLayoutManager(context,2)
        rv_gallery.adapter = MainGalleryAdapter(this,viewModel)

    }

    private fun observeViewModel() {

        viewModel.getLoading().observe(this, Observer { loading ->
            progressBar.visibility = loading
        })
    }

}
