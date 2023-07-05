package com.bob.graphqlcountriesapp.domain

import com.bob.CountryQuery

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getDetailedCountry(code: String): DetailedCountry?
}