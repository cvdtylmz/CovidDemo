package com.cevdet.coviddemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cevdet.coviddemo.ui.dialog.DialogErrorMessage
import com.cevdet.coviddemo.ui.dialog.LoadingView

abstract class BaseActivity : AppCompatActivity() {
    private var loading: LoadingView? = null
    private var errorMessageDialog: DialogErrorMessage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViews()


    fun showLoading() {
        loading = LoadingView(this)
        if (loading?.isShowing == false) {
            loading?.show()
        }
    }

    fun hideLoading() {
        loading = if (loading == null) LoadingView(this)
        else loading
        loading?.dismiss()
    }

    fun showErrorDialog(message: String) {
        errorMessageDialog = DialogErrorMessage(this, message)
        if (!errorMessageDialog!!.isShowing) {
            errorMessageDialog!!.show()
        }
    }
}
