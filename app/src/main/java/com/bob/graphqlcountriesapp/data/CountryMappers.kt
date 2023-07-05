package com.bob.graphqlcountriesapp.data

import com.bob.CountriesQuery
import com.bob.CountryQuery
import com.bob.graphqlcountriesapp.domain.DetailedCountry
import com.bob.graphqlcountriesapp.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code,
        name,
        emoji,
        capital ?: "No Capital",
        currency ?: "No Currency",
        languages.mapNotNull { it.name },
        continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code,
        name,
        emoji,
        capital ?: "No Capital"
    )
}





