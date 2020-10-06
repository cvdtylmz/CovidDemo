package com.cevdet.coviddemo.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.cevdet.coviddemo.R
import kotlinx.android.synthetic.main.dialog_error_message.*

class DialogErrorMessage (
    context : Context,
    private var message: String) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_error_message)
        txtDialogMessage?.text = message
        btnDialogDismiss?.setOnClickListener { this.dismiss() }
    }
}


