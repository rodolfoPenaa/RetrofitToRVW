package com.example.restapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restapi.pojo.photos.Photo
import kotlinx.android.synthetic.main.photo_item_list.view.*

class photoAdapter (): RecyclerView.Adapter<photoAdapter.PhotoHolder>() {
    // 2.0
    private lateinit var mDATA: List<Photo>
    private fun updateList(myDt: List<Photo>){
        mDATA= myDt
        notifyDataSetChanged()
    }
    class PhotoHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val photos0 = itemView.apiPhoto
        val titlePhoto= itemView.title_photo
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_list, parent, false)
    return PhotoHolder(view)
    }
    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val photos1 = mDATA[position]
        holder.titlePhoto.text= photos1.title
        Glide.with(holder.itemView.context).load(photos1.url).into(holder.photos0)
    }
    override fun getItemCount(): Int {
        return mDATA.size
    }
}