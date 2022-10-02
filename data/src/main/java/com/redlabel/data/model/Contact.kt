package com.redlabel.data.model

data class Contact(
    val id: Long,
    val lookupKey: String?,
    val fullName: String,
    val pictureUrl: String?,
    val email: String? = null,
    val phoneNumber: String? = null,
)
