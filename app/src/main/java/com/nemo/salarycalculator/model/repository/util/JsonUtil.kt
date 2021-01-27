package com.nemo.salarycalculator.model.repository.util

import android.content.Context
import com.nemo.salarycalculator.model.data.entity.InsuranceList

interface JsonUtil {
    suspend fun <T: InsuranceList> getFromAsset(context: Context, file: String, clazz: Class<T>): T
}
