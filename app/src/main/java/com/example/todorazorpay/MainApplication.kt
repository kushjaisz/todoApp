package com.example.todorazorpay

import android.app.Application
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication :Application(){
    companion object {
        lateinit var firebaseAnalytics: FirebaseAnalytics

        fun logFirebaseEvent(eventName: String, params: Bundle? = null) {
            firebaseAnalytics.logEvent(eventName, params)
        }
    }

    override fun onCreate() {
        super.onCreate()


        firebaseAnalytics = Firebase.analytics
    }
}