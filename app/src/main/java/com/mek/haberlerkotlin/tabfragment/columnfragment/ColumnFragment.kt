package com.mek.haberlerkotlin.tabfragment.columnfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.utils.TITLE

class ColumnFragment : Fragment() {

    companion object {
        fun newInstance() = ColumnFragment()
    }

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
        TITLE = "Köşe Yazıları"
        // TODO: Use the ViewModel
    }

}
