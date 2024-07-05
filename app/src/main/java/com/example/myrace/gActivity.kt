package com.example.myrace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class gActivity : AppCompatActivity(), GameTask {

    //Click feedback button and Go To feedback page
    private lateinit var FeedbackBtn: Button

    private lateinit var rootLayout: LinearLayout
    private lateinit var startBtn: Button
    private lateinit var mGameView: GameView
    private lateinit var score: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gactivity)

        //Click feedback button and Go To feedback page
        FeedbackBtn = findViewById(R.id.fBtn)

        startBtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        mGameView = GameView(this, this)


        //Click feedback button and Go To feedback page
        FeedbackBtn.setOnClickListener{
            val intent = Intent(applicationContext, gfeedbackActivity::class.java)
            startActivity(intent)
        }

        startBtn.setOnClickListener {
            mGameView.setBackgroundResource(R.drawable.road)
            rootLayout.addView(mGameView)
            startBtn.visibility = View.GONE
            score.visibility = View.GONE
        }
    }

    override fun closeGame(mScore: Int) {
        score.text = "Score : $mScore"
        rootLayout.removeView(mGameView)
        startBtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
    }
}
