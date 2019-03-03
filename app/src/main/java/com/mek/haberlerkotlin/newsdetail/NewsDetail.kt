package com.mek.haberlerkotlin.newsdetail

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.utils.Helper
import com.mek.haberlerkotlin.utils.fromChildFragment
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.news_detail_fragment.*
import javax.inject.Inject

class NewsDetail : Fragment() {


    private lateinit var viewModel: NewsDetailVM
    private lateinit var _viewModel: MainActivityVM
    lateinit var itemId: String
    @Inject
    lateinit var factory: ViewModelFactory


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().inject(this)
    }

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
        viewModel = ViewModelProviders.of(this, factory).get(NewsDetailVM::class.java)
        _viewModel = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        _viewModel.setBottomBarBehavior(false)
        _viewModel.setHasBackButton(true)
        _viewModel.setTitle("Haber Detay")
        viewModel.setNewsId(itemId)
        viewModel.fetchDetail()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getLoading().observe(this, Observer { loading ->
            if (loading) {
                progressBar.visibility = View.VISIBLE
                scrollView.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                scrollView.visibility = View.VISIBLE
            }
        })
        viewModel.getDetailData().observe(this, Observer { data ->
            data?.let {
                txt_title.text = it.title
                txt_path.text = Helper.pathParse(it.path)
                txt_time.text = Helper.dateParse(it.createdDate)
                img_news.apply {
                    Glide.with(this)
                        .load(it.files[0].fileUrl)
                        .apply(RequestOptions().override(800, 500))
                        .into(this)
                }
                txt_desc.text = it.desc
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    txt_newstext.text = Html.fromHtml(it.newsText, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    txt_newstext.text = Html.fromHtml(it.newsText)
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _viewModel.setBottomBarBehavior(true)
        if (fromChildFragment) fromChildFragment = false

    }
}

