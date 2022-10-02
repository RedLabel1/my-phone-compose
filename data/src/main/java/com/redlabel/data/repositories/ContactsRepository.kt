package com.redlabel.data.repositories

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.redlabel.data.model.Contact
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContactsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val projection: Array<out String> = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.LOOKUP_KEY,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
    )

    fun retrieveContacts(filter: String?): List<Contact> {

        val contacts = mutableListOf<Contact>()

        val contentUri: Uri = when (filter.isNullOrEmpty()) {
            true -> ContactsContract.Contacts.CONTENT_URI
            false -> Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter))
        }

        val cursor = context.contentResolver.query(
            contentUri,
            projection,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY
        )

        while (cursor?.moveToNext() == true) {

            val contactId = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val lookupKey = cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
            val displayName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
            val thumbnail = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)

            contacts.add(
                Contact(
                    id = cursor.getLong(contactId),
                    lookupKey = cursor.getString(lookupKey),
                    fullName = cursor.getString(displayName),
                    pictureUrl = cursor.getString(thumbnail)
                )
            )
        }
        cursor?.close()
        return contacts
    }
}
