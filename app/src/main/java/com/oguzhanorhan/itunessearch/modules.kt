package com.oguzhanorhan.itunessearch

import com.oguzhanorhan.itunessearch.data.datasource.RemoteDataSource
import com.oguzhanorhan.itunessearch.data.repository.ItunesRepositoryImpl
import com.oguzhanorhan.itunessearch.datasource.remote.ItunesApi
import com.oguzhanorhan.itunessearch.datasource.remote.RemoteDataSourceImpl
import com.oguzhanorhan.itunessearch.datasource.remote.ResponseHandler
import com.oguzhanorhan.itunessearch.datasource.remote.createNetworkClient
import com.oguzhanorhan.itunessearch.domain.repository.ITunesRepository
import com.oguzhanorhan.itunessearch.domain.usecase.RetrieveFilterItemsUseCase
import com.oguzhanorhan.itunessearch.domain.usecase.SearchItemsUseCase
import com.oguzhanorhan.itunessearch.presentation.search.SearchVM
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.android.viewmodel.dsl.viewModel

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules( listOf(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule
    ))
}

val viewModelModule: Module = module {
    viewModel {
        SearchVM (
            searchItemsUseCase = get(),
            retrieveFilterItemsUseCase = get())
    }
}

val useCaseModule: Module = module {
    factory { RetrieveFilterItemsUseCase() }
    factory { SearchItemsUseCase(itunesRepository = get()) }
}

val repositoryModule: Module = module {
    single { ItunesRepositoryImpl( remoteDataSource = get(), responseHandler = get()) as ITunesRepository }
}

val dataSourceModule: Module = module {
    single { RemoteDataSourceImpl(api = itunesApi) as RemoteDataSource }
}

val networkModule: Module = module {
    single { itunesApi }
    single { ResponseHandler() }
}


private const val BASE_URL = "https://itunes.apple.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL)

private val itunesApi: ItunesApi = retrofit.create(ItunesApi::class.java)