/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Block {

    private long timestamp;
    private long prevHash = 0;
    private long hash;
    private long nonce;
    private Transaction[] transactions = new Transaction[10];
    private int currentIndex = 0;// กำหนดตำแหน่งindexเริ่มจาก0
    private boolean isGenesis = false;

    public Block(long timestamp) { // Genesis Block
        this.timestamp = timestamp;// timestamp คือ parameter;
        isGenesis = true;
    }// Conductor มี่ชื่อเหมือนคลาส

    public Block(long timestamp, long prevHash) {// Non-Genesis Block
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

    public void addTransaction(Transaction tx) {// addtransaction current indexเริ่มต้นเป็น0
                                                // อันดับแรกของอาเรย์รับtransaction จากtest
        this.transactions[currentIndex] = tx;// =ชี้ตำแหน่งtransaction ตัวcurrentindexเป็นตำแหน่งarrayในปัจจุบัน
        currentIndex++;
    }

    public Transaction getTransaction(int idx) {
        return this.transactions[idx];//
    }

    public int getTransactionLenght() {
        return currentIndex;
    }

    // Assume all txs are filled
    public void mine(long difficulty) {// method mineทำงานก้ต่อเมื่อ current indexมีค่าcurrent index=10;
        long result;
        long transactionSummery = 0;// public void mine เป้าหมายต้องการหาค่าnounceที่ทำให้สมการเปนจริง
                                    // สมการที่ว่าหาผลรวมsender id ,receiver id,amountของtransaction+nounce
                                    // timestampของblockแล้วจะมีค่าน้อยกว่าdifficulty
        long currentNonce = -999999999;// ค่าcurrentNounceตั้งค่าติดลบที่น้อยที่สุดเท่าที่มีได้

        for (Transaction tran : transactions) {// หาผลรวมของsenderid receiverid
                                               // amount;พอได้ผลรวมแล้วก็จะเอาผลรวมไปคิดในสมการต่อในloop do while;
            transactionSummery = transactionSummery + tran.getAmount() + tran.getReceiverId() + tran.getSenderId();
        }
        do {// เอาผลรวมมาบวกค่าnounce,timestamp,แล้วเอาผลรวมส่งไปให้hash
            // ถ้าผลลัพธ์ยังไม่น้อยกว่าdifficulty
            // มันก็จะทำต่อไปโดยnounceจะเพิ่มขึ้น1;จนกว่าค่าhashจะน้อยกว่าdifficulty
            result = currentNonce + timestamp + transactionSummery;
            this.nonce = currentNonce;// currentnounceเป็นส่วนนึงของสมการที่มีจำนวนที่เพื่มขึ้นเรื่อยๆตามloopจนกว่ามันจะหลุดลูปทำให้สมการด้านบนเปนจิง
            currentNonce++;
            this.hash = hash(result);
        } while (this.hash >= difficulty);
    }

    public boolean isValid() {
        long result = 0;
        long transactionSummery = 0;
        for (Transaction tran : transactions) {
            transactionSummery = transactionSummery + tran.getAmount() + tran.getReceiverId() + tran.getSenderId();
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
