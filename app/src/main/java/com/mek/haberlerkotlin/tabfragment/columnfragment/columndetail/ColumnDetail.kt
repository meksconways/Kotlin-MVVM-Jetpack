package com.mek.haberlerkotlin.tabfragment.columnfragment.columndetail

import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.column_detail_fragment.*
import javax.inject.Inject


class ColumnDetail : Fragment() {


    private lateinit var viewModel: ColumnDetailViewModel
    private lateinit var mainViewModel: MainActivityVM

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
        return inflater.inflate(R.layout.column_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!,factory).get(ColumnDetailViewModel::class.java)
        viewModel.fetchData()
        viewModel.getLoading().observe(this, Observer { loading ->
            progressBar.visibility = loading
            if (loading == View.GONE){
                scrollView.visibility = View.VISIBLE
            }else{
                scrollView.visibility = View.GONE
            }
        })
        viewModel.getData().observe(this, Observer { data ->
            //scrollView.visibility = View.VISIBLE
            data?.let {
                txt_writer.text = it.name
                txt_titleColumn.text = it.title
                txt_newsDesc.text = it.desc
                if (it.files.isNotEmpty()){
                    img_writerImage.apply {
                        Glide.with(this)
                            .load(it.files[0].fileUrl)
                    }
                }else{
                    img_writerImage.visibility = View.GONE
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    txt_newsText.text = Html.fromHtml(it.newsText, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    txt_newsText.text = Html.fromHtml(it.newsText)
                }
            }


        })

        mainViewModel = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainViewModel.setBottomBarBehavior(false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainViewModel.setBottomBarBehavior(true)

    }



}
