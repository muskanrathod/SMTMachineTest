package com.baseproject.ui.authModel

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.baseproject.R
import com.baseproject.base.activity.BaseActivity
import com.baseproject.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<AuthViewModel, ActivityAuthBinding>() {
    override fun getViewModel(): AuthViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_auth

    override fun onReadyToRender(
        binder: ActivityAuthBinding,
        mViewModel: AuthViewModel,
        savedInstanceState: Bundle?
    ) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.auth_navigation_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.auth_navigation)

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }

}