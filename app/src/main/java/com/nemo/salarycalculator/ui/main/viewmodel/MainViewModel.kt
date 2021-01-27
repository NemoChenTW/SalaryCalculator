package com.nemo.salarycalculator.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.salarycalculator.model.repository.HealthInsuranceRepository
import com.nemo.salarycalculator.model.repository.LaborInsuranceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val healthInsuranceRepository: HealthInsuranceRepository,
    private val laborInsuranceRepository: LaborInsuranceRepository
) :
    ViewModel() {

    fun getHealthInsuranceList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = healthInsuranceRepository.getInsurances()
            list.forEach {
                println("${it.level}: ${it.burden}")
            }

        }
    }

    fun getLaborInsuranceList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = laborInsuranceRepository.getInsurances()
            list.forEach {
                println("${it.level}: ${it.burden}")
            }
        }
    }
}
