package com.alfonsocastro.mayannumbersconverter.ui.mayan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfonsocastro.mayannumbersconverter.utils.MayanConverter

class ToMayanViewModel : ViewModel() {

    private var mayanNumberMutableLiveData = MutableLiveData("")
    private var decimalNumberMutableLiveData = MutableLiveData(0)
    val mayanNumberLiveData: LiveData<String> = mayanNumberMutableLiveData

    fun convert(number: Int) {
        decimalNumberMutableLiveData.value = number
        mayanNumberMutableLiveData.value = MayanConverter.convertToBase20(decimalNumberMutableLiveData.value ?: 0)
    }
}