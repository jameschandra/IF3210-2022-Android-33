package com.example.perludilindungi.ui.faskes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.FaskesListAdapter
import com.example.perludilindungi.data.Faskes
import com.example.perludilindungi.models.FaskesData
import kotlinx.android.synthetic.main.fragment_faskeslist.*
import kotlinx.android.synthetic.main.fragment_faskeslist.view.*


class FaskesListFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var mFaskesListViewModel: FaskesListViewModel
    private lateinit var provinceArrayAdapter: ArrayAdapter<String>
    private lateinit var cityArrayAdapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FASKES", "oncreate faskes")
        val view = inflater.inflate(R.layout.fragment_faskeslist, container, false)

        val faskesListAdapter = FaskesListAdapter()
        var recyclerView = view.recyclerView_faskes
        recyclerView.adapter = faskesListAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        provinceArrayAdapter =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item
            )
        cityArrayAdapter =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item
            )

        view.spinner_province.adapter = provinceArrayAdapter
        view.spinner_province.onItemSelectedListener = this
        view.spinner_city.adapter = cityArrayAdapter
        view.spinner_city.onItemSelectedListener = this

        mFaskesListViewModel = ViewModelProvider(this).get(FaskesListViewModel::class.java)

        // TODO: add input for city and province
        Log.d("FASKES", "GETTING FASKES")
        mFaskesListViewModel.getProvince()

        mFaskesListViewModel.faskesList.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("FASKES", "response success")
                response.body()?.data.let {
                    if (it != null) {
                        Log.d("FASKES", "it not null")
                        faskesListAdapter.setData(it)
                        faskesListAdapter.setOnClickRowListener(object: FaskesListAdapter.onClickRowListener {
                            override fun onClickRowAt(position: Int) {
                                // TODO: CHECK LATER
                                Navigation.findNavController(view).navigate(FaskesListFragmentDirections.actionNavigationHomeToFaskesDetailFragment(it[position]))
                            }
                        })
                    } else {
                        Log.d("FASKES", "it null")
                    }
                }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT)
            }
        })

        mFaskesListViewModel.provinceList.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.results.let { it ->
                    if (it != null) {
                        val provinceNames: List<String> = it.map { data -> data.value }
                        populateProvinceSpinner(provinceNames)
                    }
                }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT)
            }
        })

        mFaskesListViewModel.cityList.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.results.let { it ->
                    if (it != null) {
                        val cityNames: List<String> = it.map { data -> data.value }
                        populateCitySpinner(cityNames)
                    }
                }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT)
            }
        })

        view.search_faskes_button.setOnClickListener {
            searchFaskes()
        }

        return view
    }

//    fun mapperToFaskes(faskesData: List<FaskesData>): List<Faskes> {
//        var faskesMap: List<Faskes> = faskesData.map {
//            Faskes(
//            it.id,
//            it.kode,
//            it.nama,
//            it.alamat,
//            it.latitude,
//            it.longitude,
//            it.telp,
//            it.jenis_faskes,
//            it.status,
//        ) }
//
//        return faskesMap
//    }

    private fun searchFaskes() {

        mFaskesListViewModel.getFaskesList(
            spinner_province.selectedItem.toString(),
            spinner_city.selectedItem.toString()
        )
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selected = p0?.getItemAtPosition(p2).toString()
        if (p0?.id == R.id.spinner_province) {
            mFaskesListViewModel.getCity(selected)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun populateProvinceSpinner(provinces: List<String>) {
        provinceArrayAdapter.clear()
        provinceArrayAdapter.addAll(provinces)
        provinceArrayAdapter.notifyDataSetChanged()
    }

    private fun populateCitySpinner(cities: List<String>) {
        cityArrayAdapter.clear()
        cityArrayAdapter.addAll(cities)
        cityArrayAdapter.notifyDataSetChanged()
    }
}