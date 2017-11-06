import org.scalatest.FunSuite

class BlockTests extends FunSuite {
  test("The first block in the chain is the genesis block") {
    val firstBlock = Block.blockChain.head
    assert(firstBlock.index == 0)
  }

  test("mine the next block") {
    val currentIndex = 5
    val currentBlock = Block(5, "current block hash", "current block data")
    val nextBlock = currentBlock.mineNextBlock("next block data")
    assert(nextBlock.index == currentIndex + 1)
    assert(Block.blockChain.head == nextBlock)
  }
}
