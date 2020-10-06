package com.cevdet.coviddemo.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cevdet.coviddemo.ui.dialog.DialogErrorMessage
import com.cevdet.coviddemo.ui.dialog.LoadingView

abstract class BaseActivity : AppCompatActivity(){
    private var loading: LoadingView? = null
    private var errorMessageDialog : DialogErrorMessage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
    }

    protected abstract fun getLayoutId () : Int

    protected abstract fun initViews ()

    protected abstract fun getContext () : Context

    fun showLoading() {
        loading = LoadingView(getContext())
        if (loading?.isShowing == false){
            loading?.show()
        }
    }
    fun hideLoading() {
        loading = if (loading == null) LoadingView(getContext())
        else loading
        loading?.dismiss()
    }
    fun showErrorDialog (message:String) {
        errorMessageDialog  = DialogErrorMessage(getContext(),message)
        if (!errorMessageDialog!!.isShowing){
            errorMessageDialog!!.show()
        }
    }
}
