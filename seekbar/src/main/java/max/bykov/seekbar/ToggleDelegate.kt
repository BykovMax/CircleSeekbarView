package max.bykov.seekbar

import android.graphics.Point
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class ToggleDelegate(
    private val progressBarRadius: Float,
    private val toggleTouchRadius: Float
) {

    var toggleCenter: TogglePoint? = null
    private var isDownOnToggle = false

    fun setAngel(angel: Float, centerX: Float, centerY: Float) {
        toggleCenter = progressBarRadius.let {
            TogglePoint(
                x = it * sin(Math.toRadians(angel.toDouble())) + centerX,
                y = it * cos(Math.toRadians(CORRECT_HEIGHT_VALUE + angel.toDouble())) + centerY
            )
        }
    }

    fun filterToggleTouch(touch: Point, isMove: Boolean = false, isDown: Boolean = false): Point? {
        return when {
            isDownOnToggle.not() && isDown -> {
                isDownOnToggle = isTouchOnToggle(touch)
                if (isDownOnToggle) touch else null
            }
            isDownOnToggle && isMove -> {
                touch
            }
            else -> {
                isDownOnToggle = false
                null
            }
        }
    }

    private fun isTouchOnToggle(touch: Point): Boolean {
        return toggleCenter?.let {
            (touch.x - it.x).pow(TWO) + (touch.y - it.y).pow(TWO) <= toggleTouchRadius.pow(TWO)
        } == true
    }

    companion object {
        private const val CORRECT_HEIGHT_VALUE = 180f
        private const val TWO = 2
    }
}

class TogglePoint(val x: Double, val y: Double)
