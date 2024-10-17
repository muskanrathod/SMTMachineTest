package com.baseproject.base.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.baseproject.R
import com.baseproject.base.activity.BaseActivity
import com.baseproject.base.viewModel.BaseViewModel
import com.baseproject.ui.dialog.DialogProgress

abstract class BaseFragment<VM : BaseViewModel, T : ViewDataBinding> : Fragment() {

    protected var TAG: String = this.javaClass.simpleName
    private var mActivity: BaseActivity<VM, T>? = null
    lateinit var mContext: Context

    open var dialogProgress: DialogProgress? = null

    var binder: T? = null
    private lateinit var mViewModel: VM

    private var rootView: View? = null
    private var isFragmentLoaded = false

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun getViewModel(): VM

    fun getDataBinder(): T {
        return this.binder!!
    }

    override fun onAttach(context: Context) {
        try {
            super.onAttach(context)
            if (context is BaseActivity<*, *>) {
                val activity = context as BaseActivity<VM, T>?
                this.mActivity = activity

                mContext = context
                //activity?.onFragmentAttached()
            }
        } catch (e: Throwable) {
            throw ClassCastException("$context must implement FragmentListener")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binder = DataBindingUtil.inflate(inflater, layoutRes, container, false)!!
        binder?.lifecycleOwner = viewLifecycleOwner
        rootView = binder?.root!!
        return rootView!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            mViewModel.let { vm ->
                binder?.let { binder ->
                    onReadyToRender(view, vm, binder, savedInstanceState)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }

    override fun onDetach() {
        super.onDetach()
        this.mActivity = null
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentLoaded = false
    }

    fun getBaseActivity(): BaseActivity<VM, T>? {
        return this.mActivity
    }

    protected abstract fun onReadyToRender(
        view: View,
        viewModel: VM,
        binder: T,
        savedInstanceState: Bundle?,
    )


    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    fun Int.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())

    fun setWhiteStatusBar() {
        requireActivity().window.statusBarColor = resources.getColor(R.color.white)

        when (resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                setAppearanceLightStatusBars(true)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                setAppearanceLightStatusBars(true)
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    @SuppressLint("NewApi")
    fun setAppearanceLightStatusBars(isLight: Boolean) {
        if (isLight) {
            requireActivity().window.insetsController!!.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            requireActivity().window.insetsController!!.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }

    fun setGreenStatusBar() {
        requireActivity().window.statusBarColor = resources.getColor(R.color.green)
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun showProgressDialog(context: Context) {
        try {
            if (dialogProgress != null && dialogProgress!!.isShowing) {
                dialogProgress!!.dismiss()
            }
            dialogProgress = DialogProgress(context)
            dialogProgress!!.show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    open fun hideProgressDialog() {
        try {
            if (dialogProgress != null && dialogProgress!!.isShowing)
                dialogProgress!!.dismiss()
        } catch (e: java.lang.Exception) {

        }
    }

}
