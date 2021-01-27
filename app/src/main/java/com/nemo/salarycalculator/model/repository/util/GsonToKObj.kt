package com.nemo.salarycalculator.model.repository.util

import android.content.Context
import com.google.gson.Gson
import com.nemo.salarycalculator.model.data.entity.InsuranceList
import javax.inject.Inject

class GsonToKObj @Inject constructor() : JsonUtil {
    override suspend fun <T : InsuranceList> getFromAsset(
        context: Context,
        file: String,
        clazz: Class<T>
    ): T {
        val jsonString = context.assets.open(file).bufferedReader().use {
            it.readText()
        }
        return Gson().fromJson(jsonString, clazz) as T
    }

}
