package com.redlabel.ui_contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redlabel.domain.interactors.GetContacts
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

    private val filter = MutableStateFlow<String?>(null)

    val state = combine(
        getContacts.flow,
        filter,
        observableLoadingCounter.observable,
        uiMessageManager.message
    ) { contacts, filter, isLoading, message ->
        ContactsViewState(
            contacts = contacts?.groupBy { it.fullName[0].toString() },
            filter = filter,
            isLoading = isLoading,
            isEmptyState = contacts?.isEmpty() == true,
            isFilterEmpty = filter.isNullOrEmpty() && contacts?.isEmpty() == true,
            message = message
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ContactsViewState.Empty)

    init {
        viewModelScope.launch {
            filter.collect { fetchContacts(filter = it) }
        }
    }

    private fun fetchContacts(filter: String?) = viewModelScope.launch {
        getContacts(params = GetContacts.Params(filter)).collectStatus(observableLoadingCounter, uiMessageManager)
    }

    fun updateFilter(filter: String?) = viewModelScope.launch {
        this@ContactsViewModel.filter.emit(filter)
    }
}
