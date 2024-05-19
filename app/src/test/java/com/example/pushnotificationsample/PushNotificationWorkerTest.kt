package com.example.pushnotificationsample

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import androidx.work.testing.TestWorkerBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import org.assertj.core.api.Assertions.*
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
class PushNotificationWorkerTest {
    private lateinit var context: Context
    private lateinit var executor: Executor
    private lateinit var notificationSender: NotificationSender

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        executor = Executors.newSingleThreadExecutor()

        // PushNotificationWorkerが依存しているNotificationSenderのモックを作成
        // sendPushNotification()メソッドが呼び出されたときに何もしないように設定
        notificationSender = mock(name = "MockNotificationSender")
        doNothing().whenever(notificationSender).sendPushNotification()
    }

    @Test
    fun testPushNotificationWorker() {
        val worker = TestWorkerBuilder.from(context, PushNotificationWorker::class.java)
            .setWorkerFactory(TestWorkerFactory(notificationSender))
            .build()

        val result = worker.doWork()

        // sendPushNotification()メソッドが1回呼び出されたことを確認
        verify(notificationSender, times(1)).sendPushNotification()

        // Result.success()が返されたことを確認
        assertThat(result).isEqualTo(ListenableWorker.Result.success())
    }
}

class TestWorkerFactory(private val notificationSender: NotificationSender) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {

        return PushNotificationWorker(appContext, workerParameters, notificationSender)
    }
}