package com.dicoding.listmusik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.listmusik.ui.screen.detail.DetailMusikViewModel
import com.dicoding.listmusik.ui.screen.favorite.FavoriteViewModel
import com.dicoding.listmusik.ui.screen.home.HomeViewModel


class ViewModelFactory(private val repository: MusikRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailMusikViewModel::class.java)) {
            return DetailMusikViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
