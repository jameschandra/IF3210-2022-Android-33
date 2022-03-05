package com.example.perludilindungi.ui.faskes_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.perludilindungi.R
import com.example.perludilindungi.data.Faskes
import com.example.perludilindungi.data.FaskesDao
import com.example.perludilindungi.data.FaskesDatabase
import com.example.perludilindungi.databinding.FragmentFaskesDetailBinding
import com.example.perludilindungi.databinding.FragmentHomeBinding
import com.example.perludilindungi.models.FaskesData
import kotlinx.coroutines.launch
import javax.sql.CommonDataSource

class FaskesDetailFragment: Fragment() {

    private var _binding: FragmentFaskesDetailBinding? = null

    val args: FaskesDetailFragmentArgs by navArgs()
    private lateinit var faskes: FaskesData

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaskesDetailBinding.inflate(inflater, container, false)
        faskes = args.faskes

        val database = FaskesDatabase.getDatabase(requireNotNull(this.activity).application).faskesDao

        binding.namaFaskes.text = faskes.nama
        binding.alamat.text = faskes.alamat
        binding.kode.text = faskes.kode
        binding.noTelp.text = faskes.telp
        binding.status.text = faskes.status
        binding.tipeFaskes.text = faskes.jenis_faskes
        Log.d("STATUS", faskes.status)
        Log.d("TEST", "CONSOLE")
        if (faskes.status != "Siap Vaksinasi") {
            binding.statusImage.setImageResource(R.drawable.ic_tidak)
        }

        binding.bookmark.setOnClickListener { view: View ->
            lifecycleScope.launch{
                onClickBookmark(database)
            }
            view.findNavController()
                .navigate(FaskesDetailFragmentDirections.actionFaskesDetailFragmentToNavigationBookmark())
        }

        binding.unbookmark.setOnClickListener { view: View ->
            lifecycleScope.launch{
                onClickUnbookmark(database)
            }
            view.findNavController()
                .navigate(FaskesDetailFragmentDirections.actionFaskesDetailFragmentToNavigationBookmark())
        }

        binding.googleMap.setOnClickListener {
            gMapsIntent()
        }

        return binding.root
    }

    private fun gMapsIntent() {
        val uri = Uri.parse("geo:${faskes.latitude},${faskes.longitude}?q=${faskes.nama}")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    private suspend fun onClickBookmark(database: FaskesDao) {
        var faskesInsert = Faskes()

        faskesInsert.alamat = faskes.alamat
        faskesInsert.nama = faskes.nama
        faskesInsert.id = faskes.id
        faskesInsert.kode = faskes.kode
        faskesInsert.jenis_faskes = faskes.jenis_faskes
        faskesInsert.alamat = faskes.alamat
        faskesInsert.latitude = faskes.latitude
        faskesInsert.longitude = faskes.longitude
        faskesInsert.telp = faskes.telp
        faskesInsert.status = faskes.status

        database.insert(faskesInsert)
    }

    private suspend fun onClickUnbookmark(database: FaskesDao) {
        var faskesInsert = Faskes()

        faskesInsert.alamat = faskes.alamat
        faskesInsert.nama = faskes.nama
        faskesInsert.id = faskes.id
        faskesInsert.kode = faskes.kode
        faskesInsert.jenis_faskes = faskes.jenis_faskes
        faskesInsert.alamat = faskes.alamat
        faskesInsert.latitude = faskes.latitude
        faskesInsert.longitude = faskes.longitude
        faskesInsert.telp = faskes.telp
        faskesInsert.status = faskes.status

        database.delete(faskesInsert)
    }
}