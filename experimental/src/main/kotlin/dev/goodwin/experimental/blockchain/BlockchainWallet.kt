package dev.goodwin.experimental.blockchain

import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey

internal data class BlockchainWallet(val publicKey: PublicKey, val privateKey: PrivateKey, val blockchain: Blockchain) {

    companion object {
        fun create(blockchain: Blockchain): BlockchainWallet {
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(2048)
            val keyPair = generator.generateKeyPair()
            return BlockchainWallet(keyPair.public, keyPair.private, blockchain)
        }
    }

    val balance: Int get() {
        return getMyTransactions().sumBy { it.amount }
    }

    private fun getMyTransactions() : Collection<TransactionOutput> {
        return blockchain.UTXO.filterValues { it.isMine(publicKey) }.values
    }

    fun sendFundsTo(recipient: PublicKey, amountToSend: Int) : Transaction {
        if (amountToSend > balance) {
            throw IllegalArgumentException("Insufficient funds")
        }
        val tx = Transaction.create(sender = publicKey, recipient = publicKey, amount = amountToSend)
        tx.outputs.add(TransactionOutput(recipient = recipient, amount = amountToSend, transactionHash = tx.hash))

        var collectedAmount = 0
        for (myTx in getMyTransactions()) {
            collectedAmount += myTx.amount
            tx.inputs.add(myTx)

            if (collectedAmount > amountToSend) {
                val change = collectedAmount - amountToSend
                tx.outputs.add(TransactionOutput(recipient = publicKey, amount = change, transactionHash = tx.hash))
            }

            if (collectedAmount >= amountToSend) {
                break
            }
        }
        return tx.sign(privateKey)
    }
}