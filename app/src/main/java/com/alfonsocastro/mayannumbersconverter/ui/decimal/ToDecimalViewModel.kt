package com.alfonsocastro.mayannumbersconverter.ui.decimal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfonsocastro.mayannumbersconverter.utils.MayanConverter

class ToDecimalViewModel : ViewModel() {
    private var mayanStringMutableLiveData = MutableLiveData("")
    val mayanStringLiveData: LiveData<String> = mayanStringMutableLiveData

    private var decimalResultMutableLiveData = MutableLiveData(0)
    val decimalResultLiveData: LiveData<Int> = decimalResultMutableLiveData

    fun addNumber(number: Int) {
        mayanStringMutableLiveData.value += MayanConverter.convertToBase20(number)
    }

    fun deleteNumber() {
        mayanStringMutableLiveData.value = mayanStringMutableLiveData.value?.dropLast(1)
    }

    fun clearNumbers() {
        decimalResultMutableLiveData.value = 0
        mayanStringMutableLiveData.value = ""
    }

    fun convert() {
        decimalResultMutableLiveData.value = MayanConverter.convertToDecimal(mayanStringMutableLiveData.value ?: "")
    }
}