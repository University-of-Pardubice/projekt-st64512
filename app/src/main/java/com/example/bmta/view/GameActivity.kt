package com.example.bmta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bmta.R
import com.example.bmta.viewmodel.Game
import com.example.bmta.viewmodel.GameFactory
import com.google.gson.Gson

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

        val bestScore = getBestScore()
        val factory = GameFactory(bestScore)
        game = ViewModelProvider(this, factory).get(Game::class.java)

        val gameView = findViewById<GameView>(R.id.gv)
        val textView = findViewById<TextView>(R.id.txt_score)
        gameView.game = game
        gameView.txtViewScore = textView

    }

    fun getBestScore(): Int {
        val jsonString: String = this.resources.openRawResource(R.raw.scores).use { it.reader().readText() }

        try {
            val gson = Gson()
            val intArray = gson.fromJson(jsonString, IntArray::class.java)

            if (intArray != null && intArray.isNotEmpty()) {
                var maxValue = intArray[0]
                for (value in intArray) {
                    if (value > maxValue) {
                        maxValue = value
                    }
                }
                return maxValue
            }
        } catch (e: Exception) {
            println("Error while parsing JSON: ${e.message}")
        }
        return 0;
    }
}