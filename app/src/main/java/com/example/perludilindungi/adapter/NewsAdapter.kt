package com.example.perludilindungi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perludilindungi.R
import com.example.perludilindungi.models.NewsData
import kotlinx.android.synthetic.main.recycler_row_news.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private var newsList = emptyList<NewsData>()
    private lateinit var clickListener: onClickRowListener

    interface onClickRowListener {
        fun onClickRowAt(position: Int)
    }

    fun setOnClickRowListener(clickListener: onClickRowListener) {
        this.clickListener = clickListener
    }

    inner class NewsViewHolder(itemView: View, clickListener: onClickRowListener): RecyclerView.ViewHolder(itemView)
    {
        init {
            itemView.setOnClickListener { view ->
                clickListener.onClickRowAt(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_news, parent, false), clickListener)
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