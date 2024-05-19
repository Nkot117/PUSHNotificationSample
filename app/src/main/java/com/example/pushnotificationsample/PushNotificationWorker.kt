package com.example.pushnotificationsample

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PushNotificationWorker  @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val notificationSender: NotificationSender
) : Worker(context, params) {
    override fun doWork(): Result {
        notificationSender.sendPushNotification()
        return Result.success()
    }
}