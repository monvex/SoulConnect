package com.example.soulconnect.screens.log_in

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soulconnect.R.string as AppText
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.common.extensions.isValidEmail
import com.example.soulconnect.common.snackbar.SnackbarManager
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.User
import com.example.soulconnect.navigation.BottomItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : SoulConnectViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }
    val currentUser = accountService.currentUser //Удалить

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.genericError)
            return
        }

        launchCatching {
            accountService.authenticate(email.trim(), password.trim()) { error ->
                if (error == null)
                    openAndPopUp(BottomItem.Search.route, BottomItem.Search.route)
                else openAndPopUp("toLogin", "toLogIn")
            }

        }
    }

//    fun onForgotPasswordClick() {
//        if (!email.isValidEmail()) {
//            SnackbarManager.showMessage(AppText.email_error)
//            return
//        }
//
//        launchCatching {
//            accountService.sendRecoveryEmail(email)
//            SnackbarManager.showMessage(AppText.recovery_email_sent)
//        }
//    }
}