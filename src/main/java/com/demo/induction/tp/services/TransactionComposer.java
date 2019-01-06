package com.demo.induction.tp.services;

import com.demo.induction.tp.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class TransactionComposer {

    @Autowired
    private CSVTransactionProcessor csvTransactionProcessor;
    @Autowired
    private XMLTransactionProcessor xmlTransactionProcessor;

    public ResponseMessage componseXML(InputStream s) {
        xmlTransactionProcessor.importTransactions(s);
        return new ResponseMessage(xmlTransactionProcessor.getImportedTransactions(), xmlTransactionProcessor.validate(), xmlTransactionProcessor.isBalanced());
    }

    public ResponseMessage componseCSV(InputStream s) {
        csvTransactionProcessor.importTransactions(s);
        return new ResponseMessage(csvTransactionProcessor.getImportedTransactions(), csvTransactionProcessor.validate(), csvTransactionProcessor.isBalanced());
    }


}
