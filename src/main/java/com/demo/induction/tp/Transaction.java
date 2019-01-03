package com.demo.induction.tp;

import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;


public class Transaction {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private BigDecimal amount;
    @XmlAttribute
    private String narration;

    public Transaction(String type, BigDecimal amount, String narration) {
        this.type = type;
        this.amount = amount;
        this.narration = narration;
    }

    public Transaction() {
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getNarration() {
        return narration;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", narration='" + narration + '\'' +
                '}';
    }
}
