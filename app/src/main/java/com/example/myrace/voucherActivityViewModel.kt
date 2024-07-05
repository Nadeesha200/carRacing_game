package com.example.myrace

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class voucherActivityViewModel : ViewModel() {

    //var count = 0
    var count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun updateCount() {
        //++count
        count.value = (count.value)?.plus(1)
    }

}
