package com.example.restapi.pojo

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val postman = Postman()
    postman.connectAPI()
}
class Postman{
    fun connectAPI() {
        try {
            val url = URL("https://jsonplaceholder.typicode.com/users\n")
            val cnct = url.openConnection() as HttpURLConnection
            if (cnct.responseCode != 200) {
                throw RuntimeException("Failed: HTTP:" + cnct.responseCode)
            }
            val br = BufferedReader(InputStreamReader(cnct.inputStream))
            println("OUT from SERVER..")
            while (br.readLine() != null) {
                println(br.readLine())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}