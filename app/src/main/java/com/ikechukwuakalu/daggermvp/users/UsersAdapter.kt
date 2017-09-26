package com.ikechukwuakalu.daggermvp.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ikechukwuakalu.daggermvp.R
import com.ikechukwuakalu.daggermvp.data.models.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*

class UsersAdapter(var users: List<User>, var clickListener: View.OnClickListener)
    : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(){

    override fun onBindViewHolder(holder: UsersViewHolder?, position: Int) {
        holder?.bindTo(users[position])
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
                LayoutInflater.from(parent?.context).inflate(R.layout.user_item, parent, false)
        )
    }

    inner class UsersViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
        private val username : TextView = v.username
        private val avatar : ImageView = v.avatarImage
        private var url : TextView = v.url

        init {
            v.setOnClickListener(clickListener)
        }

        fun bindTo(user: User) {
            v.tag = user.login
            username.text = user.login
            url.text = user.url
            avatar.loadUrl(user.avatar_url)
        }

        private fun ImageView.loadUrl(imageUrl: String){
            Picasso.with(context).load(imageUrl).into(this)
        }
    }
}