package com.google.vitaly.appvk.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.vitaly.appvk.mvp.model.api.IDataSource
import com.google.vitaly.appvk.mvp.network.NetworkStatus
import com.google.vitaly.appvk.ui.App
import com.google.vitaly.appvk.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String {
        return "https://api.vk.com"
    }

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }


    @Provides
    fun networkStatus(app: App): NetworkStatus {
        return AndroidNetworkStatus(app)
    }

}