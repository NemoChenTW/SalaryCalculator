package com.nemo.salarycalculator.model.repository

import android.content.Context
import com.nemo.salarycalculator.model.data.entity.InsuranceList
import com.nemo.salarycalculator.model.repository.util.JsonUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LaborInsuranceRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonUtil: JsonUtil
) : InsuranceRepository {

    override suspend fun getInsurances(): InsuranceList {
        return jsonUtil.getFromAsset(context, "laborInsurance.json", InsuranceList::class.java)
    }

}
