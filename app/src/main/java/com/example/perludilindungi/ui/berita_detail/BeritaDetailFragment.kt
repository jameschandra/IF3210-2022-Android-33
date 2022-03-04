package com.example.perludilindungi.ui.berita_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.NewsAdapter
import com.example.perludilindungi.databinding.FragmentBeritaDetailBinding
import com.example.perludilindungi.databinding.FragmentNewsBinding
import kotlinx.android.synthetic.main.fragment_berita_detail.*
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * A simple [Fragment] subclass.
 * Use the [BeritaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeritaDetailFragment : Fragment() {

    private var _binding: FragmentBeritaDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val args: BeritaDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBeritaDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    private fun setupView() {
        url_berita.text = args.url
        webView_news.webViewClient = WebViewClient()
        webView_news.loadUrl(args.url)
        webView_news.settings.javaScriptEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}