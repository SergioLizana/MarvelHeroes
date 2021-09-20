package com.marvelheroes.common.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.animation.AnimationUtils
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.marvelheroes.common.R
import com.marvelheroes.common.databinding.FragmentBaseViewflipperBinding
import com.marvelheroes.common.databinding.ViewRetryBinding
import com.marvelheroes.common.view.utils.displayChild


private const val LOADING_INDEX = 0
private const val RETRY_INDEX = 1
private const val CONTENT_INDEX = 2
private const val EMPTY_INDEX = 3

abstract class BaseFragment<T> : Fragment() where T : ViewDataBinding {

    private var _binding: T? = null
    private var onActivityWasNotCreated = false

    protected var binding: T
        get() = requireBinding()
        set(value) {
            _binding = value
        }


    var mBinding: FragmentBaseViewflipperBinding? = null
    var retryBinding: ViewRetryBinding? = null

    private fun requireBinding(): T {
        _binding?.let {
            return it
        }
            ?: throw IllegalStateException("Binding is null, check the status of your fragment ${this::class}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentBaseViewflipperBinding.inflate(inflater)
        retryBinding = ViewRetryBinding.inflate(inflater)
        mBinding?.viewFlipper?.addView(getContentView(inflater), MATCH_PARENT, MATCH_PARENT)
        getEmptyView(inflater)?.let {
            mBinding?.viewFlipper?.addView(getEmptyView(inflater), MATCH_PARENT, MATCH_PARENT)
        }
        mBinding?.viewFlipper?.inAnimation =
            AnimationUtils.loadAnimation(context, R.anim.anim_pop_enter)
        mBinding?.viewFlipper?.outAnimation =
            AnimationUtils.loadAnimation(context, R.anim.anim_pop_exit)
        return mBinding?.root
    }

    abstract fun getContentView(inflater: LayoutInflater): View

    open fun getEmptyView(inflater: LayoutInflater): View? {
        return null
    }

    fun showLoading() {
        mBinding?.viewFlipper?.displayChild(LOADING_INDEX)
    }

    fun showRetry() {
        mBinding?.viewFlipper?.displayChild(RETRY_INDEX)
    }

    fun showContent() {
        mBinding?.viewFlipper?.displayChild(CONTENT_INDEX)
    }

    fun showEmpty() {
        mBinding?.viewFlipper?.displayChild(EMPTY_INDEX)
    }

    fun isLoading(): Boolean = mBinding?.viewFlipper?.displayedChild == LOADING_INDEX

    fun isRetry(): Boolean = mBinding?.viewFlipper?.displayedChild == RETRY_INDEX

    fun isContent(): Boolean = mBinding?.viewFlipper?.displayedChild == CONTENT_INDEX

    fun isEmpty(): Boolean = mBinding?.viewFlipper?.displayedChild == EMPTY_INDEX

    fun setRetryClickListener(listener: () -> Unit) {
        mBinding?.retryView?.retryButton?.setOnClickListener {
            listener()
        }
    }

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity?.lifecycle?.currentState == Lifecycle.State.CREATED) {
            createDaggerComponent()
        } else {
            onActivityWasNotCreated = true
        }
    }

    open fun createDaggerComponent() {
        // Do secure operations with the activity already created
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (onActivityWasNotCreated) {
            createDaggerComponent()
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}