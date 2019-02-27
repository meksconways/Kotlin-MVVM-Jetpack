package com.mek.haberlerkotlin.viewallfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.utils.Helper
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.all_path_news_fragment.*
import javax.inject.Inject

class AllPathNewsFragment : Fragment() {

    companion object {
        fun newInstance() = AllPathNewsFragment()
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: AllPathNewsVM
    private lateinit var mainViewModel: MainActivityVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.getAppComponent(context).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_path_news_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, factory).get(AllPathNewsVM::class.java)
        observeViewModel()
        mainViewModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)
        mainViewModel.setHasBackButton(true)

        rv_viewAll.layoutManager = LinearLayoutManager(context)
        rv_viewAll.adapter = AllPathNewsAdapter(this, viewModel)
        viewModel.fetchData()


    }

    private fun observeViewModel() {
        viewModel.getLoading().observe(this, Observer { loading ->

            if (loading == View.GONE) {
                rv_viewAll.visibility = View.VISIBLE
            } else {
                rv_viewAll.visibility = View.GONE
            }


            progressBar.visibility = loading
        })
        viewModel.getPathData().observe(this, Observer { path ->
            mainViewModel.setTitle("Hürriyet Haber - ${Helper.pathParse(path).capitalize()}")
        })
    }

}
