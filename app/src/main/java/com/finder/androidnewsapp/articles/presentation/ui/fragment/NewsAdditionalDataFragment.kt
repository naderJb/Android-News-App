package com.finder.androidnewsapp.articles.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.databinding.FragmentNewsAdditionalDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsAdditionalDataFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNewsAdditionalDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var articleModel: ArticleModel

    private val navArgs: NewsAdditionalDataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsAdditionalDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        articleModel = navArgs.articleModel
        binding.article = articleModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}