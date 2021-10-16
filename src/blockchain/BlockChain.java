
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

        long currentPrevHash = 0;

        for (int i = 0; i < currentIndex; i++) {
            if (!(blocks[i].isValid())) {
                return i;
            }
            if (i == 0) {
                if (blocks[i].getPrevHash() != 0) {
                    return i;
                }
            } else {
                if (blocks[i].getPrevHash() != currentPrevHash) {
                    return i;
                }
            }
            currentPrevHash = blocks[i].getHash();

        }
        return -1;
    }

    public boolean isTxInBlockchain(Transaction tx) {
        if (firstInvalidBlock() != -1) {
            return false;
        }
        for (int i = 0; i < currentIndex; i++) {
            int lenght = blocks[i].getTransactionLenght();
            for (int j = 0; j < lenght; j++) {
                Transaction currentTran = blocks[i].getTransaction(j);
                if (tx.getReceiverId() == currentTran.getReceiverId() && tx.getSenderId() == currentTran.getSenderId()
                        && tx.getAmount() == currentTran.getAmount()) {
                    return true;
                }
            }

        }
        return false;
    }

}