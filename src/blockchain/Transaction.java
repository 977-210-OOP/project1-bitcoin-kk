/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

public class Transaction {

    private long senderId;
    private long receiverId;
    private long amount;

    public Transaction(long sender, long receiver, long amount) {
        this.senderId = sender;// id ของผู้ส่ง
        this.receiverId = receiver;// id ของผู้รับ
        this.amount = amount;// จำนวนเงิน
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
