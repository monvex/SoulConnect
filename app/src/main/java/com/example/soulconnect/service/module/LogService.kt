package com.example.soulconnect.service.module

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}