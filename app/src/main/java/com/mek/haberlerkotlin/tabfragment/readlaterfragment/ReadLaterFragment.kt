package com.mek.haberlerkotlin.tabfragment.readlaterfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mek.haberlerkotlin.R

class ReadLaterFragment : Fragment() {

    companion object {
        fun newInstance(i: Int) = ReadLaterFragment()
    }

    private lateinit var viewModel: ReadLaterVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.read_later_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReadLaterVM::class.java)

        // TODO: Use the ViewModel
    }

}
