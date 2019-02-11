package com.mek.haberlerkotlin.tabfragment.galleryfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mek.haberlerkotlin.R

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance(i: Int) = GalleryFragment()
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
        // TODO: Use the ViewModel
    }

}
