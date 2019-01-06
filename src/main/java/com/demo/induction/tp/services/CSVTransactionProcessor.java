package com.demo.induction.tp.services;

import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.Violation;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.demo.induction.tp.utils.TransactionUtil.isBalancedProcess;
import static com.demo.induction.tp.utils.TransactionUtil.validationProcess;

@Service
public class CSVTransactionProcessor implements TransactionProcessor {

    private static List<Transaction> transactionList;


    @Override
    public void importTransactions(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        Pattern pattern = Pattern.compile(",");
        transactionList = in.lines().map(line -> {
            String[] x = pattern.split(line);
            if(x.length==3){
                return new Transaction(x[0], new BigDecimal(x[1]), x[2]);
            }else if(x.length ==2){
                return new Transaction(x[0], new BigDecimal(x[1]), null);
            }else if(x.length ==1){
                return new Transaction(x[0], BigDecimal.ZERO, null);
            }
            return null;
        }).filter(transaction -> transaction!=null).collect(Collectors.toList());

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
