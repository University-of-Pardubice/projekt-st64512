package com.example.bmta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta.R
import com.google.gson.Gson
import java.io.File

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
        val gson = Gson()
        val file = File(this.getExternalFilesDir(null), "scores.json")
        var intArray = arrayOf<Int>()
        if (file.exists()) {
            val jsonString = file.readText()
            intArray = gson.fromJson(jsonString, Array<Int>::class.java)

        }
        return intArray
    }
}