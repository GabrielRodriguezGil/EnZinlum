package edu.teamrocket;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.management.InvalidAttributeValueException;


public class TokenContract {
    private PublicKey ownerPK;
    private Address owner;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;
    private Double totalTokensSold = 0d;
    public Double tokenPrice = 0d;
    private HashMap<PublicKey, Double> balances = new HashMap<>();

    public TokenContract(Address owner){
        this.owner = owner;
        this.ownerPK = owner.getPK();
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Address owner() {
        return this.owner;
    }

    public String name() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public double totalSupply() {
        return this.totalSupply;
    }

    public void setTokenPrice(double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public Double getTokenPrice() {
        return tokenPrice;
    }

    public Map<PublicKey, Double> getBalances() {
        return balances;
    }

    public void addOwner(PublicKey PK, Double unit){
        getBalances().putIfAbsent(PK, unit);
    }

    public int numOwners(){
        return getBalances().size();
    }

     public Double balanceOf(PublicKey owner) {
        return this.getBalances().getOrDefault(owner, 0d);
    }

    public void transfer(PublicKey recipient, Double units) {
        try {
            require(balanceOf(ownerPK) >= units);
            this.getBalances().compute(ownerPK, (pk, tokens) -> tokens - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (InvalidAttributeValueException e) {
        }      
    };

    public void transfer(PublicKey sender, PublicKey recipient, Double units) {
        try {
            require(balanceOf(sender) >= units);
            this.getBalances().put(sender, balanceOf(sender) - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (InvalidAttributeValueException e) {
        }   
    }

    void require(Boolean holds) throws InvalidAttributeValueException {
        if (! holds) {
            throw new InvalidAttributeValueException(
                "No tienes los suficientes tokens para realizar esta transacción.");
        }
    }

    public String owners() {
        StringBuilder owners = new StringBuilder();
        for (PublicKey pk : this.getBalances().keySet()) {
            if (!pk.equals(this.ownerPK)) {
                    owners.append("Owner: ")
                        .append(pk.hashCode())
                        .append("\s")
                        .append(this.getBalances().get(pk))
                        .append("\s")
                        .append(this.symbol())
                        .append("\n");
            }
        }
        return owners.toString();
    }

    public int totalTokensSold() {
        this.getBalances().forEach((pk, units) -> this.totalTokensSold += units);
        this.totalTokensSold -= balanceOf(ownerPK);
        return this.totalTokensSold.intValue();
    }

    void payable(PublicKey recipient, Double enziniums) {
        try {
            require(enziniums >= this.getTokenPrice());
            Double units = Math.floor(enziniums / tokenPrice);
            transfer(recipient, units);
            this.owner.transferEZI(units * tokenPrice);
        } catch (InvalidAttributeValueException e) {
        }
    }

}
