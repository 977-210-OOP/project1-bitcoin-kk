
package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.sql.rowset.spi.TransactionalWriter;

public class Block {

    private long timestamp;
    private long prevHash = 0;
    private long hash;
    private long nonce;
    private Transaction[] transactions = new Transaction[10];
    private int currentIndex = 0;
    private boolean isGenesis = false;

    public Block(long timestamp) {
        this.timestamp = timestamp;
    }

    public Block(long timestamp, long prevHash) {
        this.timestamp = timestamp;
        this.prevHash = prevHash;
    }

    private long hash(long in) {
        String key = Long.toString(in);

        long hash = 0;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < 8; i++) {
                hash <<= 8;
                hash ^= (long) bytes[i] & 0xff;
            }
        } catch (Exception e) {
            System.out.println("This message should not happen. Please contact Lecturer");
            e.printStackTrace();
        }

        return hash;
    }

    public void addTransaction(Transaction tx) {
        this.transactions[currentIndex] = tx;
        currentIndex++;
    }

    public Transaction getTransaction(int idx) {
        return this.transactions[idx];
    }

    public int getTransactionLenght() {
        return currentIndex;
    }

    public void mine(long difficulty) {
        long result;
        long transactionSummery = 0;
        long currentNonce = -999999999;

        for (int i = 0; i < currentIndex; i++) {
            transactionSummery = transactionSummery + transactions[i].getAmount() + transactions[i].getReceiverId()
                    + transactions[i].getSenderId();
        }
        do {
            result = currentNonce + timestamp + transactionSummery;
            this.nonce = currentNonce;
            currentNonce++;
            this.hash = hash(result);
        } while (this.hash >= difficulty);
    }

    public boolean isValid() {
        long result = 0;
        long transactionSummery = 0;
        for (int i = 0; i < currentIndex; i++) {
            transactionSummery = transactionSummery + transactions[i].getAmount() + transactions[i].getReceiverId()
                    + transactions[i].getSenderId();
        }
        result = this.nonce + timestamp + transactionSummery;

        return (this.hash == hash(result));
    }

    public void setNonce(long n) {
        this.nonce = n;
    }

    public long getNonce() {
        return this.nonce;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public long getHash() {
        return this.hash;
    }

    public long getPrevHash() {
        return this.prevHash;
    }

    public void setBlockHash(long h) {
        this.hash = h;
    }

}
