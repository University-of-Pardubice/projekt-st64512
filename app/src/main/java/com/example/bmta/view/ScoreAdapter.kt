package com.example.bmta.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta.R

class ScoreAdapter(val scoreList: Array<Int>) : RecyclerView.Adapter<ScoreHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder {
        return ScoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.score_row, parent, false))
    }

    override fun onBindViewHolder(holder: ScoreHolder, position: Int) {
        holder.setScoreData(scoreList[position])
    }

    override fun getItemCount(): Int {
        return scoreList.size
    }
}