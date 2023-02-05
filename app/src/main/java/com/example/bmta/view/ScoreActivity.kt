package com.example.bmta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta.R
import com.google.gson.Gson

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val backToMainActivityBtn = findViewById<Button>(R.id.back_to_menu_button)
        backToMainActivityBtn.setOnClickListener {
            finish()
        }

        val scoreRecycler = findViewById<RecyclerView>(R.id.score_recycler)
        scoreRecycler.layoutManager = LinearLayoutManager(this)
        scoreRecycler.adapter = ScoreAdapter(readScoresFromJson())
    }

    fun readScoresFromJson () : Array<Int> {
        var scoreArray: Array<Int> = arrayOf()
        try {
            val jsonString: String = this.resources.openRawResource(R.raw.scores).use { it.reader().readText() }
            val gson = Gson()
            scoreArray = gson.fromJson(jsonString, Array<Int>::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return scoreArray;
    }
}