package com.android.finalexam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.finalexam.R
import com.android.finalexam.adapter.ListAdapter
import com.android.finalexam.adapter.NewsAdapter
import com.android.finalexam.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountriesListFragment {

    private var countriesAdapter: ListAdapter? = null
    private val countriesList: MutableList<Country> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_countries_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        repository.getNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    jobAdapter?.addItems(it)
                    jobsList.addAll(it)
                },
                {
                    // TODO handle error
                }
            )
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(
            newsClickListener = {
                goToDetails(it)
            }
        )
        val manager = LinearLayoutManager(context)
        jobsRecyclerView.apply {
            layoutManager = manager
            adapter = jobAdapter
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