package com.baseproject.ui.mainModel

import com.baseproject.base.viewModel.BaseViewModel
import com.baseproject.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appDataManager: AppDataManager) :
    BaseViewModel(appDataManager) {
}