package com.example.perludilindungi.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perludilindungi.adapter.NewsAdapter
import com.example.perludilindungi.databinding.FragmentNewsBinding
import com.example.perludilindungi.repository.Repository
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private var _binding: FragmentNewsBinding? = null

    private val newsAdapter by lazy { NewsAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = Repository()
        val viewModelFactory = NewsViewModelFactory(repository)

        newsViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        newsViewModel.getNews()
        newsViewModel.newsResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.results.let {
                    if (it != null) {
                        newsAdapter.setData(it)
                        newsAdapter.setOnClickRowListener(object: NewsAdapter.onClickRowListener {
                            override fun onClickRowAt(position: Int) {
                                // TODO: CHECK LATER
                                Navigation.findNavController(binding.root).navigate(NewsFragmentDirections.actionNavigationNewsToBeritaDetailFragment2(it[position].guid))
                            }
                        })
                    }
                }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT)
            }
        })

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    private fun setupRecyclerView() {
        recyclerView_news.layoutManager = LinearLayoutManager(context)
        recyclerView_news.adapter = newsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}