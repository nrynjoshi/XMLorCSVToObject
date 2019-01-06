package com.demo.induction.tp.services;

import com.demo.induction.tp.model.Violation;
import com.demo.induction.tp.model.Transaction;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface TransactionProcessor {

    void importTransactions(InputStream is) throws UnsupportedEncodingException;

    List<Transaction> getImportedTransactions();

    List<Violation> validate();

    boolean isBalanced();
}
