package com.example.soulconnect.model.service.impl

import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.SearchService
import com.example.soulconnect.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SearchServiceImpl @Inject constructor(private val firestore: FirebaseFirestore, private val auth: AccountService) :
    SearchService {


    override val users: Flow<List<User>>
        get() = auth.currentUser.flatMapLatest {
            firestore.collection("users").dataObjects()
        }
    override suspend fun getSortedCandidates(user: User?, users: List<User?>): List<User?> {
        return users
    }

    override suspend fun getUsers(): List<User> =
        firestore.collection("users").get().await().toObjects()
}
