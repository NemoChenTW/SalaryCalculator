package com.nemo.salarycalculator.model.repository

import android.content.Context
import com.google.gson.Gson
import com.nemo.salarycalculator.model.data.entity.InsuranceList
import com.nemo.salarycalculator.model.repository.util.JsonUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HealthInsuranceRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonUtil: JsonUtil
): InsuranceRepository {

    override suspend fun getInsurances(): InsuranceList {
        return jsonUtil.getFromAsset(context, "healthInsurance.json", InsuranceList::class.java)
    }
}
