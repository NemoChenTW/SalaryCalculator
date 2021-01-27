package com.nemo.salarycalculator.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.salarycalculator.model.data.entity.InsuranceList
import com.nemo.salarycalculator.model.repository.HealthInsuranceRepository
import com.nemo.salarycalculator.model.repository.LaborInsuranceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainViewModel @ViewModelInject constructor(
    private val healthInsuranceRepository: HealthInsuranceRepository,
    private val laborInsuranceRepository: LaborInsuranceRepository
) :
    ViewModel() {

    val expectedActualSalary = MutableLiveData("100000")
    val multiplePerMonth = MutableLiveData("1.15")
    val result = MutableLiveData("")

    private val healthInsuranceList = MutableLiveData<InsuranceList>()
    private val laborInsuranceList = MutableLiveData<InsuranceList>()

    val isDataReady = MediatorLiveData<Boolean>().apply {
        fun checkDataReadyOrNot(): Boolean {
            return if (healthInsuranceList.value == null || laborInsuranceList.value == null) {
                false
            } else {
                healthInsuranceList.value!!.isNotEmpty() && laborInsuranceList.value!!.isNotEmpty()
            }
        }

        addSource(healthInsuranceList) {
            value = checkDataReadyOrNot()
        }
        addSource(laborInsuranceList) {
            value = checkDataReadyOrNot()
        }
    }

    fun calculate() {
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue("")
            val expectedActualSalaryInt = expectedActualSalary.value?.toInt() ?: 0

            findResult(expectedActualSalaryInt)
        }

    }

    private fun findResult(target: Int) {
        var currentResult = 0
        val multipleVal = multiplePerMonth.value!!.toDouble()
        var salary = target - 10000

        var resultString = ""
        while (currentResult < target) {
            currentResult = getActualSalary(salary)
            val monthExpect = getActualSalary((salary * multipleVal).roundToInt())
            val currentStr =
                "${getSalaryStr(salary)}->  ${getSalaryStr(currentResult)}    ${getSalaryStr(monthExpect)}   ${getSalaryStr(currentResult * 16)}  ${getSalaryStr(salary * 16)}\n"
            resultString = currentStr + resultString
            salary += 1000
        }
        resultString = "Salary   A-Salary  A-Expect  Y-Salary(A)\n$resultString"
        result.postValue(resultString)
    }

    private fun getSalaryStr(salary: Int): String {
        val str = salary.toString()
        return if (str.length > 6) {
            str.dropLast(4) + "w"
        } else {
            "${salary.toString().padStart(6, ' ')}"
        }
    }

    private fun getActualSalary(salary: Int): Int {
        return salary - getTax(salary) - getLaborInsuranceFee(salary) -
                getHealthInsuranceFee(salary, 3)
    }

    private fun getTax(salary: Int): Int {
        return (salary * 0.05).roundToInt()
    }

    private fun getLaborInsuranceFee(salary: Int): Int {
        return 1054
    }

    private fun getHealthInsuranceFee(salary: Int, numberOfInsurancePeople: Int): Int {
        return healthInsuranceList.value?.lastOrNull() {
            salary >= it.salary
        }!!.burden * numberOfInsurancePeople
    }

    fun queryData() {
        viewModelScope.launch(Dispatchers.IO) {
            queryHealthInsuranceList()
            queryLaborInsuranceList()
        }
    }

    private fun queryHealthInsuranceList() {
        viewModelScope.launch(Dispatchers.IO) {
            healthInsuranceList.postValue(healthInsuranceRepository.getInsurances())
        }
    }

    private fun queryLaborInsuranceList() {
        viewModelScope.launch(Dispatchers.IO) {
            laborInsuranceList.postValue(laborInsuranceRepository.getInsurances())
        }
    }
}
