package com.do55anto5.movieapp.dependency_injection

import com.do55anto5.movieapp.data.api.ServiceApi
import com.do55anto5.movieapp.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideServiceApi(
        serviceProvider: ServiceProvider
    ): ServiceApi {
        return serviceProvider.createService(ServiceApi::class.java)
    }

    @Provides
    fun provideServiceProvider() = ServiceProvider()
}