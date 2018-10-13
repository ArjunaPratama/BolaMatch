package com.arjuna.matchball.Model

interface mainview {
    fun showLoading()

    fun showMatchList(data: List<EventsItem>)

    fun hideLoading()
}