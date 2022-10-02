package com.redlabel.ui_contacts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.redlabel.domain.model.Contact
import com.redlabel.ui_common.FilterTextField
import com.redlabel.ui_common.model.SortOption
import com.redlabel.ui_common.model.UiMessage
import com.redlabel.ui_common.util.containerColor
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterial3Api
@ExperimentalCoroutinesApi
@Composable
fun Contacts(viewModel: ContactsViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState().value

    Contacts(
        viewModel = viewModel,
        contacts = state.contacts,
        isLoading = state.isLoading,
        isEmptyState = state.isEmptyState,
        message = state.message
    )
}

@ExperimentalMaterial3Api
@ExperimentalCoroutinesApi
@Composable
fun Contacts(
    viewModel: ContactsViewModel,
    contacts: Map<String, List<Contact>>?,
    isLoading: Boolean,
    isEmptyState: Boolean,
    message: UiMessage?
) {
    if (contacts.isNullOrEmpty()) {
        EmptyContactsScreen()
    } else {
        ContactsContent(
            viewModel = viewModel,
            contacts = contacts,
            isLoading = isLoading,
            isEmptyState = isEmptyState,
            message = message
        )
    }
}

@Composable
fun EmptyContactsScreen() {

}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@ExperimentalCoroutinesApi
@Composable
fun ContactsContent(
    viewModel: ContactsViewModel,
    contacts: Map<String, List<Contact>>,
    isLoading: Boolean,
    isEmptyState: Boolean,
    message: UiMessage?
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val collapsedFraction = scrollBehavior.state.collapsedFraction

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Contacts", modifier = Modifier.align(Alignment.CenterStart))
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_contact))
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        containerColor(
                            containerColor = MaterialTheme.colorScheme.surface,
                            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            colorTransitionFraction = collapsedFraction
                        )
                    )
            ) {
                FilterTextField(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 24.dp),
                    filterHint = stringResource(id = R.string.search_contacts),
                    onFilterChanged = { viewModel.updateFilter(it) },
                    sortOptions = listOf(SortOption.FULL_NAME),
                    currentSortOption = SortOption.FULL_NAME
                )
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {

                contacts.forEach { (initial, contactsForInitial) ->

                    stickyHeader {
                        Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                            Text(modifier = Modifier.fillMaxWidth(), text = initial)
                        }
                    }

                    items(contactsForInitial) { contact ->
                        Text(
                            text = contact.fullName,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}
