package com.bob.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bob.graphqlcountriesapp.domain.DetailedCountry
import com.bob.graphqlcountriesapp.domain.GetAllCountriesUseCase
import com.bob.graphqlcountriesapp.domain.GetDetailedCountryUseCase
import com.bob.graphqlcountriesapp.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getDetailedCountryUseCase: GetDetailedCountryUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            _state.update { it.copy(
                countries = getAllCountriesUseCase.execute(),
                isLoading = false
            ) }
        }
    }

    fun selectCountry(code: String){
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = getDetailedCountryUseCase.execute(code)
            ) }
        }
    }

    fun dismissCountry(){
        _state.update { it.copy(
            selectedCountry = null
        ) }
    }


    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}

