package com.demo.induction.tp.services;

import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.TransactionList;
import com.demo.induction.tp.model.Violation;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

import static com.demo.induction.tp.utils.TransactionUtil.isBalancedProcess;
import static com.demo.induction.tp.utils.TransactionUtil.validationProcess;

@Service
public class XMLTransactionProcessor implements TransactionProcessor {

    private static List<Transaction> transactionList;


    @Override
    public void importTransactions(InputStream is) {
        try {

            JAXBContext context = JAXBContext.newInstance(TransactionList.class);
            Unmarshaller um = context.createUnmarshaller();
            TransactionList rootTransaction = (TransactionList) um.unmarshal(is);
            transactionList = (List<Transaction>) rootTransaction.getTransaction();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getImportedTransactions() {
        return transactionList;
    }

    @Override
    public List<Violation> validate() {
        return validationProcess(transactionList);
    }

    @Override
    public boolean isBalanced() {
        return isBalancedProcess(transactionList);
    }

}
