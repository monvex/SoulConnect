package com.example.soulconnect.model.service.impl

import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.SearchService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SearchServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService,
) :
    SearchService {

    override val users: Flow<List<User>>
        get() = auth.currentUser.flatMapLatest {
            firestore.collection("users").dataObjects()
        }

    override suspend fun getSortedCandidates(user: User?, users: List<User?>): List<User> {
        if (user == null) {
            return emptyList()
        }

        val weightedSimilarity = mutableListOf<Pair<String, Double>>()

        for (otherUser in users.filterNotNull()) {
            if (otherUser.name != user.name) {
                val compositeSimilarity: Double
                if(user.chats.contains(otherUser.id))
                    continue
                else if(user.likeList.contains(otherUser.id)) {
                    compositeSimilarity = 10000.0
                }
                else {
                    val ageSimilarity =
                        1 - kotlin.math.abs((user.age.toInt() - otherUser.age.toInt()) / 10.0)
                    val interestsSimilarity =
                        user.tagList.intersect(otherUser.tagList).size.toDouble() / user.tagList.size
                    val weightAge = 0.3
                    val weightInterests = 0.8
                    compositeSimilarity =
                        weightAge * ageSimilarity + weightInterests * interestsSimilarity

                }
                weightedSimilarity.add(Pair(otherUser.name, compositeSimilarity))
            }
        }
        val sortedSimilarity = weightedSimilarity.sortedByDescending { it.second }
        val sortedUsers = sortedSimilarity.mapNotNull { (userName, compositeSimilarity) ->
            val foundUser = users.find { it?.name == userName }
            foundUser?.coefficient = String.format("%.2f", compositeSimilarity).toDouble() // Format to two decimal places
            foundUser
        }

        sortedUsers.forEach { user ->
            println("User: $user, Coefficient: ${user.coefficient}")
        }

        return sortedUsers
    }

    override suspend fun getUsers(): List<User> =
        firestore.collection("users").get().await().toObjects()
}