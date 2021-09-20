package com.test.marvelheroes.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.marvelheroes.common.view.BaseFragment
import com.marvelheroes.common.view.decoration.MarginItemDecoration
import com.marvelheroes.common.view.utils.viewModel
import com.test.marvelheroes.R
import com.test.marvelheroes.databinding.MarvelEmptyListBinding
import com.test.marvelheroes.databinding.MarvelHeroListFragmentBinding
import com.test.marvelheroes.di.MainComponent
import com.test.marvelheroes.di.MainInjection
import com.test.marvelheroes.view.adapter.MarvelHeroListAdapter
import com.test.marvelheroes.view.adapter.OnMarvelClickListener
import com.test.marvelheroes.view.viewmodel.MarvelViewModel

class MarvelHeroListFragment : BaseFragment<MarvelHeroListFragmentBinding>(),
    OnMarvelClickListener {

    private lateinit var mainComponent: MainComponent
    private val marvelViewModel: MarvelViewModel by viewModel { mainComponent.marvelViewModel }
    private var emptyBinding: MarvelEmptyListBinding? = null
    private val adapter: MarvelHeroListAdapter by lazy {
        MarvelHeroListAdapter(this)
    }

    override fun createDaggerComponent() {
        mainComponent = (activity as MainInjection).getMainComponent()
        mainComponent.inject(this)
    }

    override fun getContentView(inflater: LayoutInflater): View {
        binding = MarvelHeroListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun getEmptyView(inflater: LayoutInflater): View? {
        emptyBinding = MarvelEmptyListBinding.inflate(inflater)
        return emptyBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initViews()
        callService()
        setRetryClickListener {
          callService()
        }
    }

    private fun callService() {
        marvelViewModel.getMarvelHeroes()
    }

    private fun initViews() {
        binding.characterList.adapter = adapter
        binding.characterList.addItemDecoration(
            MarginItemDecoration(
                middleSpace = resources.getDimension(R.dimen.small_space).toInt()
            )
        )
    }

    private fun initObservables() {
        marvelViewModel.characterList.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                showEmpty()
            } else {
                adapter.setList(it)
                showContent()
            }
        })

        marvelViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            }
        })

        marvelViewModel.error.observe(viewLifecycleOwner, {
            showRetry()
        })
    }

    override fun onGoToDetails(marvelId: Int) {
        findNavController().navigate(
            MarvelHeroListFragmentDirections.actionMarvelHeroListFragmentToMarvelHeroDetailFragment(
                marvelId
            )
        )
    }
}