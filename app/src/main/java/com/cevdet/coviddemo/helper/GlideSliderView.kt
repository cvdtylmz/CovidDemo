package com.cevdet.coviddemo.helper

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daimajia.slider.library.SliderTypes.DefaultSliderView

class GlideSliderView(context: Context) : DefaultSliderView (context) {

    override fun bindEventAndShow(v: View?, targetImageView: ImageView?) {
        targetImageView?.let {
            Glide.with(context)
                .load(url)
                .into(it)
        }
    }
}
