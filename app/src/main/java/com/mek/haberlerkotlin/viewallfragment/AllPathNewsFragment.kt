package com.mek.haberlerkotlin.viewallfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.homefragment.NewsSelectedListener
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.Helper
import com.mek.haberlerkotlin.utils.fromChildFragment
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.all_path_news_fragment.*
import javax.inject.Inject

class AllPathNewsFragment : Fragment(),NewsSelectedListener {




    override fun setSelectedNews(model: ListNewsModel) {


        fromChildFragment = true
        rv_viewAll.findNavController().navigate(
            R.id.go_to_newsDetail,
            bundleOf("model" to model.id)
        )

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
        mainViewModel.setBottomBarBehavior(false)

        rv_viewAll.layoutManager = LinearLayoutManager(context)
        rv_viewAll.adapter = AllPathNewsAdapter(this, viewModel,this)
        viewModel.fetchData()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainViewModel.setBottomBarBehavior(true)

    }

    private fun observeViewModel() {
        viewModel.getLoading().observe(this, Observer { loading ->

            if (loading){
                rv_viewAll.showShimmerAdapter()
            }else{
                rv_viewAll.hideShimmerAdapter()
            }


        })
        viewModel.getPathData().observe(this, Observer { path ->
            mainViewModel.setTitle(Helper.pathParse(path).capitalize()+" Haberleri")
        })
    }

}
