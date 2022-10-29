package com.redlabel.domain.mapper

import com.redlabel.data.model.Contact as ContactDataModel
import com.redlabel.domain.Mapper
import com.redlabel.domain.model.Contact
import javax.inject.Inject

class ContactMapper @Inject constructor(): Mapper<ContactDataModel, Contact>() {

    override fun toDomain(dataModel: ContactDataModel) = with(dataModel) {
        Contact(pictureUrl)
    }
}
