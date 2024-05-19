package com.example.pushnotificationsample

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMReceiver : FirebaseMessagingService() {
    private val TAG = "push notification"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived: " + remoteMessage.data["message"])
    }
}