package com.example.bmta.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bmta.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newGameBtn = findViewById<Button>(R.id.new_game_btn)
        newGameBtn.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        val scoreBtn = findViewById<Button>(R.id.score_btn)
        scoreBtn.setOnClickListener {
            startActivity(Intent(this, ScoreActivity::class.java))
        }

        val endGameBtn = findViewById<Button>(R.id.end_game_btn)
        endGameBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        }
    }
}