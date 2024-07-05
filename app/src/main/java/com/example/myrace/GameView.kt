package com.example.myrace

import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import java.util.*

class GameView(var c: Context, var gameTask: GameTask) : View(c) {

    private var currentLevel = 1
    private val levelThreshold = 7 // Score threshold to advance to the next level

    private var myPaint: Paint = Paint()
    private var speed = 1
    private var time = 0
    private var score = 0
    private var myCarPosition = 0
    private val otherCars = ArrayList<HashMap<String, Any>>()

    var viewWidth = 0
    var viewHeight = 0

    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight


        // Check if player reached the score threshold for the next level
        if (score >= currentLevel * levelThreshold) {
            currentLevel++
            increaseDifficulty() //difficulty for the next level
        }


        if (time % 700 < 10 + speed) {
            val map = HashMap<String, Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            otherCars.add(map)
        }
        time = time + 10 + speed
        val carWidth = viewWidth / 7
        val carHeight = carWidth + 10
        myPaint.style = Paint.Style.FILL
        val d = resources.getDrawable(R.drawable.car_red, null)

        d.setBounds(
            myCarPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight - 2 - carHeight,
            myCarPosition * viewWidth / 3 + viewWidth / 15 + carWidth - 25,
            viewHeight - 2
        )
        d.draw(canvas)

        myPaint.color = Color.GREEN
        var highScore = 0

        val iterator = otherCars.iterator()
        while (iterator.hasNext()) {
            try {
                val car = iterator.next()
                val carX = car["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var carY = time - car["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.car_yellow, null)

                d2.setBounds(
                    carX + 25, carY - carHeight, carX + carWidth - 25, carY

                )
                d2.draw(canvas)

                if (car["lane"] as Int == myCarPosition) {
                    if (carY > viewHeight - 2 - carHeight && carY < viewHeight - 2) {
                        gameTask.closeGame(score)
                        return
                    }
                }

                if (carY > viewHeight + carHeight) {
                    iterator.remove()
                    score++
                    speed = 1 + Math.abs(score / 8)
                    if (score > highScore) {
                        highScore = score
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        myPaint.color = Color.WHITE
        myPaint.textSize = 40f
        canvas.drawText("Score : $score", 80f, 80f, myPaint)
        canvas.drawText("Speed : $speed", 380f, 80f, myPaint)

        postInvalidateDelayed(10)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                val x1 = event.x
                if (x1 < viewWidth / 2) {
                    if (myCarPosition > 0) {
                        myCarPosition--
                    }
                }

                if (x1 > viewWidth / 2) {
                    if (myCarPosition < 2) {
                        myCarPosition++
                    }
                }
                invalidate()
            }

            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }

    private fun increaseDifficulty() {
        //parameters to increase difficulty, such as increasing speed
        speed++

        // Show an AlertDialog to notify the player
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Congratulations!")
        builder.setMessage("You have reached level $currentLevel.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
