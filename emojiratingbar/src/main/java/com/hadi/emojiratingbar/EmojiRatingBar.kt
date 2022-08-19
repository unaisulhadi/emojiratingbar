package com.hadi.emojiratingbar

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

@SuppressLint("ResourceType")
class EmojiRatingBar(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private class Smiley(var image: ImageView, var text: TextView, var status: RateStatus)

    private var rating: RateStatus = RateStatus.EMPTY
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
    private var showAllText: Boolean = false
    private var color: Int  = 0
    private var fontFamilyId = 0
    private var readOnly = false

    private lateinit var smileyList: List<Smiley>



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
                color = getColor(R.styleable.EmojiRatingBar_titleColor, 0)
                showAllText = getBoolean(R.styleable.EmojiRatingBar_showAllText, false)
                rating = RateStatus.values()[getInt(R.styleable.EmojiRatingBar_defaultValue,0)]
                fontFamilyId = getResourceId(R.styleable.EmojiRatingBar_android_fontFamily,0)
                readOnly = false
            } finally {
                recycle()
            }
        }
        initType()
    }

    private fun initType() {
        binding()
        handleRatingClick()
        if(color > 0){
            setInitialColor(color)
        }
        if(fontFamilyId > 0){
            setTypeFace(fontFamilyId)
        }
        setCurrentRateStatus(rating)
        if(showAllText){
            hideAllTitles(false)
        }
        else if (!showText) {
            hideAllTitles(true)
        }
        setReadOnly(false)
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

        smileyList = listOf(
            Smiley(ivAwful, tvAwful, RateStatus.AWFUL),
            Smiley(ivBad, tvBad, RateStatus.BAD),
            Smiley(ivOkay, tvOkay, RateStatus.OKAY),
            Smiley(ivGood, tvGood, RateStatus.GOOD),
            Smiley(ivGreat, tvGreat, RateStatus.GREAT)
        )
    }

    private fun handleRatingClick() {
        btnAwful.setOnClickListener {
            scaleEmoji(ivAwful, btnAwful)
            setCurrentRateStatus(RateStatus.AWFUL)
        }


        btnBad.setOnClickListener {
            scaleEmoji(ivBad, btnBad)
            setCurrentRateStatus(RateStatus.BAD)
        }


        btnOkay.setOnClickListener {
            scaleEmoji(ivOkay, btnOkay)
            setCurrentRateStatus(RateStatus.OKAY)
        }


        btnGood.setOnClickListener {
            scaleEmoji(ivGood, btnGood)
            setCurrentRateStatus(RateStatus.GOOD)
        }

        btnGreat.setOnClickListener {
            scaleEmoji(ivGreat, btnGreat)
            setCurrentRateStatus(RateStatus.GREAT)
        }
    }


    fun setRateChangeListener(listener: OnRateChangeListener) {
        ratingChangeListener = listener
    }

    fun getCurrentRateStatus(): RateStatus {
        return rating
    }

    fun setShowText(showText: Boolean) {
        this.showText = showText
        for (smiley in smileyList){
            if(smiley.status == rating) smiley.text.visibility = if(showText) View.VISIBLE else View.INVISIBLE
            else smiley.text.visibility = View.INVISIBLE
        }
    }

    fun setShowAllText (showAllText: Boolean){
        this.showAllText = showAllText
        val visible = if(showAllText) View.VISIBLE else View.INVISIBLE
        for (smiley in smileyList){
            smiley.text.visibility = visible
        }
    }

    fun getShowText(): Boolean {
        return showText
    }

    fun getShowAllText(): Boolean {
        return showAllText
    }

    fun setReadOnly(readOnly: Boolean) {
        this.readOnly = readOnly
    }

    fun setCurrentRateStatus(rateStatus: RateStatus){
        if (this.readOnly) return
        rating = rateStatus
        ratingChangeListener?.onRateChanged(rating)

        for (smiley in smileyList){
            smiley.text.visibility = View.INVISIBLE
            if(smiley.status == rateStatus) {
                getRatingImageResource(smiley.status, true)?.let { smiley.image.setImageResource(it) }
                if(showText) smiley.text.visibility = View.VISIBLE
            }
            else {
                getRatingImageResource(smiley.status, false)?.let { smiley.image.setImageResource(it) }
            }

            if(showAllText){
                smiley.text.visibility = View.VISIBLE
            }
        }
    }


    private fun getRatingImageResource (rateStatus: RateStatus, active: Boolean = true) = when (rateStatus) {
        RateStatus.AWFUL -> if(active) R.drawable.ic_awful else R.drawable.ic_awful_inactive
        RateStatus.BAD -> if(active) R.drawable.ic_bad else R.drawable.ic_bad_inactive
        RateStatus.OKAY -> if(active) R.drawable.ic_okay else R.drawable.ic_okay_inactive
        RateStatus.GOOD -> if(active) R.drawable.ic_good else R.drawable.ic_good_inactive
        RateStatus.GREAT -> if(active) R.drawable.ic_great else R.drawable.ic_great_inactive
        RateStatus.EMPTY -> null
    }

    fun setTypeFace(@FontRes font: Int) {
        tvAwful.typeface = ResourcesCompat.getFont(context, font)
        tvBad.typeface = ResourcesCompat.getFont(context, font)
        tvOkay.typeface = ResourcesCompat.getFont(context, font)
        tvGood.typeface = ResourcesCompat.getFont(context, font)
        tvGreat.typeface = ResourcesCompat.getFont(context, font)
    }

    fun setTypeFaceFromAssets(fontPath: String) {
        tvAwful.typeface = Typeface.createFromAsset(context.assets, fontPath)
        tvBad.typeface = Typeface.createFromAsset(context.assets, fontPath)
        tvOkay.typeface = Typeface.createFromAsset(context.assets, fontPath)
        tvGood.typeface = Typeface.createFromAsset(context.assets, fontPath)
        tvGreat.typeface = Typeface.createFromAsset(context.assets, fontPath)
    }

    fun setTitleColor(color: Int) {
        tvAwful.setTextColor(ContextCompat.getColor(context,color))
        tvBad.setTextColor(ContextCompat.getColor(context,color))
        tvOkay.setTextColor(ContextCompat.getColor(context,color))
        tvGood.setTextColor(ContextCompat.getColor(context,color))
        tvGreat.setTextColor(ContextCompat.getColor(context,color))
    }

    private fun setInitialColor(color: Int) {
        tvAwful.setTextColor(color)
        tvBad.setTextColor(color)
        tvOkay.setTextColor(color)
        tvGood.setTextColor(color)
        tvGreat.setTextColor(color)
    }

    private fun hideAllTitles(hide: Boolean = true) {
        val titleVisibility = if(hide) View.GONE else View.VISIBLE
        tvAwful.visibility = titleVisibility
        tvBad.visibility = titleVisibility
        tvOkay.visibility = titleVisibility
        tvGood.visibility = titleVisibility
        tvGreat.visibility = titleVisibility
    }

    fun setAwfulEmojiTitle(title:String){
        tvAwful.text = title
    }

    fun setBadEmojiTitle(title:String){
        tvBad.text = title
    }

    fun setOkayEmojiTitle(title:String){
        tvOkay.text = title
    }

    fun setGoodEmojiTitle(title:String){
        tvGood.text = title
    }

    fun setGreatEmojiTitle(title:String){
        tvGreat.text = title
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