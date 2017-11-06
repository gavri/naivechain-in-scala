class Block(val index: Int, val hash: String, val blockData: String) {
  val timestamp = Epoch.secondsToNow()
  def mineNextBlock(blockData: String) = {
    val nextHash = {
      val toBeHashed: String = s"$index$hash$timestamp$blockData"
      Block.hash(toBeHashed)
    }
    val newBlock = Block(index + 1, nextHash, blockData)
    Block.addBlockToChain(newBlock)
    newBlock
  }
}

object Block {
  def apply(index: Int, hash: String, blockData: String): Block = new Block(index, hash, blockData)
  private val genesisBlock = Block(0, "", "")
  var blockChain = List(genesisBlock)

  def addBlockToChain(block: Block): Unit = {
    blockChain = block :: blockChain
  }

  def hash(content: String): String = {
    val m = java.security.MessageDigest.getInstance("SHA-256").digest(content.getBytes("UTF-8"))
    m.map("%02x".format(_)).mkString
  }
}

object Epoch {
  def secondsToNow() = System.currentTimeMillis / 1000
}
