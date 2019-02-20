package com.mek.haberlerkotlin.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivityVM

class NewsDetail : Fragment() {

    companion object {
        fun newInstance() = NewsDetail()
    }

    private lateinit var viewModel: NewsDetailVM
    private lateinit var _viewModel: MainActivityVM
    lateinit var itemId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.news_detail_fragment, container, false)

        itemId = arguments?.getString("model").toString()



        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsDetailVM::class.java)
        _viewModel = activity?.let { ViewModelProviders.of(it).get(MainActivityVM::class.java) }!!
        _viewModel.setBottomBarBehavior(false)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _viewModel.setBottomBarBehavior(true)
    }
}

