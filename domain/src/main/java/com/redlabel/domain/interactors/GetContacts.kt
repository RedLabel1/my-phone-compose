package com.redlabel.domain.interactors

import com.redlabel.data.repositories.ContactsRepository
import com.redlabel.domain.Interactor
import com.redlabel.domain.mapper.ContactMapper
import com.redlabel.domain.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetContacts @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val contactMapper: ContactMapper
) : Interactor<Unit, List<Contact>>() {

    override suspend fun doWork(params: Unit): List<Contact> = withContext(Dispatchers.IO) {
        contactsRepository.retrieveContacts().map { contactMapper.toDomain(it) }
    }
}
