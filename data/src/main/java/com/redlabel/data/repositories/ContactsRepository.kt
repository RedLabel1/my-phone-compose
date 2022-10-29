package com.redlabel.data.repositories

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import com.redlabel.data.model.Contact
import com.redlabel.data.model.Phone
import com.redlabel.data.model.PhoneType
import com.redlabel.data.model.StructuredName
import com.redlabel.data.model.phoneTypeDictionary
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

class ContactsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val projection: Array<out String> = arrayOf(
        ContactsContract.Data.CONTACT_ID,
        ContactsContract.Data.LOOKUP_KEY,
        ContactsContract.Data.MIMETYPE,
        ContactsContract.Data.DATA1,
        ContactsContract.Data.DATA2,
        ContactsContract.Data.DATA3,
        ContactsContract.Data.DATA4,
        ContactsContract.Data.DATA5,
        ContactsContract.Data.DATA6,
        ContactsContract.Data.PHOTO_ID,
    )

    private val selection = ContactsContract.Data.MIMETYPE +
            " IN ('" + ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE +
            "', '" + ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE + "')"

    fun retrieveContactData(filter: String?): List<Contact> {

        val cursor = context.contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            projection,
            selection,
            null,
            ContactsContract.Data.DISPLAY_NAME
        )

        while (cursor?.moveToNext() == true) {

            var contact: Contact? = Contact(id = cursor.getLong(0), lookupKey = cursor.getString(1))

            contact = when (cursor.getString(2)) {
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE -> contact?.copy(structuredName = getStructuredName(cursor))
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE -> contact?.copy(phones = addPhone(cursor = cursor, phones = contact.phones))
//                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE ->
//                ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE ->
                else -> null
            }

            Contact(
                id = cursor.getLong(0),
                lookupKey = cursor.getString(1)
            )
        }

        cursor?.close()
        return emptyList()
    }

    private fun getStructuredName(cursor: Cursor) = StructuredName(
        displayName = cursor.getString(3),
        givenName = cursor.getString(4),
        familyName = cursor.getString(5),
        middleName = cursor.getString(7),
        prefix = cursor.getString(6),
        suffix = cursor.getString(8)
    )

    private fun addPhone(cursor: Cursor, phones: List<Phone>) = phones.toMutableList().apply {
        add(
            Phone(
                number = cursor.getString(3),
                normalizedNumber = cursor.getString(6),
                type = phoneTypeDictionary.getValue(cursor.getInt(4))
            )
        )
    }


//    private fun retrieveContacts(filter: String?): List<Contact> {
//
//        val contacts = mutableListOf<Contact>()
//
//        val contentUri: Uri = when (filter.isNullOrEmpty()) {
//            true -> ContactsContract.Contacts.CONTENT_URI
//            false -> Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter))
//        }
//
//        val cursor = context.contentResolver.query(
//            contentUri,
//            projection,
//            null,
//            null,
//            null
//        )
//
//        while (cursor?.moveToNext() == true) {
//
//            val contactId = cursor.getColumnIndex(ContactsContract.Contacts._ID)
//            val lookupKey = cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
//
//            contacts.add(
//                Contact(
//                    id = cursor.getLong(contactId),
//                    lookupKey = cursor.getString(lookupKey)
//                )
//            )
//        }
//        cursor?.close()
//        return contacts
//    }
//
//    private fun retrieveContactsDetails(contacts: List<Contact>): List<Contact> {
//
//        return contacts.map { contact ->
//            val contentUri: Uri = Uri.
//
//            val cursor = context.contentResolver.query(
//                entityUri,
//                arrayOf(
//                    ContactsContract.RawContactsEntity.DATA_ID,
//                    ContactsContract.RawContactsEntity.MIMETYPE,
//                    ContactsContract.RawContactsEntity.DATA1
//                ),
//                null,
//                null,
//                null
//            )
//
//            lateinit var result: Contact
//            while (cursor?.moveToNext() == true) {
//                result =
//                    contact.copy(
//                        fullName = cursor.getString(1),
//                        pictureUrl = cursor.getString(2)
//                    )
//            }
//
//            cursor?.close()
//
//            result
//        }
//    }

//    private suspend fun getContactNumbers(): HashMap<String, ArrayList<String>> {
//        val contactsNumberMap = HashMap<String, ArrayList<String>>()
//        val phoneCursor: Cursor? = mApplication.contentResolver.query(
//            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//            null,
//            null,
//            null,
//            null
//        )
//        if (phoneCursor != null && phoneCursor.count > 0) {
//            val contactIdIndex = phoneCursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
//            val numberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
//            while (phoneCursor.moveToNext()) {
//                val contactId = phoneCursor.getString(contactIdIndex)
//                val number: String = phoneCursor.getString(numberIndex)
//                //check if the map contains key or not, if not then create a new array list with number
//                if (contactsNumberMap.containsKey(contactId)) {
//                    contactsNumberMap[contactId]?.add(number)
//                } else {
//                    contactsNumberMap[contactId] = arrayListOf(number)
//                }
//            }
//            //contact contains all the number of a particular contact
//            phoneCursor.close()
//        }
//        return contactsNumberMap
//    }
//
//    private suspend fun getContactEmails(): HashMap<String, ArrayList<String>> {
//        val contactsEmailMap = HashMap<String, ArrayList<String>>()
//        val emailCursor = mApplication.contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
//            null,
//            null,
//            null,
//            null)
//        if (emailCursor != null && emailCursor.count > 0) {
//            val contactIdIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)
//            val emailIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
//            while (emailCursor.moveToNext()) {
//                val contactId = emailCursor.getString(contactIdIndex)
//                val email = emailCursor.getString(emailIndex)
//                //check if the map contains key or not, if not then create a new array list with email
//                if (contactsEmailMap.containsKey(contactId)) {
//                    contactsEmailMap[contactId]?.add(email)
//                } else {
//                    contactsEmailMap[contactId] = arrayListOf(email)
//                }
//            }
//            //contact contains all the emails of a particular contact
//            emailCursor.close()
//        }
//        return contactsEmailMap
//    }
}
