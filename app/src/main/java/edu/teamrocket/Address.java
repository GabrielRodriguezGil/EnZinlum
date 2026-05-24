package edu.teamrocket;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {
    public PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;
    private String symbol = "EZI";


    Address(){
    }

    boolean isSKpresent(){
        return SK != null;
    }

    public void generateKeyPair(){
        KeyPair pair = GenSig.generateKeyPair();
        this.SK = pair.getPrivate();
        setPK(pair.getPublic());
    }

    void transferEZI(double enziniums){
        this.balance += enziniums;
    }

    private void setPK(PublicKey PK){
        this.PK = PK;
    }

    @Override
    public String toString() {
        return "PublicKey: " + this.PK.hashCode();
    }

    public double getBalance() {
        return balance;
    }

    public PublicKey getPK() {
        return PK;
    }

    public void send(TokenContract contract, Double enziniums){
        if (enziniums <= this.balance) {
            contract.payable(getPK(), enziniums);
            this.balance -= enziniums;
        }
    }
}
