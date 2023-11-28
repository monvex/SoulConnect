package com.example.soulconnect.model.service.impl

import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.User
import com.example.soulconnect.model.service.trace
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }


    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

//    override suspend fun sendRecoveryEmail(email: String) {
//        auth.sendPasswordResetEmail(email).await()
//    }

    override fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInAnonymously()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        val credential = EmailAuthProvider.getCredential(email, password)

        Firebase.auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener { onResult(it.exception) }
    }

//    override suspend fun deleteAccount() {
//        auth.currentUser!!.delete().await()
//    }

//    override suspend fun signOut() {
//        if (auth.currentUser!!.isAnonymous) {
//            auth.currentUser!!.delete()
//        }
//        auth.signOut()
//
//        // Sign the user back in anonymously.
//        createAnonymousAccount()
//    }

    companion object {
        private const val LINK_ACCOUNT_TRACE = "linkAccount"
    }
}