package com.nemo.salarycalculator.model.repository

import com.nemo.salarycalculator.model.data.entity.InsuranceList

interface InsuranceRepository {
    suspend fun getInsurances(): InsuranceList
}
