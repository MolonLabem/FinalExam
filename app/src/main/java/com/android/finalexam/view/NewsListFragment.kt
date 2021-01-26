package com.android.finalexam.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.finalexam.R
import com.android.finalexam.adapter.NewsAdapter
import com.android.finalexam.model.News
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListFragment : Fragment() {
    val args: NewsListFragmentArgs by navArgs()

    var list = mutableListOf<News>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        val adapter = NewsAdapter()
        val recyclerView = view.toDoRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://saurav.tech/NewsAPI/top-headlines/")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        val catName = args.category
        val countryName = args.country

        val apiService = retrofit.create(NewsApiService::class.java)
        apiService.getArticles(catName, countryName).enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Log.e("Error", t.message!!)
            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                Log.e("Response size: ", response.body()!!.size.toString() + "")
                list.addAll(response.body()!!.news)
                adapter.setData(list)
            }
        })


        return view
    }

}