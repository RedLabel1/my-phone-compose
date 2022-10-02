package com.redlabel.ui_common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.redlabel.ui_common.model.SortOption

@ExperimentalMaterial3Api
@Composable
fun FilterTextField(
    filterHint: String,
    onFilterChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    sortOptions: List<SortOption>,
    currentSortOption: SortOption,
) {

    var filter by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    SearchTextField(
        modifier = modifier,
        value = filter,
        onValueChange = { value ->
            filter = value
            onFilterChanged(value.text)
        },
        hint = filterHint
    )
}
