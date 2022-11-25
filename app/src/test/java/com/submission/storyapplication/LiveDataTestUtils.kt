package com.submission.storyapplication

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class LiveDataTestUtils {
    companion object{
        @VisibleForTesting(otherwise = VisibleForTesting.NONE)
        fun <T> LiveData<T>.getOrAwaitValue(
            time: Long = 2,
            timeUnit: TimeUnit = TimeUnit.SECONDS,
            afterObserve: () -> Unit = {}
        ): T {
            var data: T? = null
            val latch = CountDownLatch(1)
            val observer = object : Observer<T> {
                override fun onChanged(o: T?) {
                    data = o
                    latch.countDown()
                    this@getOrAwaitValue.removeObserver(this)
                }
            }
            this.observeForever(observer)

            try {
                afterObserve.invoke()

                // Don't wait indefinitely if the LiveData is not set.
                if (!latch.await(time, timeUnit)) {
                    throw TimeoutException("LiveData value was never set.")
                }

            } finally {
                this.removeObserver(observer)
            }

            @Suppress("UNCHECKED_CAST")
            return data as T
        }
    }
    object DataDummy {
        fun generateDummyStoriesResponse(): List<AllStoriesModel.stories> {
            val items: MutableList<AllStoriesModel.stories> = arrayListOf()
            for (i in 0..100) {
                val stories = AllStoriesModel.stories(
                    "$i",
                    "Budi",
                    "Lorem ipsum",
                    "https://www.dicoding.com/",
                    "2022-02-02",
                    37.4220936F,
                    -122.08392F
                )
                items.add(stories)
            }
            return items
        }
    }
}