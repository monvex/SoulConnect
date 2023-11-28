package com.example.soulconnect.model.service

import com.example.soulconnect.model.service.User
import dagger.Provides
import kotlinx.coroutines.flow.Flow


interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

//    suspend fun authenticate(email: String, password: String)
//    suspend fun sendRecoveryEmail(email: String)
//    suspend fun createAnonymousAccount()
//    suspend fun linkAccount(email: String, password: String)
//    suspend fun deleteAccount()
//    suspend fun signOut()

    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
}