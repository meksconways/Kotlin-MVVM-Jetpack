package com.mek.haberlerkotlin.tabfragment.columnfragment.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.columnfragment.columndetail.ColumnDetailViewModel
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.column_fragment.*
import javax.inject.Inject

class ColumnFragment : Fragment(),ColumnSelectedListener {


    override fun setSelectedColumn(id: String) {
        val detailViewModel = ViewModelProviders.of(activity!!,factory).get(ColumnDetailViewModel::class.java)
        detailViewModel.setNewsId(id)
        rv_column.findNavController().navigate(R.id.go_to_columnDetail)
    }

    private lateinit var viewModel: ColumnVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().inject(this)
    }

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.column_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(ColumnVM::class.java)

        val mainViewModel = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainViewModel.setTitle("Köşe Yazıları")
        mainViewModel.setHasBackButton(false)

        rv_column.layoutManager = LinearLayoutManager(context)
        rv_column.adapter = ColumnAdapter(this, viewModel,this)

        viewModel.getLoading().observe(this, Observer { loading ->
            if (loading) {
                rv_column.showShimmerAdapter()
                rv_column.isClickable = false
            } else {
                rv_column.hideShimmerAdapter()
                rv_column.isClickable = true
            }
        })


    }

}
