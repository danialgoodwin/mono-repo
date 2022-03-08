package dev.goodwin.experimental.blockchain

import java.time.Instant

// Idea: For blocks to be valid, its hash has to start with number of 0s
internal data class BlockchainBlock(
    val previousHash: String,
    val transactions: MutableList<Transaction> = mutableListOf(),
//    val data: String,
    val timestamp: Long = Instant.now().toEpochMilli(),
    val nonce: Long = 0,
    var hash: String = ""
) {

    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        return "$previousHash$transactions$timestamp$nonce".hash()
//        return "$previousHash$data$transactions$timestamp$nonce".hash()
    }

    fun addTransaction(transaction: Transaction) : BlockchainBlock {
        if (transaction.isSignatureValid())
            transactions.add(transaction)
        return this
    }

}
