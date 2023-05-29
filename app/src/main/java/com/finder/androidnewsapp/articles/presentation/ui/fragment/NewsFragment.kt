package com.finder.androidnewsapp.articles.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.finder.androidnewsapp.SharedViewModel
import com.finder.androidnewsapp.articles.data.model.CompaniesEnum
import com.finder.androidnewsapp.articles.presentation.ui.adapter.NewsAdapter
import com.finder.androidnewsapp.articles.presentation.viewmodel.NewsViewModel
import com.finder.androidnewsapp.base.extensions.gone
import com.finder.androidnewsapp.base.extensions.shouldShowFap
import com.finder.androidnewsapp.base.extensions.visible
import com.finder.androidnewsapp.base.models.DataStatus
import com.finder.androidnewsapp.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        implementListeners()
        initObservers()
    }


    private fun initViews() {
        binding.rvNews.adapter = newsAdapter
        newsViewModel.getNews(CompaniesEnum.GOOGLE.name)
    }

    private fun implementListeners() {
        binding.fap.setOnClickListener {
            binding.rvNews.smoothScrollToPosition(0)
        }
        binding.srlNews.setOnRefreshListener {
            newsViewModel.getNews(CompaniesEnum.GOOGLE.name)
        }
        newsAdapter.setOnItemClickListener {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToNewsAdditionalDataFragment(it)
            )
        }
        binding.rvNews.shouldShowFap {
            binding.fap.isVisible = it
        }
        binding.layoutHeader.root.setOnClickListener {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToSearchFragment()
            )
        }
        binding.layoutHeader.ivCancel.setOnClickListener {
            it.gone()
            binding.layoutHeader.tvSearch.text = null
            newsViewModel.getNews(CompaniesEnum.GOOGLE.name)
        }
    }

    private fun initObservers() {

        lifecycleScope.launch {
            newsViewModel.news.collect {
                newsAdapter.addData(it)
            }
        }
        lifecycleScope.launch {
            newsViewModel.status.collect { dataStatus ->
                when (dataStatus) {
                    is DataStatus.DataError -> showLoading(false)
                    DataStatus.DataLoaded -> showLoading(false)
                    DataStatus.DataLoading -> showLoading(true)
                }

            }
        }
        lifecycleScope.launch {
            sharedViewModel.search.collect {
                binding.layoutHeader.tvSearch.text = it.searchQuery
                binding.layoutHeader.ivCancel.visible()
                newsViewModel.getNewsByField(it)
            }
        }
    }

    private fun showLoading(isVisible: Boolean) =
        apply { binding.srlNews.isRefreshing = isVisible }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}