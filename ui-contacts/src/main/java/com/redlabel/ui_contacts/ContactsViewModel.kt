package com.redlabel.ui_contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redlabel.domain.interactors.GetContacts
import com.redlabel.domain.model.Contact
import com.redlabel.ui_common.model.UiMessage
import com.redlabel.ui_common.model.UiMessageManager
import com.redlabel.ui_common.util.ObservableLoadingCounter
import com.redlabel.ui_common.util.collectStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class ContactsViewModel @Inject constructor(
    private val getContacts: GetContacts,
    private val observableLoadingCounter: ObservableLoadingCounter,
    private val uiMessageManager: UiMessageManager,
    ) : ViewModel() {

    val mflow = getContacts.flow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val mobservableLoadingCounter = observableLoadingCounter.observable.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    val muiMessageManager = uiMessageManager.message.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val state = combine(
        getContacts.flow,
        observableLoadingCounter.observable,
        uiMessageManager.message
    ) { contacts, isLoading, message ->
        ContactsViewState(contacts, isLoading, message)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ContactsViewState.Empty)

    init {
        viewModelScope.launch {
            getContacts(params = Unit).collectStatus(observableLoadingCounter, uiMessageManager)
        }
    }
}

data class ContactsViewState(
    val contacts: List<Contact> = emptyList(),
    val isLoading: Boolean = false,
    val message: UiMessage? = null
) {
    companion object {
        val Empty = ContactsViewState()
    }
}
