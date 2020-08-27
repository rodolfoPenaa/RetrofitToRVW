package com.example.restapi.pojo.posts

import com.google.gson.annotations.SerializedName

class Post(@SerializedName("user_id") val userId: Int,
                val id: Int,
                val title: String,
                val body: String)