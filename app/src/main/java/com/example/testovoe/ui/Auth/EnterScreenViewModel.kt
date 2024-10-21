package com.example.testovoe.ui.Auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EnterScreenViewModel : ViewModel() {
    private val _emailState = MutableStateFlow(false)
    val emailState = _emailState.asStateFlow()

    private val _emailValid = MutableStateFlow(false)
    val emailValid = _emailValid.asStateFlow()


    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()


    fun emailIsEntered(inputEmail: String) {
        _email.value = inputEmail
        isMailNotEmpty(inputEmail)
        isMailValid()
    }

    fun isMailValid() : Boolean{
         return  if (android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
             true
        } else {
              false
        }
    }

    fun isMailNotEmpty(inputEmail: String) {
        if (inputEmail.isNotEmpty()) {
            _emailState.value = true
        } else {
            _emailState.value = false
        }
    }
}