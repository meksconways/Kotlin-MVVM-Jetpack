package com.mek.haberlerkotlin.gallerydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.gallerydetail.galleryslider.GallerySliderVM
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.GalleryNewsSelectedListener
import kotlinx.android.synthetic.main.gallery_detail_fragment.*

class GalleryDetailFragment : Fragment(), GalleryNewsSelectedListener {


    override fun setSelectedImage(position: Int) {

        val sliderViewModel = ViewModelProviders.of(activity!!)[GallerySliderVM::class.java]
        sliderViewModel.setData(viewModel.data.value!!.files)
        sliderViewModel.setPosition(position)

        rv_galleryDetail.findNavController().navigate(
            R.id.go_to_gallerySlider
        )
    }


    override fun setSelectedNews(model: GalleryNewsModel) {

    }

    private lateinit var viewModel: GalleryDetailVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivityVM = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainActivityVM.setBottomBarBehavior(false)
        mainActivityVM.setTitle("Galeri")
        mainActivityVM.setHasBackButton(true)

        viewModel = ViewModelProviders.of(activity!!).get(GalleryDetailVM::class.java)

        viewModel.getData().observe(this, Observer { data ->
            txt_title.text = data.title
        })

        rv_galleryDetail.layoutManager = GridLayoutManager(context, 3)
        rv_galleryDetail.adapter = GalleryDetailAdapter(this, viewModel, this)
    }

    lateinit var mainActivityVM: MainActivityVM

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivityVM.setBottomBarBehavior(true)

    }


}
