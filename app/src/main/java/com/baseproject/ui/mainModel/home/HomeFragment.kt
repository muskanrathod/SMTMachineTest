package com.baseproject.ui.mainModel.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baseproject.R
import com.baseproject.base.fragment.BaseFragment
import com.baseproject.data.model.DataDTO
import com.baseproject.data.model.Post
import com.baseproject.databinding.FragmentHomeBinding
import com.baseproject.ui.mainModel.home.adapter.DataListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun getViewModel(): HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    private var mList = ArrayList<Post>()
    lateinit var dataListAdapter: DataListAdapter
    var skip = 0
    val limit = 20

    override fun onReadyToRender(
        view: View,
        viewModel: HomeViewModel,
        binder: FragmentHomeBinding,
        savedInstanceState: Bundle?
    ) {
        setGreenStatusBar()
        initView(binder)
        observers()
    }

    private fun initView(binder: FragmentHomeBinding) {
        getViewModel().getData(skip, limit)
        dataListAdapter = DataListAdapter(mList, requireContext())
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binder.rvList.layoutManager = layoutManager
        binder.rvList.adapter = dataListAdapter

        binder.rvList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (position + 1 == mList.size){
                    skip += 20
                    if(getViewModel().totalItems > skip){
                        getViewModel().getData(skip, limit)
                    }
                }
            }
        })
    }

    private fun observers() {
        getViewModel().successData.observe(viewLifecycleOwner) {
            if (it != null) {
                hideProgressDialog()
//                mList.clear()
                mList.addAll(it
                    .posts)
                dataListAdapter.notifyDataSetChanged()
                getViewModel().successData.value = null
            }
        }

        getViewModel().loading.observe(this) {
            if (it) {
                showProgressDialog(requireContext())
            } else {
                hideProgressDialog()
            }
        }
    }

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnectedOrConnecting == true
        }
    }

}