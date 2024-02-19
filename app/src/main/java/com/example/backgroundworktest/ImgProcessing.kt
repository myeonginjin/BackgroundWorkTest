

package com.example.backgroundworktest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.TimeUnit

object ImgProcessing {

    private lateinit var image: Bitmap
    fun addImg (context : Context) : ImageView{
        val imageView = ImageView(context)

        try {


            val assetsManger = context.resources.assets
            val inputStream =  assetsManger.open("image.png")
            image= BitmapFactory.decodeStream(inputStream)

            imageView.apply {
                layoutParams = LinearLayout.LayoutParams(
                    100,
                    100
                )
            }

            imageView.setImageBitmap(image)


            TimeUnit.SECONDS.sleep(1)

        }catch(e : Exception) {
            e.printStackTrace()
        }

        return imageView
    }
}