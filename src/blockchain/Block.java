/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Block {
    
    public Block(long timestamp) {
    }
    
    public Block(long timestamp, long prevHash) {
    }
    
    private long hash(long in) {
        String key = Long.toString(in);

        long hash = 0;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            for (int i=0; i<8; i++) {
                hash <<= 8;
                hash ^= (long) bytes[i] & 0xff;
            }
        } catch(Exception e) {
            System.out.println("This message should not happen. Please contact Lecturer");
            e.printStackTrace();
        }

        return hash;
    }
    
    public void addTransaction(Transaction tx) {

    }
    
    public Transaction getTransaction(int idx) {

    }
    
    // Assume all txs are filled
    public void mine(long difficulty) {
        
    }
    
    public boolean isValid() {
        
    }

    public void setNonce(long n) {

    }

    public long getNonce() {

    }

    public long getTimestamp() {

    }
    
    public long getHash() {
        
    }
    
    public long getPrevHash() {
        
    }

    public void setBlockHash(long h) {

    }

}
