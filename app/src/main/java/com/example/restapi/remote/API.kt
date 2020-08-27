package com.example.restapi.remote

import com.example.restapi.pojo.posts.Post
import com.example.restapi.pojo.users.User
import com.example.restapi.pojo.photos.Photo
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("/users")
    fun getAllUsers(): Call<List<User>>

    @POST("/posts")
    fun createPOST(@Body post: Post): Call<Post>

    @DELETE("/posts/{postId}")
    fun deletePost(@Path("postId") postId: Int?): Call<Void>

    @GET("/photos")
    fun getAllPhotos(): Call<List<Photo>>

}