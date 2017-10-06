package com.ikechukwuakalu.daggermvp.utils.espresso

import android.support.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class SimpleCounterIdlingResource(private var resourceName: String) : IdlingResource {

    private var counter = AtomicInteger(0)

    @Volatile
    private var resourceCallback : IdlingResource.ResourceCallback? = null

    override fun getName(): String = resourceName

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(p0: IdlingResource.ResourceCallback?) {
        resourceCallback = p0
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val currentValue = counter.decrementAndGet()
        if (currentValue == 0)
            resourceCallback?.onTransitionToIdle()

        if (currentValue < 0)
            throw IllegalArgumentException("Counter is corrupt")
    }
}