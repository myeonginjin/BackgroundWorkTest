package com.example.backgroundworktest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var mainFrame : LinearLayout


    private lateinit var image: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainFrame = LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
        }

        val btn: Button = Button(this)
        btn.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(Color.GRAY)
            text = "클릭"
        }
        mainFrame.addView(btn)


        val assetsManger = this.resources.assets
        val inputStream = assetsManger.open("image.png")
        image = BitmapFactory.decodeStream(inputStream)


        setContentView(mainFrame)

        btn.setOnClickListener {

            Toast.makeText(this, "tap", Toast.LENGTH_SHORT).show()

            GlobalScope.launch(Dispatchers.IO) {
                for (i in 0..4) {

                    try {
                        addImg()
                        delay(1000L)

                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

        private suspend fun addImg () = withContext(Dispatchers.Main) {

            try {
                val imageView = ImageView(this@MainActivity)
                imageView.setImageBitmap(image)
                mainFrame.addView(imageView)



            }catch(e : Exception) {
                e.printStackTrace()
            }
        }

}




