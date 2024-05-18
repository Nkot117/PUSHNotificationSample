package com.example.pushnotificationsample

import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val workManager: WorkManager) :
    ViewModel() {
    fun requestPushNotification() {
        workManager.enqueue(OneTimeWorkRequest.from(PushNotificationWorker::class.java))
    }
}