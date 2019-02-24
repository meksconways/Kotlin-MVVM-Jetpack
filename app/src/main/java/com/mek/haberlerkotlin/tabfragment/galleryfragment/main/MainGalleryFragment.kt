package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.service.voice.VoiceInteractionService
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.utils.Helper
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.main_gallery_fragment.*
import javax.inject.Inject

class MainGalleryFragment : Fragment() {

    companion object {
        fun newInstance(type: String): MainGalleryFragment {
            val fragment = MainGalleryFragment()
            val args = Bundle()
            args.putString("type", type)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.getAppComponent(context).inject(this)
    }

    @Inject lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainGalleryVM
    private lateinit var mainViewModel: MainActivityVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_gallery_fragment, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!,factory).get(MainGalleryVM::class.java)
        observeViewModel()
        val s = arguments?.getString("type")
        viewModel.setNewsType(s!!)
        rv_gallery.layoutManager = GridLayoutManager(context,2)
        rv_gallery.adapter = MainGalleryAdapter(this,viewModel)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)

        mainViewModel.setHasBackButton(false)

    }

    private fun observeViewModel() {
        viewModel.getNewsType().observe(this, Observer { type ->
            viewModel.fetchNews()
            if (type == "tümü"){
                mainViewModel.setTitle("Galeri Haberleri - ${type.capitalize()}")
            }else{
                mainViewModel.setTitle("Galeri Haberleri - ${Helper.pathParse(type).capitalize()}")
            }

        })
        viewModel.getLoading().observe(this, Observer { loading ->
            progressBar.visibility = loading
            if (progressBar.isGone){
                rv_gallery.visibility = View.VISIBLE
            }else{
                rv_gallery.visibility = View.GONE
            }
        })
    }

}
