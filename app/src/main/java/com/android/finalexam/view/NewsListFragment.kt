package com.android.finalexam.view

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.android.finalexam.R
import retrofit2.Retrofit

class NewsListFragment : Fragment() {
    val args: NewsListFragmentArgs by navArgs()

    var list = mutableListOf<News>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        val adapter = Adapter()
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

        val apiService = retrofit.create(ApiService::class.java)
        apiService.getArticles(catName, countryName).enqueue(object : Callback<List<Article>> {
            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                Log.e("Error", t.message!!)
            }

            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                Log.e("Response size: ", response.body()!!.size.toString() + "")
                list.addAll(response.body()!!)
                adapter.setData(list)
            }
        })


        return view
    }

}