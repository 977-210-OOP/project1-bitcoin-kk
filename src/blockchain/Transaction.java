
package blockchain;

public class Transaction {

    private long senderId;
    private long receiverId;
    private long amount;

    public Transaction(long sender, long receiver, long amount) {
        this.senderId = sender;
        this.receiverId = receiver;
        this.amount = amount;
    }

    public long getSenderId() {
        return this.senderId;
    }

    public long getReceiverId() {
        return this.receiverId;
    }

    public long getAmount() {
        return amount;
    }

}
