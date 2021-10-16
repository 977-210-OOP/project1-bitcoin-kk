
package blockchain;

public class BlockChain {

    private Block[] blocks = new Block[100];

    private int currentIndex = 0;

    public BlockChain() {

    }

    public void append(Block b) {
        this.blocks[currentIndex] = b;
        currentIndex++;
    }

    public void modifyBlock(Block b, int idx) {
        this.blocks[idx] = b;

    }

    public int firstInvalidBlock() {
        int index = 0;
        long currentPrevHash = 0;

        for (Block block : blocks) {
            if (!(block.isValid())) {
                return index;
            }
            if (index == 0) {
                if (block.getPrevHash() != 0) {
                    return index;
                }

            } else {

                if (block.getPrevHash() != currentPrevHash) {
                    return index;
                }

            }
            index++;
            currentPrevHash = block.getHash();
        }
        return -1;
    }

    public boolean isTxInBlockchain(Transaction tx) {
        if (firstInvalidBlock() != -1) {
            return false;
        }
        for (Block block : blocks) {
            int lenght = block.getTransactionLenght();
            for (int i = 0; i < lenght; i++) {
                Transaction currentTran = block.getTransaction(i);
                if (tx.getReceiverId() == currentTran.getReceiverId() && tx.getSenderId() == currentTran.getSenderId()
                        && tx.getAmount() == currentTran.getAmount()) {
                    return true;
                }
            }

        }
        return false;
    }

}