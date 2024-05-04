package com.example.newgreetingcard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ironsource.mediationsdk.ISBannerSize
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener


class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        //Init the Add
        IronSource.init(this,"85460dcd");

        //Create banner Ad to test
        val bannerContainer = findViewById<FrameLayout>(R.id.banner)
        val bannerAd = IronSource.createBanner(this,ISBannerSize.BANNER)
            bannerContainer.addView(bannerAd);


        bannerAd.levelPlayBannerListener = object : LevelPlayBannerListener {
            override fun onAdLoaded(adInfo: AdInfo) {
            }

            override fun onAdLoadFailed(ironSourceError: IronSourceError) {
            }

            override fun onAdClicked(adInfo: AdInfo) {
            }

            override fun onAdLeftApplication(adInfo: AdInfo) {
            }

            override fun onAdScreenPresented(adInfo: AdInfo) {
            }

            override fun onAdScreenDismissed(adInfo: AdInfo) {
            }
        }

        IronSource.loadBanner(bannerAd)


        //The button I created to click on to show  interstitial ad
        val secondButton =findViewById<Button>(R.id.second_page)
        secondButton.setOnClickListener{
//            val intent = Intent (this,MainActivity2::class.java)
//            startActivity(intent)
            IronSource.showInterstitial()
        }
        IronSource.loadInterstitial()
        IronSource.setLevelPlayInterstitialListener(object : LevelPlayInterstitialListener {
            // Invoked when the interstitial ad was loaded successfully.
            // AdInfo parameter includes information about the loaded ad
            override fun onAdReady(adInfo: AdInfo) {}

            // Indicates that the ad failed to be loaded
            override fun onAdLoadFailed(error: IronSourceError) {}

            // Invoked when the Interstitial Ad Unit has opened, and user left the application screen.
            // This is the impression indication.
            override fun onAdOpened(adInfo: AdInfo) {}

            // Invoked when the interstitial ad closed and the user went back to the application screen.
            override fun onAdClosed(adInfo: AdInfo) {}

            // Invoked when the ad failed to show
            override fun onAdShowFailed(error: IronSourceError, adInfo: AdInfo) {}

            // Invoked when end user clicked on the interstitial ad
            override fun onAdClicked(adInfo: AdInfo) {}

            // Invoked before the interstitial ad was opened, and before the InterstitialOnAdOpenedEvent is reported.
            // This callback is not supported by all networks, and we recommend using it only if
            // it's supported by all networks you included in your build.
            override fun onAdShowSucceeded(adInfo: AdInfo) {}
        })




    }


    override fun onResume() {
        super.onResume()
        // call the IronSource onResume method
        IronSource.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        // call the IronSource onPause method
        IronSource.onPause(this)
    }



}

