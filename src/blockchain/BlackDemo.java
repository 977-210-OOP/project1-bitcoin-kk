package blockchain;

public class BlackDemo {

    public static void main(String[] args) {
        Transaction t1 = new Transaction(01, 02, 30);
        Transaction t2 = new Transaction(02, 03, 10);
        Block b1 = new Block(961923);
        b1.addTransaction(t1);
    }
}
