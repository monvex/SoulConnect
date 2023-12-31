package com.example.soulconnect.screens.log_in

import androidx.compose.runtime.mutableStateOf
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.common.extensions.isValidEmail
import com.example.soulconnect.common.snackbar.SnackbarManager
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.navigation.BottomItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.soulconnect.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService,
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

    val currentUser = accountService.hasUser

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(clearAndNavigate: (String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.genericError)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            clearAndNavigate(BottomItem.Search.route)
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