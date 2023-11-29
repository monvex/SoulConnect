package com.example.soulconnect.model.service

import com.example.soulconnect.model.Task
import com.example.soulconnect.model.User
import kotlinx.coroutines.flow.Flow

interface StorageService {

    suspend fun getUser(): User?
}