package com.example.bmta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.bmta.view.Constants

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val dm = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        Constants.setScreenWidth(dm.widthPixels)
        Constants.setScreenHeight(dm.heightPixels)

        setContentView(R.layout.activity_main)
    }
}