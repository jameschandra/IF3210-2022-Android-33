package com.example.perludilindungi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perludilindungi.R
import com.example.perludilindungi.models.NewsData
import kotlinx.android.synthetic.main.recycler_row_news.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private var newsList = emptyList<NewsData>()

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_news, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.news_title.text = newsList[position].title
        holder.itemView.news_date.text = newsList[position].pubDate
        Glide.with(holder.itemView.context).load(newsList[position].enclosure._url).into(holder.itemView.imageView)
    }

    fun setData(newNewsList: ArrayList<NewsData>){
        newsList = newNewsList
        notifyDataSetChanged()
    }
}