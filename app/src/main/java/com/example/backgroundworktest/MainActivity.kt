package com.example.backgroundworktest

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

class MainActivity : AppCompatActivity() {

    private lateinit var mainFrame : LinearLayout
    private lateinit var imgFrame : LinearLayout





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        mainFrame =  LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity  = Gravity.CENTER
            setBackgroundColor(Color.BLACK)
        }

        imgFrame = LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(Color.WHITE)
        }

        mainFrame.addView(imgFrame)


        val btn : Button = Button(this)
        btn.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(Color.GRAY)
            text = "클릭"
        }
        mainFrame.addView(btn)



        setContentView(mainFrame)

        btn.setOnClickListener{

            Toast.makeText(this, "tap",Toast.LENGTH_SHORT).show()

            GlobalScope.launch(Dispatchers.Main) {
                for (i in 0..4) {

                    try {
                        val view : ImageView = ImgProcessing.addImg(this@MainActivity)
                        imgFrame.addView(view)
                        delay(1000L)

                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }





}