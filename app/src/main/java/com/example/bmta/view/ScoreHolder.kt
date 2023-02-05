package com.example.bmta.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta.R

class ScoreHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var textScore : TextView?

    init {
        textScore = view.findViewById(R.id.row)
    }

    fun setScoreData(score: Int) {
        textScore?.text = score.toString()
    }
}