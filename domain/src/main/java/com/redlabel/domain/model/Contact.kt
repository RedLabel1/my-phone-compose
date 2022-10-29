package com.redlabel.domain.model

import java.util.*

data class Contact(
//    val id: Long,
//    val lookupKey: String,
//    val structuredName: StructuredName? = null,
//    val phones: List<Phone> = emptyList(),
//    val emails: List<Email> = emptyList(),
//    val organizations: List<Organization> = emptyList(),
//    val instantMessaging: List<InstantMessaging> = emptyList(),
//    val note: String? = null,
//    val structuredPostal: List<StructuredPostal> = emptyList(),
//    val websites: List<Website> = emptyList(),
//    val events: List<Event> = emptyList(),
//    val relations: List<Relation> = emptyList(),
//    val sipAddresses: List<SipAddress> = emptyList(),
    val pictureUrl: String? = null,
)

data class StructuredName(
    val displayName: String,
    val givenName: String,
    val FamilyName: String?,
    val middleName: String?,
    val prefix: String?,
    val suffix: String?
)

data class Phone(
    val number: String,
    val normalizedNumber: String,
    val type: PhoneType
)

enum class PhoneType {
    TYPE_CUSTOM,
    TYPE_HOME,
    TYPE_MOBILE,
    TYPE_WORK,
    TYPE_FAX_WORK,
    TYPE_FAX_HOME,
    TYPE_PAGER,
    TYPE_OTHER,
    TYPE_MAIN
}

data class Email(
    val email: String,
    val type: EmailType
)

enum class EmailType {
    TYPE_CUSTOM,
    TYPE_HOME,
    TYPE_WORK,
    TYPE_OTHER,
    TYPE_MOBILE
}

data class Organization(
    val company: String,
    val type: OrganizationType
)

enum class OrganizationType {
    TYPE_CUSTOM,
    TYPE_WORK,
    TYPE_OTHER
}

data class InstantMessaging(
    val name: String,
    val provider: String,
    val type: InstantMessagingType
)

enum class InstantMessagingType {
    TYPE_CUSTOM
}

data class StructuredPostal(
    val formattedAddress: String,
    val street: String?,
    val postalCode: String?,
    val city: String?,
    val region: String?,
    val country: String?,
    val provider: String,
    val type: StructuredPostalType
)

enum class StructuredPostalType {
    TYPE_CUSTOM,
    TYPE_HOME,
    TYPE_WORK,
    TYPE_OTHER
}

data class Website(
    val url: String,
    val type: WebsiteType
)

enum class WebsiteType {
    TYPE_CUSTOM,
    TYPE_HOMEPAGE,
    TYPE_BLOG,
    TYPE_PROFILE,
    TYPE_HOME,
    TYPE_WORK,
    TYPE_FTP,
    TYPE_OTHER
}

data class Event(
    val date: Date,
    val type: EventType
)

enum class EventType {
    TYPE_CUSTOM,
    TYPE_ANNIVERSARY,
    TYPE_OTHER,
    TYPE_BIRTHDAY
}

data class Relation(
    val relationship: String,
    val type: RelationType
)

enum class RelationType {
    TYPE_CUSTOM,
    TYPE_ASSISTANT,
    TYPE_BROTHER,
    TYPE_CHILD,
    TYPE_DOMESTIC_PARTNER,
    TYPE_FATHER,
    TYPE_FRIEND,
    TYPE_MANAGER,
    TYPE_MOTHER,
    TYPE_PARENT,
    TYPE_PARTNER,
    TYPE_REFERRED_BY,
    TYPE_RELATIVE,
    TYPE_SISTER,
    TYPE_SPOUSE
}

data class SipAddress(
    val sip: String,
    val type: SipAddressType
)

enum class SipAddressType {
    TYPE_CUSTOM,
    TYPE_HOME,
    TYPE_WORK,
    TYPE_OTHER
}
