package com.redlabel.domain.interactors

import com.redlabel.data.repositories.ContactsRepository
import com.redlabel.domain.Interactor
import com.redlabel.domain.mapper.ContactMapper
import com.redlabel.domain.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetContacts @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val contactMapper: ContactMapper
) : Interactor<GetContacts.Params, List<Contact>>() {

    override suspend fun doWork(params: Params): List<Contact> = withContext(Dispatchers.IO) {
        contactsRepository.retrieveContactData(params.filter).map { contactMapper.toDomain(it) }
    }

    data class Params(val filter: String?)
}
