/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        // (1) calling isValid() method on this block returns false
        // or-- (2) this block is the very first block in the blockchain but it is not a
        // genesis
        // block (its previous hash value is not 0) or (3) this block’s previous hash
        // value is
        // different from the previous block’s hash value. If all blocks are valid,
        // return -1.

        int index = 0;
        long currentPrevHash = 0;

        for (Block block : blocks) {
            if (!(block.isValid())) {
                return index;
            }
            if (index == 0) {//// (2)
                if (block.getPrevHash() != 0) {
                    return index;
                }

            } else {// (3)

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