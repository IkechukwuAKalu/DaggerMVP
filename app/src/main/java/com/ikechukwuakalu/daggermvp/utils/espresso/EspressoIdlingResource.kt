package com.ikechukwuakalu.daggermvp.utils.espresso

import android.support.test.espresso.IdlingResource

class EspressoIdlingResource {

    companion object {

        private val simpleIdlingResource = SimpleCounterIdlingResource("GLOBAL_RESOURCE")

        fun increment() {
            simpleIdlingResource.increment()
        }

        fun decrement() {
            simpleIdlingResource.decrement()
        }

        fun getIdlingResource(): IdlingResource = simpleIdlingResource
    }
}