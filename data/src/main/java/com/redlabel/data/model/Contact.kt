package com.redlabel.data.model

import java.util.*

data class Contact(
    val id: Long,
    val lookupKey: String,
    val structuredName: StructuredName? = null,
    val phones: List<Phone> = emptyList(),
    val emails: List<Email> = emptyList(),
    val organizations: List<Organization> = emptyList(),
    val instantMessaging: List<InstantMessaging> = emptyList(),
    val note: String? = null,
    val structuredPostal: List<StructuredPostal> = emptyList(),
    val websites: List<Website> = emptyList(),
    val events: List<Event> = emptyList(),
    val relations: List<Relation> = emptyList(),
    val sipAddresses: List<SipAddress> = emptyList(),
    val pictureUrl: String? = null,
)

data class StructuredName(
    val displayName: String,
    val givenName: String,
    val familyName: String?,
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
    TYPE_MAIN,

    TYPE_CALLBACK,
    TYPE_CAR,
    TYPE_COMPANY_MAIN,
    TYPE_ISDN,
    TYPE_OTHER_FAX,
    TYPE_RADIO,
    TYPE_TELEX,
    TYPE_TTY_TDD,
    TYPE_WORK_MOBILE,
    TYPE_WORK_PAGER,
    TYPE_ASSISTANT,
    TYPE_MMS,
}

val phoneTypeDictionary = mapOf(
     0 to PhoneType.TYPE_CUSTOM,
     1 to PhoneType.TYPE_HOME,
     2 to PhoneType.TYPE_MOBILE,
     3 to PhoneType.TYPE_WORK,
     4 to PhoneType.TYPE_FAX_WORK,
     5 to PhoneType.TYPE_FAX_HOME,
     6 to PhoneType.TYPE_PAGER,
     7 to PhoneType.TYPE_OTHER,
     12 to PhoneType.TYPE_MAIN,

     8 to PhoneType.TYPE_CALLBACK,
     9 to PhoneType.TYPE_CAR,
     10 to PhoneType.TYPE_COMPANY_MAIN,
     11 to PhoneType.TYPE_ISDN,
     13 to PhoneType.TYPE_OTHER_FAX,
     14 to PhoneType.TYPE_RADIO,
     15 to PhoneType.TYPE_TELEX,
     16 to PhoneType.TYPE_TTY_TDD,
     17 to PhoneType.TYPE_WORK_MOBILE,
     18 to PhoneType.TYPE_WORK_PAGER,
     19 to PhoneType.TYPE_ASSISTANT,
     20 to PhoneType.TYPE_MMS,
)

data class Email(
    val email: String,
    val type: EmailType
)

enum class EmailType {
    TYPE_CUSTOM,
    TYPE_HOME,
    TYPE_WORK,
    TYPE_OTHER,
    TYPE_MOBILE,
}

val emailTypeDictionary = mapOf(
    0 to EmailType.TYPE_CUSTOM,
    1 to EmailType.TYPE_HOME,
    2 to EmailType.TYPE_WORK,
    3 to EmailType.TYPE_OTHER,
    4 to EmailType.TYPE_MOBILE,
)

data class Organization(
    val company: String,
    val type: OrganizationType
)

enum class OrganizationType {
    TYPE_CUSTOM,
    TYPE_WORK,
    TYPE_OTHER,
}

val organizationTypeDictionary = mapOf(
    0 to OrganizationType.TYPE_CUSTOM,
    1 to OrganizationType.TYPE_WORK,
    2 to OrganizationType.TYPE_OTHER,
)

data class InstantMessaging(
    val name: String,
    val provider: String,
    val type: InstantMessagingType
)

enum class InstantMessagingType {
    TYPE_CUSTOM,

    PROTOCOL_AIM,
    PROTOCOL_MSN,
    PROTOCOL_YAHOO,
    PROTOCOL_SKYPE,
    PROTOCOL_QQ,
    PROTOCOL_GOOGLE_TALK,
    PROTOCOL_ICQ,
    PROTOCOL_JABBER,
    PROTOCOL_NETMEETING,
}

val instantMessagingTypeDictionary = mapOf(
    -1 to InstantMessagingType.TYPE_CUSTOM,

    0 to InstantMessagingType.PROTOCOL_AIM,
    1 to InstantMessagingType.PROTOCOL_MSN,
    2 to InstantMessagingType.PROTOCOL_YAHOO,
    3 to InstantMessagingType.PROTOCOL_SKYPE,
    4 to InstantMessagingType.PROTOCOL_QQ,
    5 to InstantMessagingType.PROTOCOL_GOOGLE_TALK,
    6 to InstantMessagingType.PROTOCOL_ICQ,
    7 to InstantMessagingType.PROTOCOL_JABBER,
    8 to InstantMessagingType.PROTOCOL_NETMEETING,
)

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
    TYPE_OTHER,
}

val structuredPostalTypeDictionary = mapOf(
    0 to StructuredPostalType.TYPE_CUSTOM,
    1 to StructuredPostalType.TYPE_HOME,
    2 to StructuredPostalType.TYPE_WORK,
    3 to StructuredPostalType.TYPE_OTHER,
)

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
    TYPE_OTHER,
}

val websiteTypeDictionary = mapOf(
    WebsiteType.TYPE_CUSTOM to 0,
    WebsiteType.TYPE_HOMEPAGE to 1,
    WebsiteType.TYPE_BLOG to 2,
    WebsiteType.TYPE_PROFILE to 3,
    WebsiteType.TYPE_HOME to 4,
    WebsiteType.TYPE_WORK to 5,
    WebsiteType.TYPE_FTP to 6,
    WebsiteType.TYPE_OTHER to 7,
)

data class Event(
    val date: Date,
    val type: EventType
)

enum class EventType {
    TYPE_CUSTOM,
    TYPE_ANNIVERSARY,
    TYPE_OTHER,
    TYPE_BIRTHDAY,
}

val eventTypeDictionary = mapOf(
    EventType.TYPE_CUSTOM to 0,
    EventType.TYPE_ANNIVERSARY to 1,
    EventType.TYPE_OTHER to 2,
    EventType.TYPE_BIRTHDAY to 3,
)

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
    TYPE_SPOUSE,
}

val relationTypeDictionary = mapOf(
    RelationType.TYPE_CUSTOM to 0,
    RelationType.TYPE_ASSISTANT to 1,
    RelationType.TYPE_BROTHER to 2,
    RelationType.TYPE_CHILD to 3,
    RelationType.TYPE_DOMESTIC_PARTNER to 4,
    RelationType.TYPE_FATHER to 5,
    RelationType.TYPE_FRIEND to 6,
    RelationType.TYPE_MANAGER to 7,
    RelationType.TYPE_MOTHER to 8,
    RelationType.TYPE_PARENT to 9,
    RelationType.TYPE_PARTNER to 10,
    RelationType.TYPE_REFERRED_BY to 11,
    RelationType.TYPE_RELATIVE to 12,
    RelationType.TYPE_SISTER to 13,
    RelationType.TYPE_SPOUSE to 14,
)

data class SipAddress(
    val sip: String,
    val type: SipAddressType
)

enum class SipAddressType {
    TYPE_CUSTOM,
    TYPE_HOME,
    TYPE_WORK,
    TYPE_OTHER,
}

val sipAddressTypeDictionary = mapOf(
    SipAddressType.TYPE_CUSTOM to 0,
    SipAddressType.TYPE_HOME to 1,
    SipAddressType.TYPE_WORK to 2,
    SipAddressType.TYPE_OTHER to 3,
)
