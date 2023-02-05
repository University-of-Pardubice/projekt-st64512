package com.example.bmta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.bmta.R
import com.example.bmta.viewmodel.Game

class GameActivity : AppCompatActivity() {
    private lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val dm = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        Constants.setScreenWidth(dm.widthPixels)
        Constants.setScreenHeight(dm.heightPixels)

        setContentView(R.layout.activity_game)
    }
}