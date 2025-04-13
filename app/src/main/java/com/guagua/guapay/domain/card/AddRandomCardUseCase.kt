package com.guagua.guapay.domain.card

import com.guagua.data.card.Card
import com.guagua.data.card.CardTag
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

/**
 * Use case for adding a random card. Only for testing purposes.
 */
class AddRandomCardUseCase(
    private val addCardUseCase: AddCardUseCase
) {
    private val monthDateFormatter = DateTimeFormatter.ofPattern("MM/dd")

    suspend operator fun invoke(num: Int = 10) = runCatching {
        supervisorScope {
            (1..num).map {
                async {
                    val randomData = LocalDate.of(2025, 1, 1).plusDays(Random.nextLong(365))
                    val tag = CardTag.entries.minus(CardTag.ALL).random()
                    addCardUseCase.invoke(
                        Card(
                            id = "",
                            name = if (tag == CardTag.OTHER) {
                                "My Card"
                            } else {
                                "${tag.name.lowercase().replaceFirstChar { it.uppercase() }} Card"
                            },
                            number = (1..16)
                                .map { ('0'..'9').random() }
                                .joinToString(""),
                            expirationDate = randomData.format(monthDateFormatter),
                            cvv = (1..3)
                                .map { ('0'..'9').random() }
                                .joinToString(""),
                            owner = "Gua Gua",
                            tag = tag
                        )
                    )
                }
            }.awaitAll()
        }
    }
}