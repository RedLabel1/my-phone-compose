package com.redlabel.ui_contacts

import androidx.compose.runtime.Immutable
import com.redlabel.domain.model.Contact
import com.redlabel.ui_common.model.UiMessage

@Immutable
data class ContactsViewState(
    val contacts: List<Contact>? = null,
    val isLoading: Boolean = false,
    val message: UiMessage? = null
) {
    companion object {
        val Empty = ContactsViewState()
    }
}
