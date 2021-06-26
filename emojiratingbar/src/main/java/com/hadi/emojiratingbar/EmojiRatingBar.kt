package com.hadi.emojiratingbar

import android.animation.*
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

class EmojiRatingBar(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private var rating: RateStatus = RateStatus.OKAY
    private var ratingChangeListener: OnRateChangeListener? = null


    private var _view: View
    private lateinit var btnAwful: LinearLayout
    private lateinit var btnBad: LinearLayout
    private lateinit var btnOkay: LinearLayout
    private lateinit var btnGood: LinearLayout
    private lateinit var btnGreat: LinearLayout


    private lateinit var ivAwful: ImageView
    private lateinit var ivBad: ImageView
    private lateinit var ivOkay: ImageView
    private lateinit var ivGood: ImageView
    private lateinit var ivGreat: ImageView

    private lateinit var tvAwful: TextView
    private lateinit var tvBad: TextView
    private lateinit var tvOkay: TextView
    private lateinit var tvGood: TextView
    private lateinit var tvGreat: TextView

    private var showText: Boolean = true


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _view = inflater.inflate(R.layout.item_rate_view, this, true)
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.EmojiRatingBar,
            0, 0
        ).apply {

            try {
                showText = getBoolean(R.styleable.EmojiRatingBar_showText, true)

            } finally {
                recycle()
            }
        }
        initType()
    }

    private fun initType() {

        binding()
        handleRatingClick()

        if (!showText) {
            hideAllTitles()
        }
    }


    private fun binding() {
        btnAwful = _view.findViewById(R.id.btn_awful)
        btnBad = _view.findViewById(R.id.btn_bad)
        btnOkay = _view.findViewById(R.id.btn_okay)
        btnGood = _view.findViewById(R.id.btn_good)
        btnGreat = _view.findViewById(R.id.btn_great)

        ivAwful = _view.findViewById(R.id.iv_awful)
        ivBad = _view.findViewById(R.id.iv_bad)
        ivOkay = _view.findViewById(R.id.iv_okay)
        ivGood = _view.findViewById(R.id.iv_good)
        ivGreat = _view.findViewById(R.id.iv_great)

        tvAwful = findViewById(R.id.tv_awful)
        tvBad = findViewById(R.id.tv_bad)
        tvOkay = findViewById(R.id.tv_okay)
        tvGood = findViewById(R.id.tv_good)
        tvGreat = findViewById(R.id.tv_great)
    }

    private fun handleRatingClick() {
        btnAwful.setOnClickListener {
            scaleEmoji(ivAwful, btnAwful)
            setAwfulRateStatus()
        }

        btnBad.setOnClickListener {
            scaleEmoji(ivBad, btnBad)
            setBadRateStatus()
        }

        btnOkay.setOnClickListener {
            scaleEmoji(ivOkay, btnOkay)
            setOkayRateStatus()
        }


        btnGood.setOnClickListener {
            scaleEmoji(ivGood, btnGood)
            setGoodRateStatus()
        }

        btnGreat.setOnClickListener {
            scaleEmoji(ivGreat, btnGreat)
            setGreatRateStatus()
        }
    }

    fun setRateChangeListener(listener: OnRateChangeListener) {
        ratingChangeListener = listener
    }

    fun getCurrentRateStatus(): RateStatus {
        return rating
    }

    fun setCurrentRateStatus(rateStatus: RateStatus) {
        when (rateStatus) {

            RateStatus.AWFUL -> setAwfulRateStatus()
            RateStatus.BAD -> setBadRateStatus()
            RateStatus.OKAY -> setOkayRateStatus()
            RateStatus.GOOD -> setGoodRateStatus()
            RateStatus.GREAT -> setGreatRateStatus()
        }
    }

    fun setShowText(showText: Boolean) {
        this.showText = showText
        setCurrentRateStatus(getCurrentRateStatus())
    }

    fun getShowText(): Boolean {
        return showText
    }

    private fun setAwfulRateStatus() {
        rating = RateStatus.AWFUL
        ratingChangeListener?.onRateChanged(rating)


        ivAwful.setImageResource(R.drawable.ic_awful)

        if (showText) {
            tvAwful.visibility = View.VISIBLE
            tvBad.visibility = View.INVISIBLE
            tvOkay.visibility = View.INVISIBLE
            tvGood.visibility = View.INVISIBLE
            tvGreat.visibility = View.INVISIBLE
        } else {
            hideAllTitles()
        }


        ivBad.setImageResource(R.drawable.ic_bad_inactive)
        ivOkay.setImageResource(R.drawable.ic_okay_inactive)
        ivGood.setImageResource(R.drawable.ic_good_inactive)
        ivGreat.setImageResource(R.drawable.ic_great_inactive)


    }

    private fun setBadRateStatus() {
        rating = RateStatus.BAD
        ratingChangeListener?.onRateChanged(rating)

        ivBad.setImageResource(R.drawable.ic_bad)
        if (showText) {
            tvBad.visibility = View.VISIBLE
            tvAwful.visibility = View.INVISIBLE
            tvOkay.visibility = View.INVISIBLE
            tvGood.visibility = View.INVISIBLE
            tvGreat.visibility = View.INVISIBLE
        } else {
            hideAllTitles()
        }


        ivAwful.setImageResource(R.drawable.ic_awful_inactive)
        ivOkay.setImageResource(R.drawable.ic_okay_inactive)
        ivGood.setImageResource(R.drawable.ic_good_inactive)
        ivGreat.setImageResource(R.drawable.ic_great_inactive)


    }

    private fun setOkayRateStatus() {
        rating = RateStatus.OKAY
        ratingChangeListener?.onRateChanged(rating)

        ivOkay.setImageResource(R.drawable.ic_okay)
        if (showText) {
            tvOkay.visibility = View.VISIBLE
            tvAwful.visibility = View.INVISIBLE
            tvBad.visibility = View.INVISIBLE
            tvGood.visibility = View.INVISIBLE
            tvGreat.visibility = View.INVISIBLE
        } else {
            hideAllTitles()
        }


        ivAwful.setImageResource(R.drawable.ic_awful_inactive)
        ivBad.setImageResource(R.drawable.ic_bad_inactive)
        ivGood.setImageResource(R.drawable.ic_good_inactive)
        ivGreat.setImageResource(R.drawable.ic_great_inactive)


    }

    private fun setGoodRateStatus() {
        rating = RateStatus.GOOD
        ratingChangeListener?.onRateChanged(rating)

        ivGood.setImageResource(R.drawable.ic_good)
        if (showText) {
            tvGood.visibility = View.VISIBLE
            tvAwful.visibility = View.INVISIBLE
            tvBad.visibility = View.INVISIBLE
            tvOkay.visibility = View.INVISIBLE
            tvGreat.visibility = View.INVISIBLE
        } else {
            hideAllTitles()
        }


        ivAwful.setImageResource(R.drawable.ic_awful_inactive)
        ivBad.setImageResource(R.drawable.ic_bad_inactive)
        ivOkay.setImageResource(R.drawable.ic_good_inactive)
        ivGreat.setImageResource(R.drawable.ic_great_inactive)


    }

    private fun setGreatRateStatus() {
        rating = RateStatus.GREAT
        ratingChangeListener?.onRateChanged(rating)

        ivGreat.setImageResource(R.drawable.ic_great)
        if (showText) {
            tvGreat.visibility = View.VISIBLE

            tvAwful.visibility = View.INVISIBLE
            tvBad.visibility = View.INVISIBLE
            tvOkay.visibility = View.INVISIBLE
            tvGood.visibility = View.INVISIBLE
        } else {
            hideAllTitles()
        }


        ivAwful.setImageResource(R.drawable.ic_awful_inactive)
        ivBad.setImageResource(R.drawable.ic_bad_inactive)
        ivOkay.setImageResource(R.drawable.ic_okay_inactive)
        ivGood.setImageResource(R.drawable.ic_good_inactive)


    }

    fun setTypeFace(@FontRes font: Int) {
        tvAwful.typeface = ResourcesCompat.getFont(context, font)
        tvBad.typeface = ResourcesCompat.getFont(context, font)
        tvOkay.typeface = ResourcesCompat.getFont(context, font)
        tvGood.typeface = ResourcesCompat.getFont(context, font)
        tvGreat.typeface = ResourcesCompat.getFont(context, font)
    }

    fun setTypeFaceFromAssets(fontPath: String) {
        tvAwful.typeface = Typeface.createFromAsset(context.assets,fontPath)
        tvBad.typeface = Typeface.createFromAsset(context.assets,fontPath)
        tvOkay.typeface = Typeface.createFromAsset(context.assets,fontPath)
        tvGood.typeface = Typeface.createFromAsset(context.assets,fontPath)
        tvGreat.typeface = Typeface.createFromAsset(context.assets,fontPath)
    }

    private fun hideAllTitles() {
        tvAwful.visibility = View.GONE
        tvBad.visibility = View.GONE
        tvOkay.visibility = View.GONE
        tvGood.visibility = View.GONE
        tvGreat.visibility = View.GONE
    }

    private fun scaleEmoji(targetView: View, disableView: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(targetView, scaleX, scaleY)
        animator.repeatCount = 1
        animator.duration = 200
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(disableView)
        animator.start()
    }

    private fun ValueAnimator.disableViewDuringAnimation(view: View) {
        // This extension method listens for start/end events on an animation and disables
        // the given view for the entirety of that animation.

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

    interface OnRateChangeListener {
        fun onRateChanged(rateStatus: RateStatus)
    }
}