package com.guagua.data.card.local

class CardLocalDataSource internal constructor(
    private val dao: CardDao
) : CardDao by dao