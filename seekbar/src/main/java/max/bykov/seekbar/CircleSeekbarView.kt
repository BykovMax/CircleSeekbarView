package max.bykov.seekbar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bykov.seekbar.R
import kotlin.math.atan2
import kotlin.math.max


class CircleSeekbarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var angleListener: ((Float) -> Unit)? = null
        set(value) {
            field = value
            value?.invoke(angleDelegate.actualAngle)
        }
    private val angleDelegate = AngleLockDelegate()
    private lateinit var toggleDelegate: ToggleDelegate
    private val foregroundStrokePaint: Paint by lazy(::createForegroundStrokePaint)
    private val backgroundStrokePaint: Paint by lazy(::createBackgroundStrokePaint)
    private val togglePaint: Paint by lazy(::createTogglePaint)
    private val mDrawRect = RectF()
    private var toggleRadius = TOGGLE_RADIUS_DEFAULT
    private var toggleShadowLength = TOGGLE_SHADOW_LENGTH_DEFAULT
    private var toggleAdditionTouchRadius = TOGGLE_ADDITION_TOUCH_RADIUS_DEFAULT
    private var progressBarRadius = 0f
    private var centerX = 0f
    private var centerY = 0f
    private var progressStrokeWidth = PROGRESS_STROKE_WIDTH_DEFAULT
    private var foregroundBarColor: Int = Color.YELLOW
    private var backgroundBarColor: Int = Color.WHITE
    private var toggleColor: Int = Color.WHITE

    init {
        if (attrs != null) {
            val typedArray =
                context.obtainStyledAttributes(
                    attrs,
                    R.styleable.CircleSeekbarView,
                    defStyleAttr,
                    0
                )
            try {
                foregroundBarColor = typedArray.getColor(
                    R.styleable.CircleSeekbarView_foregroundProgressColor,
                    foregroundBarColor
                )
                backgroundBarColor = typedArray.getColor(
                    R.styleable.CircleSeekbarView_backgroundProgressColor,
                    backgroundBarColor
                )
                toggleColor = typedArray.getColor(
                    R.styleable.CircleSeekbarView_toggleColor,
                    toggleColor
                )
                progressStrokeWidth =
                    typedArray.getDimensionPixelSize(
                        R.styleable.CircleSeekbarView_lineThickness,
                        progressStrokeWidth.toInt()
                    ).toFloat()
                toggleRadius =
                    typedArray.getDimensionPixelSize(
                        R.styleable.CircleSeekbarView_toggleRadius,
                        toggleRadius.toInt()
                    ).toFloat()
                toggleShadowLength =
                    typedArray.getDimensionPixelSize(
                        R.styleable.CircleSeekbarView_toggleShadow,
                        toggleShadowLength.toInt()
                    ).toFloat()
                angleDelegate.correct(
                    typedArray.getFloat(
                        R.styleable.CircleSeekbarView_toggleInitValue,
                        0f
                    )
                )
                toggleAdditionTouchRadius =
                    typedArray.getDimension(
                        R.styleable.CircleSeekbarView_toggleAdditionalTouchRadius,
                        toggleAdditionTouchRadius
                    )
            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun createForegroundStrokePaint(): Paint {
        return Paint().apply {
            style = Paint.Style.STROKE
            color = foregroundBarColor
            isAntiAlias = true
            strokeWidth = progressStrokeWidth
            strokeCap = Paint.Cap.ROUND
        }
    }

    private fun createBackgroundStrokePaint(): Paint {
        return Paint().apply {
            style = Paint.Style.STROKE
            color = backgroundBarColor
            isAntiAlias = true
            strokeWidth = progressStrokeWidth
        }
    }

    private fun createTogglePaint(): Paint {
        return Paint().apply {
            color = toggleColor
            style = Paint.Style.FILL
            isAntiAlias = true
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.P) {
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }
            setShadowLayer(toggleShadowLength, 0f, 0f, Color.GRAY);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            updateAngleByTouch(it)
        }
        return true
    }

    private fun updateAngleByTouch(event: MotionEvent) {
        val toggleTouch = toggleDelegate.filterToggleTouch(
            event.toPoint(),
            isMove = event.action == MotionEvent.ACTION_MOVE,
            isDown = event.action == MotionEvent.ACTION_DOWN
        )
        toggleTouch?.let { touch ->
            val inputAngel = getAngle(touch)
            angleDelegate.correct(inputAngel)
                .also { angel ->
                    toggleDelegate.setAngel(angel, centerX, centerY)
                    angleListener?.invoke(angel)
                    invalidate()
                }
        }
        parent.requestDisallowInterceptTouchEvent(toggleTouch != null)
    }


    override fun onDraw(canvas: Canvas) {
        canvas.drawOval(mDrawRect, backgroundStrokePaint)
        canvas.drawArc(
            mDrawRect,
            -QUARTER_CIRCLE_DEGREES,
            angleDelegate.actualAngle,
            false,
            foregroundStrokePaint
        )
        toggleDelegate.toggleCenter?.let { toggle ->
            canvas.drawCircle(toggle.x.toFloat(), toggle.y.toFloat(), toggleRadius, togglePaint)
        }
    }

    private fun getAngle(target: Point): Float {
        var angle = Math.toDegrees(
            atan2(
                (target.y - centerY).toDouble(),
                (target.x - centerX).toDouble()
            )
        ).toFloat() + QUARTER_CIRCLE_DEGREES // fix startAngle
        if (angle < 0) {
            angle += FULL_CIRCLE_DEGREES
        }
        return angle
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val defaultSize = getDefaultSizePx()
        val defaultWidth = max(suggestedMinimumWidth, defaultSize)
        val defaultHeight = max(suggestedMinimumHeight, defaultSize)
        val width: Int
        val height: Int
        width = when (widthMode) {
            MeasureSpec.EXACTLY -> {
                widthSize
            }
            MeasureSpec.AT_MOST -> {
                minOf(defaultWidth, widthSize)
            }
            else -> {
                defaultWidth
            }
        }
        height = when (heightMode) {
            MeasureSpec.EXACTLY -> {
                heightSize
            }
            MeasureSpec.AT_MOST -> {
                minOf(defaultHeight, heightSize)
            }
            else -> {
                defaultHeight
            }
        }
        setMeasuredDimension(width, height)
        invalidateDrawValues(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        invalidateDrawValues(width = w, height = h)
    }

    private fun invalidateDrawValues(width: Int, height: Int) {
        centerX = width / TWO
        centerY = height / TWO
        minOf(width, height).toFloat().let { shortSide ->
            val toggleMargin = toggleRadius + toggleShadowLength
            progressBarRadius = shortSide / TWO - toggleMargin
            val horizontalMargin = (width - shortSide) / TWO
            val verticalMargin = (height - shortSide) / TWO
            mDrawRect.set(
                horizontalMargin + toggleMargin,
                verticalMargin + toggleMargin,
                shortSide + horizontalMargin - toggleMargin,
                shortSide + verticalMargin - toggleMargin
            )
        }
        toggleDelegate = ToggleDelegate(
            progressBarRadius = progressBarRadius,
            toggleTouchRadius = toggleRadius + toggleAdditionTouchRadius
        )
        toggleDelegate.setAngel(
            angleDelegate.actualAngle,
            centerX = width / TWO,
            centerY = height / TWO
        )
    }

    private fun MotionEvent.toPoint(): Point {
        return Point(this.x.toInt(), this.y.toInt())
    }

    fun setAngel(angel: Float) {
        angleDelegate.correct(angel).let { actualAngle ->
            angleListener?.invoke(actualAngle)
        }
        invalidate()
    }

    private fun getDefaultSizePx(): Int {
        return (DEFAULT_SIZE_DP * Resources.getSystem().displayMetrics.density).toInt()
    }

    companion object {
        private const val TWO = 2f
        private const val DEFAULT_SIZE_DP = 200
        private const val FULL_CIRCLE_DEGREES = 360f
        private const val QUARTER_CIRCLE_DEGREES = 90f
        private const val TOGGLE_RADIUS_DEFAULT = 30f
        private const val TOGGLE_SHADOW_LENGTH_DEFAULT = 10f
        private const val TOGGLE_ADDITION_TOUCH_RADIUS_DEFAULT = 10f
        private const val PROGRESS_STROKE_WIDTH_DEFAULT = 20f
    }

}
