# EmojiRatingBar
[![Kotlin Version](https://img.shields.io/badge/Kotlin-v1.5.10-blue.svg)](https://kotlinlang.org)  [![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=flat)](https://www.android.com/) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
<br/>

#### A simple Emoji Rating Bar View in Android completely written in Kotlin.

 <img src="https://raw.githubusercontent.com/unaisulhadi/EmojiRatingBar/master/art/Rating.png">
 
<!-- ## 🛠 Installation

Add this line to your module gradle.
```bash
implementation "com.hadi.emojiratingbar:1.0.0"
```
 -->
## ⌨️ Usage

- Add this in your layout xml file.

```xml
<com.hadi.emojiratingbar.EmojiRatingBar
                android:id="@+id/emoji_rating_bar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                app:showText="true"/>
```
- Set ```showText``` value accordingly if you need Emoji Title
```
app:showText="true" //XML
emojiRatingBar.setShowText(true) //Kotlin
```
- Get/Set current status of EmojiRatingBar, returns enum 
[ AWFUL,BAD,OKAY,GOOD,GREAT]
```kotlin
emojiRatingBar.getCurrentRateStatus().toString() //GET
emojiRatingBar.setCurrentRateStatus(RateStatus.GOOD) //SET

```
- Use OnRateChangeListener for observing Rate Changes
```kotlin
emojiRatingBar.setRateChangeListener(object : EmojiRatingBar.OnRateChangeListener {
            override fun onRateChanged(rateStatus: RateStatus) {
                //Do you Stuff
            }
        })
```
- Do your stuff for separate values
```kotlin
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
 ```

## ✍️ Author
* <b>Unaisul Hadi</b>
* Email: unaisulhadi@gmail.com


## 📝 License

```
MIT License

Copyright (c) 2021 Unaisul Hadi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
