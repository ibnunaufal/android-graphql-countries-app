package com.bob.graphqlcountriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.bob.graphqlcountriesapp.data.ApolloCountryClient
import com.bob.graphqlcountriesapp.domain.CountryClient
import com.bob.graphqlcountriesapp.domain.GetAllCountriesUseCase
import com.bob.graphqlcountriesapp.domain.GetDetailedCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ApolloClient similar with retrofit
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient{
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetAllCountryUseCase(countryClient: CountryClient): GetAllCountriesUseCase{
        return GetAllCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideGetDetailedCountryUseCase(countryClient: CountryClient): GetDetailedCountryUseCase{
        return GetDetailedCountryUseCase(countryClient)
    }
}