package com.marvelheroes.common.view.utils

import android.widget.ViewFlipper
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun ViewFlipper.displayChild(child: Int) {
    if (child != displayedChild) {
        displayedChild = child
    }
}

inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    crossinline factory: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            factory() as T
    }
}

inline fun <reified T : ViewModel> Fragment.viewModel(
    crossinline factory: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            factory() as T
    }
}

inline fun <reified T : ViewModel> Fragment.activityViewModel(
    crossinline factory: () -> T
) = activityViewModels<T> {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            factory() as T
    }
}