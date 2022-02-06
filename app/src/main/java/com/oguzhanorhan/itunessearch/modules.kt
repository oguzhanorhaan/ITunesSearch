package com.oguzhanorhan.itunessearch

import com.oguzhanorhan.itunessearch.datasource.remote.ItunesApi
import com.oguzhanorhan.itunessearch.datasource.remote.ResponseHandler
import com.oguzhanorhan.itunessearch.datasource.remote.createNetworkClient
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

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

}

val useCaseModule: Module = module {
}

val repositoryModule: Module = module {
}

val dataSourceModule: Module = module {
}

val networkModule: Module = module {
    single { itunesApi }
    single { ResponseHandler() }
}


private const val BASE_URL = ""

private val retrofit: Retrofit = createNetworkClient(BASE_URL)

private val itunesApi: ItunesApi = retrofit.create(ItunesApi::class.java)