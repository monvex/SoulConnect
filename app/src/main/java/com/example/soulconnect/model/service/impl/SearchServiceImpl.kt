package com.example.soulconnect.model.service.impl

import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.SearchService
import com.example.soulconnect.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchServiceImpl @Inject constructor(private val firestore: FirebaseFirestore, private val auth: AccountService) :
    SearchService {
    override suspend fun getSortedCandidates(user: User, otherUsers: List<User>): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }
}