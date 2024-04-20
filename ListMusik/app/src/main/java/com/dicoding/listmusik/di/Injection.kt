package com.dicoding.listmusik.di

import com.dicoding.listmusik.MusikRepository

object Injection {
    fun provideRepository(): MusikRepository {
        return MusikRepository.getInstance()
    }
}