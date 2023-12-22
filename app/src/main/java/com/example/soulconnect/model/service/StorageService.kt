package com.example.soulconnect.model.service

import com.example.soulconnect.model.User

interface StorageService {

    suspend fun getUser(): User?
    suspend fun updateUser(user: User)
    suspend fun getUserFromId(id: String): User?
}