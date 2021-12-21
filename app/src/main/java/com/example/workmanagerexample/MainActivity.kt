package com.example.workmanagerexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* SINGLE TASK */
        /* SET DATA */
       /* val data = workDataOf("msg" to "Hello")
        val backGroundWork = OneTimeWorkRequestBuilder<BackGroundWork>().setInputData(data).setInitialDelay(10, TimeUnit.SECONDS).build()

        val workManager = WorkManager.getInstance(application)
        val continuation = workManager.beginWith(backGroundWork)
        continuation.enqueue()

        *//* CHECK STATUS *//*
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(backGroundWork.id)
            .observe(this, Observer {

                val status: String = it.state.name
                Toast.makeText(this,status, Toast.LENGTH_SHORT).show()
            })*/


        /* REPEATING TASK */
        val data = workDataOf("msg" to "Hello")
        val work = PeriodicWorkRequestBuilder<BackGroundWork>(5, TimeUnit.SECONDS).setInputData(data).build()
        WorkManager.getInstance(application).enqueue(work)
        // WorkManager.getInstance(application).enqueueUniquePeriodicWork("sdds",ExistingPeriodicWorkPolicy.KEEP, work)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work.id)
            .observe(this, {
                val status: String = it.state.name
                Toast.makeText(this,status, Toast.LENGTH_SHORT).show()
            })


    }
}