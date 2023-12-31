package com.example.soulconnect.model.service.impl

import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.StorageService
import com.example.soulconnect.model.service.trace
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val firestore: FirebaseFirestore, private val auth: AccountService) :
    StorageService {
    override suspend fun getUser(): User? =
        firestore.collection("users").document(auth.currentUserId).get().await().toObject()

    override suspend fun getUserFromId(id: String): User? =
        firestore.collection("users").document(id).get().await().toObject()

    override suspend fun updateUser(user: User): Unit =
        trace("updateUser") {
            firestore.collection("users").document(user.id).set(user).await()
        }
}