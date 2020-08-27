package com.example.restapi

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.restapi.pojo.posts.Post
import com.example.restapi.pojo.users.User
import com.example.restapi.pojo.photos.Photo
import com.example.restapi.remote.RetrofitClient
import kotlinx.android.synthetic.main.api_response_dialoge.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private var postsList = ArrayList<Post>()
    private var userList: ArrayList<User> = ArrayList()
    // private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var userAdapter: UserAdapter

    private var photoList: ArrayList<Photo> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //       loadApiData()
        //       userAdapter = UserAdapter(userList)
        //       postsRecyclerView.adapter = userAdapter
        //addtoAPI.setOnClickListener{
        //   showDialog()
        // }

        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPhotos()
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                response.body()?.map { photoList.add(it) }
                //val filterListPhto = photoList.
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
//                 for charge data of response API
    private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.map { userList.add(it) }
                userAdapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("FAIL", "chan")
                Toast.makeText(
                    applicationContext,
                    "Error $t", Toast.LENGTH_LONG
                ).show()
            }
        })
    }
    private fun makePost(post: Post) {
        val service = RetrofitClient.retrofitInstance()
        val call = service.createPOST(post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                when (response.code()) {
                    in 200..299 -> {
                        Toast.makeText(this@MainActivity, "Posting", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Don't Posted", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("FAIL", "chan")
                Toast.makeText(applicationContext, "Error $t", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.api_response_dialoge)
        dialog.send_post.setOnClickListener{
            val title = dialog.title_text.text.toString()
            val body = dialog.post_text.text.toString()
            val userId = dialog.auth_text.text.toString().toInt()
            val post  = Post(userId, 100,title,body)
            makePost(post)
            dialog.dismiss()
        }
        dialog.show()
    }

}

