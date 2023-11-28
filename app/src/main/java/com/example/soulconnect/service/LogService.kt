package com.example.soulconnect.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}