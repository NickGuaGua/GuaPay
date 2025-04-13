package com.guagua.guapay.ui.cards.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guagua.data.card.CardTag
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.appbar.DetailAppBar
import com.guagua.guapay.ui.common.appbar.NavigationType
import com.guagua.guapay.ui.common.button.PrimaryButton
import com.guagua.guapay.ui.common.extension.text
import com.guagua.guapay.ui.theme.AppColor
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel
import java.time.Month
import java.util.Calendar

@Composable
fun AddCardScreen(
    modifier: Modifier = Modifier,
    viewModel: AddCardViewModel = koinViewModel(),
    navigationType: NavigationType = NavigationType.Back,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsStateWithLifecycle()

    when (event) {
        is AddCardScreenEvent.Success -> {
            viewModel.consumeEvent()
            onBack()
        }

        else -> Unit
    }

    AddCardScreenContent(
        modifier,
        state,
        navigationType,
        onBack,
        viewModel::addCard,
        viewModel::setCardName,
        viewModel::setCardOwner,
        viewModel::setCardNumber,
        viewModel::setExpireMonth,
        viewModel::setExpireYear,
        viewModel::setCvv,
        viewModel::setTag
    )
}

@Composable
private fun AddCardScreenContent(
    modifier: Modifier = Modifier,
    state: AddCardScreenUiState,
    navigationType: NavigationType = NavigationType.Back,
    onBack: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onCardNameChange: (String) -> Unit = {},
    onCardOwnerChange: (String) -> Unit = {},
    onCardNumberChange: (String) -> Unit = {},
    onExpireMonthChange: (String) -> Unit = {},
    onExpireYearChange: (String) -> Unit = {},
    onCvvChange: (String) -> Unit = {},
    onTagChange: (CardTag) -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(LocalColor.current.surface.background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        DetailAppBar(
            title = stringResource(R.string.add_card_title),
            navigationType = navigationType
        ) {
            onBack()
        }

        Column(
            modifier = Modifier.padding(LocalSpace.current.margin.compact),
            verticalArrangement = Arrangement.spacedBy(LocalSpace.current.margin.compact)
        ) {
            TextInput(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.input_card_name),
                value = state.cardName.value,
                hint = stringResource(R.string.input_card_name_hint),
                isError = state.cardName.isValid == false,
                isNecessary = true,
                onValueChange = { onCardNameChange(it) }
            )
            TextInput(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.input_card_owner),
                value = state.cardOwner.value,
                hint = stringResource(R.string.input_card_owner_hint),
                isError = state.cardOwner.isValid == false,
                isNecessary = true,
                onValueChange = { onCardOwnerChange(it) }
            )
            TextInput(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.input_card_number),
                value = state.cardNumber.value,
                hint = stringResource(R.string.input_card_number_hint),
                isError = state.cardNumber.isValid == false,
                isNecessary = true,
                keyboardType = KeyboardType.Number,
                visualTransformation = CreditCardVisualTransformation(),
                onValueChange = {
                    if (it.length <= 16) onCardNumberChange(it)
                }
            )
            ExpireDateRow(
                modifier = Modifier.fillMaxWidth(),
                month = state.expireMonth.value.takeIf { it.isNotEmpty() },
                year = state.expireYear.value.takeIf { it.isNotEmpty() },
                isMonthError = state.expireMonth.isValid == false,
                isYearError = state.expireYear.isValid == false,
                onMonthSelected = { onExpireMonthChange(it) },
                onYearSelected = { onExpireYearChange(it) }
            )
            TextInput(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.input_card_cvv),
                value = state.cvv.value,
                hint = stringResource(R.string.input_card_cvv_hint),
                isError = state.cvv.isValid == false,
                isNecessary = true,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    if (it.length <= 3) onCvvChange(it)
                }
            )
            CardTagRow(
                modifier = Modifier.fillMaxWidth(),
                tag = state.tag.value,
                onTagChange = { onTagChange(it) }
            )
            Spacer(Modifier.height(32.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.add_card),
                isLoading = state.isLoading,
                onClick = onAddClick
            )
        }
    }
}

@Composable
fun ExpireDateRow(
    modifier: Modifier = Modifier,
    month: String? = null,
    year: String? = null,
    isMonthError: Boolean = false,
    isYearError: Boolean = false,
    onMonthSelected: (String) -> Unit = {},
    onYearSelected: (String) -> Unit = {},
) {
    val currentYear = remember { Calendar.getInstance().get(Calendar.YEAR) }
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.input_card_expiration_date) + "*",
            style = LocalTypography.current.titleSmall,
            color = LocalColor.current.text.primaryBlack,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            TextDropdown(
                modifier = Modifier.weight(1f),
                selected = month,
                hint = stringResource(R.string.input_card_expiration_month_hint),
                options = (1..12).map { it.toString() },
                isError = isMonthError,
                onSelect = onMonthSelected
            )
            Spacer(modifier = Modifier.width(10.dp))
            TextDropdown(
                modifier = Modifier.weight(1f),
                selected = year,
                hint = stringResource(R.string.input_card_expiration_year_hint),
                options = (currentYear - 10..currentYear + 30).map { it.toString() },
                isError = isYearError,
                onSelect = onYearSelected
            )
        }
    }
}

@Composable
fun CardTagRow(
    modifier: Modifier = Modifier,
    tag: CardTag,
    onTagChange: (CardTag) -> Unit = {},
) {
    val tags = remember {
        CardTag.entries.minus(CardTag.ALL)
    }
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.input_card_expiration_date) + "*",
            style = LocalTypography.current.titleSmall,
            color = LocalColor.current.text.primaryBlack,
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextDropdown(
            modifier = Modifier.fillMaxWidth(),
            selected = tag.text(),
            hint = "",
            options = tags.map { it.text() },
            isError = false,
            onSelect = { onTagChange(CardTag.from(it)) }
        )
    }
}

@Composable
private fun TextDropdown(
    modifier: Modifier = Modifier,
    selected: String? = null,
    hint: String,
    options: List<String>,
    isError: Boolean = false,
    onSelect: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    BoxWithConstraints(
        modifier = modifier.clickable { expanded = !expanded }
    ) {
        val maxWidth = this.maxWidth
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(LocalSpace.current.radius.compact))
                .border(
                    1.dp,
                    if (isError) LocalColor.current.base.red._500 else AppColor.textInputBorder,
                    RoundedCornerShape(LocalSpace.current.radius.compact)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 14.dp
                ),
        ) {
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = selected ?: hint,
                style = LocalTypography.current.bodyLarge.copy(
                    lineHeight = 20.sp
                ),
                color = selected?.let { LocalColor.current.text.primaryBlack } ?: Color(0xFFB8B8B8),
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = null,
                tint = LocalColor.current.text.primaryBlack,
            )
        }
        DropdownMenu(
            modifier = Modifier.width(maxWidth),
            expanded = expanded,
            containerColor = LocalColor.current.base.gray._100,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(
                    modifier = Modifier,
                    text = { Text(it) },
                    onClick = {
                        expanded = false
                        onSelect(it)
                    },
                )
            }
        }
    }
}

@Composable
private fun TextInput(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    hint: String,
    isError: Boolean = false,
    isNecessary: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = if (isNecessary) {
                "$title*"
            } else {
                title
            },
            style = LocalTypography.current.titleSmall,
            color = LocalColor.current.text.primaryBlack,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(LocalSpace.current.radius.compact))
                .border(
                    1.dp,
                    if (isError) LocalColor.current.base.red._500 else AppColor.textInputBorder,
                    RoundedCornerShape(LocalSpace.current.radius.compact)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 14.dp
                )
        ) {
            if (value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = hint,
                    style = LocalTypography.current.bodyLarge.copy(
                        lineHeight = 20.sp
                    ),
                    color = Color(0xFFB8B8B8),
                )
            }
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType
                ),
                visualTransformation = visualTransformation,
                textStyle = LocalTypography.current.bodyLarge.copy(
                    lineHeight = 20.sp,
                    color = LocalColor.current.text.primaryBlack
                ),
                onValueChange = {
                    onValueChange(it)
                },
            )
        }
    }
}

class CreditCardVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(16)
        val formatted = trimmed.chunked(4).joinToString(" ")
        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // 每 4 個字元插入一個空格，所以 offset 後要加上 (offset / 4)
                val transformedOffset = offset + (offset / 4)
                return transformedOffset.coerceAtMost(formatted.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                // 每 5 個字元包含一個空格，所以 offset 減去空格數
                val originalOffset = offset - (offset / 5)
                return originalOffset.coerceAtMost(trimmed.length)
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetTranslator)
    }
}


@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    TextInput(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        title = "Card Number",
        value = "",
        hint = "Enter your card number",
        isNecessary = true,
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun TextDropdownPreview() {
    TextDropdown(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        selected = null,
        hint = "Month",
        options = (1..12).map { Month.entries[it - 1].name }
    )
}

@Preview(showBackground = true)
@Composable
fun AddCardScreenContentPreview() {
    AddCardScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = AddCardScreenUiState(),
    )
}