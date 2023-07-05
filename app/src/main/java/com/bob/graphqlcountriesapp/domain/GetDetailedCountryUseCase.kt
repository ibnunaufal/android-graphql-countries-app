package com.bob.graphqlcountriesapp.domain

class GetDetailedCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry?{
        return countryClient.getDetailedCountry(code)
    }
}

