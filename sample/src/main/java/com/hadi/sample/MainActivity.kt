package com.hadi.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hadi.emojiratingbar.EmojiRatingBar
import com.hadi.emojiratingbar.RateStatus

class MainActivity : AppCompatActivity() {

    private lateinit var emojiRatingBar: EmojiRatingBar
    private lateinit var tvSelectedRate: TextView
    private lateinit var btnGetCurrentRate: Button
    private lateinit var btnEnableTitle: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        emojiRatingBar = findViewById(R.id.emoji_rating_bar)
        tvSelectedRate = findViewById(R.id.tv_rate_status)
        btnGetCurrentRate = findViewById(R.id.btn_get_rating)
        btnEnableTitle = findViewById(R.id.btn_enable_title)


        tvSelectedRate.text = emojiRatingBar.getCurrentRateStatus().toString()
        btnGetCurrentRate.setOnClickListener {
            Toast.makeText(this, "${emojiRatingBar.getCurrentRateStatus()}", Toast.LENGTH_SHORT).show()
        }

        btnEnableTitle.setOnClickListener {
            if(emojiRatingBar.getShowText()) {
                emojiRatingBar.setShowText(false)
                btnEnableTitle.text = "ENABLE EMOJI TITLE"
            }else{
                emojiRatingBar.setShowText(true)
                btnEnableTitle.text = "DISABLE EMOJI TITLE"
            }
        }

        emojiRatingBar.setRateChangeListener(object : EmojiRatingBar.OnRateChangeListener {
            override fun onRateChanged(rateStatus: RateStatus) {
                tvSelectedRate.text = emojiRatingBar.getCurrentRateStatus().toString()
                Toast.makeText(this@MainActivity, "${rateStatus}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}