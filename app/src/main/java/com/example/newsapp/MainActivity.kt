package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsapp.BuildConfig.NEWS_API_KEY
import com.example.newsapp.data.MyApi
import com.example.newsapp.models.SourcesResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://newsapi.org/v2/"
    private val TAG: String = "CHECK_RESPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getAllTopHeadlines()
    }

    private fun getAllTopHeadlines() {
        val customInterceptor = Interceptor { chain ->
            // Custom logic
            chain.proceed(chain.request())
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(customInterceptor)
            .build()

        val api = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getTopHeadlines(country = "us", apiKey = NEWS_API_KEY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    Log.d("Check the Response", response.message())
                    if (response.isSuccessful) {
                        val sources = response.body()?.sources
                        Log.d("We are the champions ", sources.toString())
                    } else {
                        Log.e("API ERROR", "Code: ${response.code()}, Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            })

    }
}