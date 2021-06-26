# EmojiRatingBar
[![Kotlin Version](https://img.shields.io/badge/Kotlin-v1.5.10-blue.svg)](https://kotlinlang.org)  [![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=flat)](https://www.android.com/) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![](https://jitpack.io/v/unaisulhadi/emojiratingbar.svg)](https://jitpack.io/#unaisulhadi/emojiratingbar)
<br/>

#### A simple Emoji Rating Bar library for Android completely written in Kotlin.

 <img src="https://raw.githubusercontent.com/unaisulhadi/EmojiRatingBar/master/art/Rating.png">
 
## 🛠 Installation

Add the JitPack repository to your ```build.gradle``` (Project) file
```bash
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add this line to your ```build.gradle``` (module)
```bash
implementation 'com.github.unaisulhadi:emojiratingbar:1.0.0'
```

## ⌨️ Usage

- Add this in your layout xml file.

```xml
<com.hadi.emojiratingbar.EmojiRatingBar
                android:id="@+id/emoji_rating_bar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                app:showText="true"
                app:titleColor="#844848"
                app:defaultValue="AWFUL"
                android:fontFamily="@font/playfair_display_semibold" />
```

- 5 EmojiRatingBar Rate Values
```kotlin
 0. RateStatus.AWFUL
 1. RateStatus.BAD
 2. RateStatus.OKAY
 3. RateStatus.GOOD
 4. RateStatus.GREAT
```

### Parameters

| Explanation               | Parameter Name          | Type       | Default Value            |
| ------------------------- | ----------------------- | ---------- | --------------           |
| Set Title Color           | **titleColor**          | color      | Same as the Emoji Color  |
| Set Show Title status     | **showText**            | boolean    | true                     |
| Set Default Rate          | **defaultValue**        | enum       | OKAY                     |
| Set FontFamily            | **android:fontFamily**  | font       | Android Default          | 

### Use Programmatically

| Explanation                 | Code                        | Parameter            | Return type    |
| --------------------------- | --------------------------- | -------------------- | -------------- |
| Set Title Color             | setTitleColor(color)        | color reference      |                |
| Set Show Title status       | setShowText(true)           | boolean              |                |
| Get Show Title Status       | getShowText()               |                      | boolean        |
| Set Current Rate            | setCurrentRateStatus(rate)  | enum                 |                |
| Get Current Rate            | getCurrentRateStatus()      |                      | rate           |
| Set Title Font (from res)   | setTypeFace(font)           | font reference       |                |
| Set Title Font (from Asset) | setTypeFaceFromAssets(font) | path to asset        |                |


### Refer code below

- Get current Rate Status of EmojiRatingBar.
```kotlin
emojiRatingBar.getCurrentRateStatus().toString()
```

- Set Rate Status programmatically.
```
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
 
 ## 🍰  Contribute  

Feel free to fork this project, to optimise the code or to add new features. 

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
