package com.example.soulconnect.model.service

import com.example.soulconnect.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow


interface StorageService {

    suspend fun getUser(): User?
    suspend fun updateUser(user : User)
    suspend fun getUserFromId(id: String): User?
}