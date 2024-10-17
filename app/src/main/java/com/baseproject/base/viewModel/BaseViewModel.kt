package com.baseproject.base.viewModel

import androidx.lifecycle.ViewModel
import com.baseproject.data.AppDataManager

abstract class BaseViewModel(open val appDataManager: AppDataManager) : ViewModel() {

    protected var TAG: String = this.javaClass.simpleName

}