package com.demo.induction.tp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "TransactionList")
public class TransactionList {

    private Collection<Transaction> Transaction;

    public Collection<Transaction> getTransaction() {
        return Transaction;
    }

    @XmlElement(name = "Transaction")
    public void setTransaction(Collection<Transaction> Transaction) {
        this.Transaction = Transaction;
    }
}
