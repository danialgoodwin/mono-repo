package dev.goodwin.experimental.blockchain

internal class Blockchain {

    var UTXO: MutableMap<String, TransactionOutput> = mutableMapOf()

    private val difficulty = 5
    private val validPrefix = "0".repeat(difficulty)
    private var blocks: MutableList<BlockchainBlock> = mutableListOf()

    fun add(block: BlockchainBlock) : BlockchainBlock {
        val minedBlock = if (isMined(block)) block else mine(block)
        blocks.add(minedBlock)
        return minedBlock
    }

    fun isValid() : Boolean {
        when {
            blocks.isEmpty() -> return true
            blocks.size == 1 -> return blocks[0].hash == blocks[0].calculateHash()
            else -> {
                for (i in 1 until blocks.size) {
                    val previousBlock = blocks[i - 1]
                    val currentBlock = blocks[i]
                    when {
                        currentBlock.hash != currentBlock.calculateHash() -> return false
                        currentBlock.previousHash != previousBlock.calculateHash() -> return false
                        !(isMined(previousBlock) && isMined(currentBlock)) -> return false
                    }
                }
                return true
            }
        }
    }

    private fun isMined(block: BlockchainBlock) : Boolean {
        return block.hash.startsWith(validPrefix)
    }

    private fun mine(block: BlockchainBlock) : BlockchainBlock {
        println("Mining: $block")
        var minedBlock = block.copy()
        while (!isMined(minedBlock)) {
            minedBlock = minedBlock.copy(nonce = minedBlock.nonce + 1)
        }
        println("Mined : $minedBlock")
        updateUTXO(minedBlock)
        return minedBlock
    }

    private fun updateUTXO(block: BlockchainBlock) {
        block.transactions.flatMap { it.inputs }.map { it.hash }.forEach { UTXO.remove(it) }
        UTXO.putAll(block.transactions.flatMap { it.outputs }.associateBy { it.hash })
    }

}
