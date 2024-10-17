package com.baseproject.ui.authModel.splash

import com.baseproject.base.viewModel.BaseViewModel
import com.baseproject.data.AppDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(appDataManager: AppDataManager): BaseViewModel(appDataManager) {
}