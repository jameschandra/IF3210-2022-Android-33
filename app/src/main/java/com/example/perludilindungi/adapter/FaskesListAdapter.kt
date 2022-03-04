package com.example.perludilindungi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.models.FaskesData
import kotlinx.android.synthetic.main.recycler_row_faskeslist.view.*

class FaskesListAdapter : RecyclerView.Adapter<FaskesListAdapter.FaskesListViewHolder>() {
    private var faskesList = emptyList<FaskesData>()
    private lateinit var clickListener: onClickRowListener

    interface onClickRowListener {
        fun onClickRowAt(position: Int)
    }

    fun setOnClickRowListener(clickListener: onClickRowListener) {
        this.clickListener = clickListener
    }

    inner class FaskesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        init {
            itemView.setOnClickListener { view ->
                clickListener.onClickRowAt(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaskesListViewHolder {
        return FaskesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_row_faskeslist, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (faskesList.size < 5) {
            faskesList.size
        } else {
            5
        }
    }

    override fun onBindViewHolder(holder: FaskesListViewHolder, position: Int) {
        Log.d("FASKES", "onbind faskes")

        val currentItem = faskesList[position]
        holder.itemView.faskeslist_name.text = currentItem.nama
        holder.itemView.faskeslist_category.text = currentItem.jenis_faskes
        holder.itemView.faskeslist_address.text = currentItem.alamat
        holder.itemView.faskeslist_code.text = currentItem.kode
        holder.itemView.faskeslist_phone.text = currentItem.telp

        // TODO: add navigation to faskes detail and pass data
    }

    fun setData(faskesList: List<FaskesData>) {
        Log.d("FASKES", "set data faskes")
        this.faskesList = faskesList
        notifyDataSetChanged()
    }
}