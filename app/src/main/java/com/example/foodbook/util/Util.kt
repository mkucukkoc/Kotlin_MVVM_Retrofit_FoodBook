package com.example.foodbook.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodbook.R


fun ImageView.gorselIndir(url:String?,placeholder:CircularProgressDrawable)
{
    var options=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
//CircularProgressDrawable kullanıyoruz bu bize görseller yüklenirken loading okunu çıkarıyor.
fun placeholderYap(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}

//xml tarafında kullanmak için bu fonksiyonu yazıyoruz.
@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView,url: String?)
{
    view.gorselIndir(url, placeholderYap(view.context))
}