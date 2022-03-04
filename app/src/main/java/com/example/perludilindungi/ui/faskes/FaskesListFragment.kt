package com.example.perludilindungi.ui.faskes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.FaskesListAdapter
import kotlinx.android.synthetic.main.fragment_faskeslist.view.*


class FaskesListFragment : Fragment() {
    private lateinit var mFaskesListViewModel: FaskesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FASKES","oncreate faskes")
        val view = inflater.inflate(R.layout.fragment_faskeslist, container, false)

        val faskesListAdapter = FaskesListAdapter()
        var recyclerView = view.recyclerView_faskes
        recyclerView.adapter = faskesListAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mFaskesListViewModel = ViewModelProvider(this).get(FaskesListViewModel::class.java)
        // TODO: add input for city and province
        Log.d("FASKES","GETTING FASKES")
        mFaskesListViewModel.getFaskesList("RIAU", "KAB. SIAK")
        mFaskesListViewModel.faskesList.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("FASKES","response success")
                response.body()?.data.let {
                    if (it != null) {
                        Log.d("FASKES","it not null")
                        faskesListAdapter.setData(it)
                    } else {
                        Log.d("FASKES","it null")
                    }
                }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT)
            }
        })

        return view
    }

}