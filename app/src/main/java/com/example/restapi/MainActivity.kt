package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.pojo.Post
import com.example.restapi.pojo.User
import com.example.restapi.remote.Api
import com.example.restapi.remote.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private var postsList = ArrayList<Post>()
    private var userList: ArrayList<User> = ArrayList()
    // private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadApiData()
        userAdapter = UserAdapter(userList)
        postsRecyclerView.adapter = userAdapter
    }
//                                         for charge data of response API
    private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                //userList = response.body() as ArrayList<User>
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
}