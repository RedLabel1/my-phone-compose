package com.redlabel.ui_contacts

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.redlabel.domain.model.Contact
import com.redlabel.ui_common.model.UiMessage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun Contacts(viewModel: ContactsViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState().value

    Contacts(
        viewModel = viewModel,
        contacts = state.contacts,
        isLoading = state.isLoading,
        message = state.message
    )
}

@Composable
@ExperimentalCoroutinesApi
fun Contacts(
    viewModel: ContactsViewModel,
    contacts: List<Contact>?,
    isLoading: Boolean,
    message: UiMessage?
) {
    Text(text = contacts?.get(0)?.fullName ?: "")
    Text(text = isLoading.toString())
    Text(text = message?.message ?: "")
}
