package com.ikechukwuakalu.daggermvp.utils.rx.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Used for running tests synchronously
 */
class ImmediateScheduler : BaseScheduler {

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}