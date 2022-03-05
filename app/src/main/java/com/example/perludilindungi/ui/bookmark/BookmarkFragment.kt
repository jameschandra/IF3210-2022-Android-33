package com.example.perludilindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perludilindungi.adapter.FaskesListAdapter
import com.example.perludilindungi.data.Faskes
import com.example.perludilindungi.data.FaskesDatabase
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.FaskesData
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import kotlinx.android.synthetic.main.fragment_faskes_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private var _binding: FragmentBookmarkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val database = FaskesDatabase.getDatabase(requireNotNull(this.activity).application).faskesDao
//
        val faskesListAdapter = FaskesListAdapter()
        var recyclerView = root.recyclerView_bookmark
        recyclerView.adapter = faskesListAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        bookmarkViewModel = BookmarkViewModel(database)
//        bookmarkViewModel.getBookmarks()
//
//        var flowListBookmark: List<FaskesData>
//
//        runBlocking(Dispatchers.IO) {
//            flowListBookmark = bookmarkViewModel.bookmarksList.first()
//        }
//
//        faskesListAdapter.setData(flowListBookmark)

        return root
    }

//    fun mapperComplicateToFaskesData(faskes: Faskes): FaskesData {
//        return FaskesData(
//            faskes.id,
//            faskes.kode,
//            faskes.nama,
//            "",
//            "",
//            faskes.alamat,
//            faskes.latitude,
//            faskes.telp,
//            faskes.jenis_faskes,
//            "",
//            faskes.status,
//            "",
//            ArrayList(),
//            "",
//        )
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}