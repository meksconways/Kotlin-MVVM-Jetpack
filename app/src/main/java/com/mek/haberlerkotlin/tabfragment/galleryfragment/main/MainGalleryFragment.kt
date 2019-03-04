package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.gallerydetail.GalleryDetailVM
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryVM
import com.mek.haberlerkotlin.utils.Helper
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.main_gallery_fragment.*
import javax.inject.Inject

class MainGalleryFragment : Fragment(), GalleryNewsSelectedListener {

    override fun setSelectedImage(position: Int) {

    }


    override fun setSelectedNews(model: GalleryNewsModel) {
        val galleryDetailVM = ViewModelProviders.of(activity!!)[GalleryDetailVM::class.java]
        galleryDetailVM.setData(model)
        parentviewModel.setNavigateTo(true)
    }


//    companion object {
//        fun newInstance(type: String): MainGalleryFragment {
//            val fragment = MainGalleryFragment()
//            val args = Bundle()
//            args.putString("type", type)
//            fragment.arguments = args
//            return fragment
//        }
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().inject(this)
    }


    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainGalleryVM
    private lateinit var mainViewModel: MainActivityVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_gallery_fragment, container, false)
    }

    lateinit var parentviewModel: GalleryVM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentviewModel = ViewModelProviders.of(activity!!, factory).get(GalleryVM::class.java)
        viewModel = ViewModelProviders.of(activity!!, factory).get(MainGalleryVM::class.java)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)
        observeViewModel()
        rv_gallery.layoutManager = GridLayoutManager(context, 2)
        rv_gallery.adapter = MainGalleryAdapter(this, viewModel, this)
        progressBar.visibility = View.GONE

        mainViewModel.setHasBackButton(false)



    }

    private fun observeViewModel() {
        parentviewModel.getNewsType().observe(this, Observer { type ->
            viewModel.fetchNews()
            if (type == "tümü") {
                mainViewModel.setTitle("Galeri Haberleri - ${type.capitalize()}")
            } else {
                mainViewModel.setTitle("Galeri Haberleri - ${Helper.pathParse(type).capitalize()}")
            }

        })
        parentviewModel.getLoading().observe(this, Observer { loading ->

            if (loading == View.GONE) {
                rv_gallery.hideShimmerAdapter()
                rv_gallery.isClickable = false
            } else {
                rv_gallery.showShimmerAdapter()
                rv_gallery.isClickable = true
            }

        })

    }

}
