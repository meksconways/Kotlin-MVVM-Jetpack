package com.mek.haberlerkotlin.tabfragment.writersfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mek.haberlerkotlin.R

class WriterFragment : Fragment() {

    companion object {
        fun newInstance(i: Int) = WriterFragment()
    }

    private lateinit var viewModel: WriterVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.writer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WriterVM::class.java)
        // TODO: Use the ViewModel
    }

}
