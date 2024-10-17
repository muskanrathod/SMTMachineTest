package com.baseproject.ui.mainModel.home

import androidx.lifecycle.MutableLiveData
import com.baseproject.base.viewModel.BaseViewModel
import com.baseproject.data.AppDataManager
import com.baseproject.data.model.DataDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(appDataManager: AppDataManager) : BaseViewModel(appDataManager) {

    protected var compositeDisposable = CompositeDisposable()

    val successData = MutableLiveData<DataDTO>()
    val message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    var totalItems = 0
    
    fun getData(skip: Int, limit: Int) {
        loading.value = true

        var paramater = HashMap<String, Any>()
        paramater["skip"] = skip
        paramater["limit"] = limit

        compositeDisposable.add(
            appDataManager.getData(paramater)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({ response ->
                    loading.value = false
                    if (response.code() == 200) {

                        val data = response.body()

                        if (data != null){
                            totalItems = response.body()!!.total.toInt()
                            successData.value = response.body()
                            
                        }


                    } else {
                        val jsonObject = JSONObject(response.errorBody()!!.string())
                        val errorMessage: String = jsonObject.getString("message")
                        message.value = errorMessage
                    }
                }, {
                    loading.value = false
                    it.printStackTrace()
                })
        )
    }

}