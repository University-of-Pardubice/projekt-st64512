package com.example.bmta.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.bmta.R
import com.example.bmta.model.Apple
import com.example.bmta.model.Grass
import com.example.bmta.model.Snake
import com.example.bmta.viewmodel.Game
import kotlinx.coroutines.Runnable
import kotlin.math.min
import kotlin.random.Random

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    lateinit var game : Game
    lateinit var txtViewScore: TextView
    lateinit var gameActivity: GameActivity
    val h = 21
    val w = 12

    val arrGrass = ArrayList<Grass>()
    private var bmGrass1: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.grass)
    private var bmGrass2: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.grass03)

    private var bmSnake: Bitmap
    private var snake: Snake
    private val snakeInitLength = 4

    private var bmApple: Bitmap
    private var apple: Apple

    private var move : Boolean = false
    private var mx : Float = 0F
    private var my : Float = 0F

    private val runnable : Runnable

    companion object {
        @JvmField
        val sizeOfMap = min(Constants.SCREEN_WIDTH / 12, (Constants.SCREEN_HEIGHT  - 150) / 21)
    }

    init {
        this.bmGrass1 = Bitmap.createScaledBitmap(bmGrass1, sizeOfMap, sizeOfMap, true)
        this.bmGrass2 = Bitmap.createScaledBitmap(bmGrass2, sizeOfMap, sizeOfMap, true)
        this.bmSnake = BitmapFactory.decodeResource(this.resources, R.drawable.snake1)
        this.bmSnake = Bitmap.createScaledBitmap(bmSnake, 14 * sizeOfMap, sizeOfMap, true)
        this.bmApple = BitmapFactory.decodeResource(this.resources, R.drawable.apple)
        this.bmApple = Bitmap.createScaledBitmap(bmApple, sizeOfMap, sizeOfMap, true)
        for (i in 0 until this.h) {
            for (j in 0 until this.w) {
                if ( (i+j) % 2 == 0 ) {
                    arrGrass.add(
                        Grass(bmGrass1, j* sizeOfMap, i* sizeOfMap, sizeOfMap, sizeOfMap)
                    )
                } else {
                    arrGrass.add(
                        Grass(bmGrass2, j* sizeOfMap, i* sizeOfMap, sizeOfMap, sizeOfMap)
                    )
                }
            }
        }
        snake = Snake(bmSnake, arrGrass[126].x, arrGrass[126].y, snakeInitLength)
        apple = Apple(bmApple, arrGrass[randomApple()[0]].x,  arrGrass[randomApple()[0]].y)
        runnable = Runnable { invalidate() }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mx = event.x
                my = event.y
                move = true}
            MotionEvent.ACTION_MOVE -> {
                if (!move) {
                    mx = event.x
                    my = event.y
                    move = true
                }
                if (mx - event.x > 100*Constants.SCREEN_WIDTH/1080 && !snake.move_right) {
                    mx = event.x
                    my = event.y
                    snake.setMoveLeft(true)
                } else if ( event.x - mx > 100*Constants.SCREEN_WIDTH/1080 && !snake.move_left) {
                    mx = event.x
                    my = event.y
                    snake.setMoveRight(true)
                } else if (my - event.y > 100*Constants.SCREEN_WIDTH/1080 && !snake.move_bottom) {
                    mx = event.x
                    my = event.y
                    snake.setMoveTop(true)
                } else if (event.y - my > 100*Constants.SCREEN_WIDTH/1080 && !snake.move_top) {
                    mx = event.x
                    my = event.y
                    snake.setMoveBottom(true)
                }
            }
            MotionEvent.ACTION_UP -> {
                mx = 0F
                my = 0F
                move = false
            }
        }
        return true
    }

    fun isGameFinished(): String {
        if (isWon()) return "Vyhrál jsi!"
        if (isSnakePartEated()) return "Kousnul ses! Prohrál jsi!"
        if (isSnakeWallEated()) return "Bacha zeď! Prohrál jsi!"
        return ""
    }

    fun isWon() : Boolean {
        return (h * w) - (snakeInitLength + game.score) == 0
    }

    fun isSnakePartEated() : Boolean {
        for (i in 1 until snake.arrPartSnake.size) {
            if (snake.arrPartSnake[0].getrBody().intersect(snake.arrPartSnake[i].getrBody())) {
                return true
            }
        }
        return false
    }

    fun isSnakeWallEated() : Boolean {
        for (i in 0 until arrGrass.size) {
            if (snake.arrPartSnake[0].getrBody().intersect(arrGrass[i].r)) {
                return false
            }
        }
        return true
    }

    fun randomApple(): IntArray {
        var result = getRandomRectAndCoordinates()
        var rect = result.first
        var xy = result.second
        var check = true
        while (check) {
            check = false
            for (i in 0 until snake.arrPartSnake.size) {
                if (rect.intersect(snake.arrPartSnake[i].getrBody())) {
                    check = true
                    result = getRandomRectAndCoordinates()
                    rect = result.first
                    xy = result.second
                }
            }
        }
        return xy
    }

    fun getRandomRectAndCoordinates(): Pair<Rect, IntArray> {
        val xy = IntArray(2)
        val r = Random
        xy[0] = r.nextInt(arrGrass.size - 1)
        xy[1] = r.nextInt(arrGrass.size - 1)
        val rect = Rect(arrGrass[xy[0]].x, arrGrass[xy[1]].y,arrGrass[xy[0]].x + sizeOfMap, arrGrass[xy[1]].y + sizeOfMap)
        return Pair(rect, xy)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        for (i in 0 until arrGrass.size) {
            val grassBitmap = arrGrass[i]
            canvas?.drawBitmap(grassBitmap.bitmap, grassBitmap.x.toFloat(), grassBitmap.y.toFloat(), null)
        }
        snake.update()
        snake.draw(canvas)
        apple.draw(canvas)

        if (snake.arrPartSnake[0].getrBody().intersect(apple.r)) {
            snake.grow()
            refreshScore()
        }

        // when eat wall or self
        val isEndGame = isGameFinished()
        if (isEndGame.isNotEmpty()) {
            gameActivity.endGame(isEndGame)
        } else {
            // when eat apple
            if (snake.arrPartSnake[0].getrBody().intersect(apple.r)) {
                val applePosition = randomApple()
                apple.reset(arrGrass[applePosition[0]].x, arrGrass[applePosition[1]].y)
            }
            handler.postDelayed(runnable, 400)
        }
    }

    fun refreshScore() {
        game.score += 1
        txtViewScore.text = "× " + game.score.toString()
    }
}