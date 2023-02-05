package com.example.bmta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.bmta.R
import com.example.bmta.viewmodel.Game
import com.example.bmta.viewmodel.GameFactory
import com.google.gson.Gson
import java.io.*

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
        val bestScoreTextView = findViewById<TextView>(R.id.txt_best_score)
        bestScoreTextView.text = "× " + bestScore.toString()
        val textView = findViewById<TextView>(R.id.txt_score)
        gameView.game = game
        gameView.txtViewScore = textView
        gameView.gameActivity = this
    }

    fun getBestScore(): Int {
        val gson = Gson()
        val file = File(this.getExternalFilesDir(null), "scores.json")
        var intArray = arrayOf<Int>()
        if (file.exists()) {
            val jsonString = file.readText()
            intArray = gson.fromJson(jsonString, Array<Int>::class.java)

        }
        if (intArray.isNotEmpty()) {
            var maxValue = intArray[0]
            for (value in intArray) {
                if (value > maxValue) {
                    maxValue = value
                }
            }
            return maxValue
        }
        return 0
    }

    fun endGame(endGameString: String) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("Konec hry")
            .setMessage(endGameString)
            .setPositiveButton("OK") { _, _ ->
                writeScoreResult()
                finish()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    fun writeScoreResult() {
        val gson = Gson()
        val jsonString: String = this.resources.openRawResource(R.raw.scores).use { it.reader().readText() }
        var intArray = gson.fromJson(jsonString, IntArray::class.java)
        intArray = intArray.plus(game.score)

        val file = File(this.getExternalFilesDir(null), "scores.json")
        file.writeText(gson.toJson(intArray))
    }

}