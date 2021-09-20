package com.test.marvelheroes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.marvelheroes.common.utils.getLandscapeImageURL
import com.marvelheroes.common.view.BaseFragment
import com.marvelheroes.common.view.utils.viewModel
import com.test.marvelheroes.R
import com.test.marvelheroes.databinding.MarvelHeroDetailFragmentBinding
import com.test.marvelheroes.di.MainComponent
import com.test.marvelheroes.di.MainInjection
import com.test.marvelheroes.view.adapter.MarvelComicAdapter
import com.test.marvelheroes.view.viewmodel.MarvelViewModel


class MarvelHeroDetailFragment : BaseFragment<MarvelHeroDetailFragmentBinding>() {

    private lateinit var mainComponent: MainComponent
    private val marvelViewModel: MarvelViewModel by viewModel { mainComponent.marvelViewModel }
    private val args: MarvelHeroDetailFragmentArgs by navArgs()
    private val comicAdapter: MarvelComicAdapter by lazy {
        MarvelComicAdapter()
    }


    override fun createDaggerComponent() {
        mainComponent = (activity as MainInjection).getMainComponent()
        mainComponent.inject(this)
    }

    override fun getContentView(inflater: LayoutInflater): View {
        binding = MarvelHeroDetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservables()
        setRetryClickListener {
            marvelViewModel.getMarvelHero(args.heroId)
        }
        marvelViewModel.getMarvelHero(args.heroId)

    }

    private fun initViews() {
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.comicList.adapter = comicAdapter
        binding.comicList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initObservables() {
        marvelViewModel.characterDetail.observe(viewLifecycleOwner, {
            activity?.title = it.characterName
            binding.heroName.text = it.characterName
            binding.heroDescription.text = it.characterDescription

            Glide.with(binding.root)
                .load(getLandscapeImageURL(it.thumbnail?.path, it.thumbnail?.extension))
                .placeholder(R.drawable.marvel_placeholder)
                .error(R.drawable.marvel_placeholder)
                .into(binding.heroDetailImage)

            val comics = it.comicList?.items?.map { comic ->
                comic.name
            }
            if (!comics.isNullOrEmpty()) {
                comicAdapter.setList(comics)
            } else {
                binding.comicTitle.visibility = View.GONE
                binding.comicList.visibility = View.GONE
            }

            showContent()
        })

        marvelViewModel.loading.observe(viewLifecycleOwner,
            {
                if (it) {
                    showLoading()
                }
            })

        marvelViewModel.error.observe(viewLifecycleOwner, {
            showRetry()
        })
    }
}