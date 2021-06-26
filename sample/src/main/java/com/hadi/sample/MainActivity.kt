package com.hadi.sample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hadi.emojiratingbar.EmojiRatingBar
import com.hadi.emojiratingbar.RateStatus

class MainActivity : AppCompatActivity() {

    private lateinit var emojiRatingBar: EmojiRatingBar
    private lateinit var btnGetCurrentRate: Button
    private lateinit var btnEnableTitle: Button
    private lateinit var btnSetFontFromResource: Button
    private lateinit var btnSetFontFromAssets: Button
    private lateinit var btnChangeTitleColor: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        emojiRatingBar = findViewById(R.id.emoji_rating_bar)
        btnGetCurrentRate = findViewById(R.id.btn_get_rating)
        btnEnableTitle = findViewById(R.id.btn_enable_title)
        btnSetFontFromResource = findViewById(R.id.btn_set_font_from_res)
        btnSetFontFromAssets = findViewById(R.id.btn_set_font_from_asset)
        btnChangeTitleColor = findViewById(R.id.btn_change_title_color)

        btnGetCurrentRate.setOnClickListener {
            Toast.makeText(this, "${emojiRatingBar.getCurrentRateStatus()}", Toast.LENGTH_SHORT)
                .show()
        }

        btnEnableTitle.setOnClickListener {
            if (emojiRatingBar.getShowText()) {
                emojiRatingBar.setShowText(false)
                btnEnableTitle.text = "ENABLE EMOJI TITLE"
            } else {
                emojiRatingBar.setShowText(true)
                btnEnableTitle.text = "DISABLE EMOJI TITLE"
            }
        }

        emojiRatingBar.setRateChangeListener(object : EmojiRatingBar.OnRateChangeListener {
            override fun onRateChanged(rateStatus: RateStatus) {
                when (rateStatus) {

                    RateStatus.AWFUL -> {
                        //Do your code
                    }
                    RateStatus.BAD -> {
                        //Do your code
                    }
                    RateStatus.OKAY -> {
                        //Do your code
                    }
                    RateStatus.GOOD -> {
                        //Do your code
                    }
                    RateStatus.GREAT -> {
                        //Do your code
                    }

                }
            }
        })

        //Set font from Resources
        btnSetFontFromResource.setOnClickListener {
            emojiRatingBar.setTypeFace(R.font.playfair_display_semibold)
        }


        //Set font from Assets
        btnSetFontFromAssets.setOnClickListener {
            emojiRatingBar.setTypeFaceFromAssets("fonts/kaushanscript_regular.ttf")
        }

        //Change Color
        btnChangeTitleColor.setOnClickListener {
            emojiRatingBar.setTitleColor(R.color.black)
        }
    }

}
