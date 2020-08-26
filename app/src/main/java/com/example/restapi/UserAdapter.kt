package com.example.restapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.pojo.User
import kotlinx.android.synthetic.main.users_list_item.view.*

class UserAdapter(var mdataSet: List<User>): RecyclerView.Adapter<UserAdapter.userHolder>() {

    class userHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTv= itemView.name
        val emailTv= itemView.email
        val phoneTv= itemView.phone
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
    return userHolder(view)
    }

    override fun onBindViewHolder(holder: userHolder, position: Int) {
        val user = mdataSet[position]
        holder.nameTv.text = user.name
        holder.emailTv.text = user.email
        holder.phoneTv.text = user.phone
    }
    override fun getItemCount(): Int {
       return mdataSet.size
    }
}