package com.example.soulconnect.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}