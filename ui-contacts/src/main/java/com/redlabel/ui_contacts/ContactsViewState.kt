package com.redlabel.ui_contacts

import androidx.compose.runtime.Immutable
import com.redlabel.domain.model.Contact
import com.redlabel.ui_common.model.UiMessage

@Immutable
data class ContactsViewState(
    val contacts: Map<String, List<Contact>>? = null,
    val filter: String? = null,
    val isLoading: Boolean = false,
    val isEmptyState: Boolean = false,
    val isFilterEmpty: Boolean = false,
    val message: UiMessage? = null
) {
    companion object {
        val Empty = ContactsViewState()
    }
}
