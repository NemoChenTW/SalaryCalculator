package com.nemo.salarycalculator.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nemo.salarycalculator.model.repository.HealthInsuranceRepository

class MainViewModel @ViewModelInject constructor(private val healthInsuranceRepository: HealthInsuranceRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}
