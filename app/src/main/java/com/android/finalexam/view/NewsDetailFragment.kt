package com.android.finalexam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.android.finalexam.R
import com.android.finalexam.model.News

class NewsDetailFragment() : Fragment() {

    companion object {
        const val ARG_POST = "post"

        fun create(news: News) = NewsDetailFragment().apply {
            arguments = bundleOf(ARG_POST to news)
        }
    }

    private val news: News by lazy {
        arguments?.getParcelable<News>(ARG_POST) as News
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detailed_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        newsTitle.text = news.title
        newsContent.text = news.content
    }
}