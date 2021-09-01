package com.friendsflix.di

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.data.remote.datasource.MovieDataSourceImpl
import com.friendsflix.data.repository.HomeRepositoryImpl
import com.friendsflix.data.repository.LoginRepositoryImpl
import com.friendsflix.data.repository.MovieDetailRepositoryImpl
import com.friendsflix.data.repository.SignUpRepositoryImpl
import com.friendsflix.domain.repository.HomeRepository
import com.friendsflix.domain.repository.LoginRepository
import com.friendsflix.domain.repository.MovieDetailRepository
import com.friendsflix.domain.repository.SignUpRepository
import com.friendsflix.network.ApiKeyInterceptor
import com.friendsflix.presentation.home.HomeViewModel
import com.friendsflix.presentation.login.LoginViewModel
import com.friendsflix.presentation.moviedetail.MovieDetailViewModel
import com.friendsflix.presentation.signup.SignUpViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"
const val API_KEY_INTERCEPTOR = "API_KEY_INTERCEPTOR"
const val BASE_URL = "https://127.0.0.1/"

val dataModule = module {
    factory<MovieDataSource> { MovieDataSourceImpl(get()) }
    factory<HomeRepository> { HomeRepositoryImpl(get()) }
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<SignUpRepository> { SignUpRepositoryImpl(get()) }
    factory<MovieDetailRepository> { MovieDetailRepositoryImpl(get()) }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    single {
        val client = OkHttpClient.Builder()
        client.interceptors().add(get(named(LOGGING_INTERCEPTOR)))
        client.build()
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}

val presentationModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}