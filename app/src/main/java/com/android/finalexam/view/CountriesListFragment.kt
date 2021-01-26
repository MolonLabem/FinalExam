package com.android.finalexam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.finalexam.R
import com.android.finalexam.adapter.ListAdapter
import com.android.finalexam.adapter.NewsAdapter
import com.android.finalexam.model.Country
import com.android.finalexam.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountriesListFragment: Fragment() {

    private var countriesAdapter: ListAdapter? = null
    private val countriesList: MutableList<Country> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_countries_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        NewsRepository.getNews(category, country)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    ListAdapter?.addItems(it)
                    NewsList.addAll(it)
                },
                {
                    // TODO handle error
                }
            )
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(
            NewsClickListener = {
                goToDetails(it)
            }
        )
        val manager = LinearLayoutManager(context)
        newsRecyclerView.apply {
            layoutManager = manager
            adapter = ListAdapter
        }
    }

    private fun goToDetailsUsingNavigation(post: Post) {
        // TODO handle navigation component
    }

    private fun goToDetails(News: News) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.mainActivity, DetailedInfo.create(post))
            ?.apply { addToBackStack(this::class.java.simpleName) }
            ?.commit()
    }

}