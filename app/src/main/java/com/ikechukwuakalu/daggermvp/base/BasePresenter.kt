package com.ikechukwuakalu.daggermvp.base

interface BasePresenter<T> {

    fun attach(view: T)

    fun detach()

}
