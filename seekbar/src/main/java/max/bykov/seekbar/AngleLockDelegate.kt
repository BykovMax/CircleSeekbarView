package max.bykov.seekbar

import kotlin.math.abs

class AngleLockDelegate {
    var actualAngle = 0f
        private set

    private var ignoreCheckForFirstValue = true

    fun correct(inputAngel: Float): Float {
        val inputAngelCorrected = maxOf(MIN_ANGLE, minOf(inputAngel, MAX_ANGLE))
        if (ignoreCheckForFirstValue) {
            ignoreCheckForFirstValue = false
            actualAngle = inputAngelCorrected
            return actualAngle
        }
        val diff = abs(actualAngle - inputAngelCorrected)
        actualAngle = if (diff > LARGE_ANGLE_CHANGES) {
            if (actualAngle > LARGE_PART) MAX_ANGLE else MIN_ANGLE
        } else {
            inputAngelCorrected
        }
        return actualAngle
    }

    companion object {
        private const val MAX_ANGLE = 359f
        private const val MIN_ANGLE = 0f
        private const val LARGE_ANGLE_CHANGES = 20f
        private const val LARGE_PART = 179f
    }
}
