package com.example.pushnotificationsample

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestWorkerBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import org.assertj.core.api.Assertions.*

@RunWith(AndroidJUnit4::class)
class PushNotificationWorkerTest {
    private lateinit var context: Context
    private lateinit var executor: Executor
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        executor = Executors.newSingleThreadExecutor()
    }

    @Test
    fun testPushNotificationWorker() {
        val worker = TestWorkerBuilder<PushNotificationWorker>(
            context,
            executor,
        ).build()

        val result = worker.doWork()
        assertThat(result).isEqualTo(ListenableWorker.Result.success())
    }
}