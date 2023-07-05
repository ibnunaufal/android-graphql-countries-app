package com.bob.graphqlcountriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.bob.CountriesQuery
import com.bob.CountryQuery
import com.bob.graphqlcountriesapp.domain.CountryClient
import com.bob.graphqlcountriesapp.domain.DetailedCountry
import com.bob.graphqlcountriesapp.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
): CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getDetailedCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }

}