package com.mek.haberlerkotlin.tabfragment.columnfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivityVM

class ColumnFragment : Fragment() {

    private lateinit var viewModel: ColumnVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.column_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ColumnVM::class.java)

        val mainViewModel = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainViewModel.setTitle("Köşe Yazıları")
        mainViewModel.setHasBackButton(false)
    }

}
