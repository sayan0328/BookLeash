package com.gc.bookleash.di

import com.gc.bookleash.viewmodels.AuthViewModel
import com.gc.bookleash.viewmodels.BooksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AuthViewModel(get(), androidContext()) }
    single { BooksViewModel() }
}