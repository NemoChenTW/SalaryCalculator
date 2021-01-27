package com.nemo.salarycalculator.model.repository

import android.content.Context
import com.google.gson.Gson
import com.nemo.salarycalculator.model.data.entity.InsuranceList
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HealthInsuranceRepository @Inject constructor(
    @ApplicationContext private val context: Context
): InsuranceRepository {
    override suspend fun getInsurances(): InsuranceList {
        val jsonString = context.assets.open("healthInsurance.json").bufferedReader().use {
            it.readText()
        }
        return Gson().fromJson(jsonString, InsuranceList::class.java)
    }
}
