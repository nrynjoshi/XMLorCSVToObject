package com.demo.induction.tp.utils;

import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.Violation;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionUtil {

    public static boolean isTypeInvalid(String type) {
        return !StringUtils.equalsIgnoreCase(type, "C") && !StringUtils.equalsIgnoreCase(type, "D");
    }

    public static boolean isAmountInvalid(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isNarrationInvalid(String narration) {
        return !StringUtils.isNotBlank(narration);
    }

    public static boolean isBalancedProcess(List<Transaction> transactionList) {
        BigDecimal creditSum = transactionList.stream().filter(transaction -> StringUtils.equalsIgnoreCase(transaction.getType(), "C")).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal debitSum = transactionList.stream().filter(transaction -> StringUtils.equalsIgnoreCase(transaction.getType(), "D")).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return creditSum.compareTo(debitSum) == 0;
    }

    public static List<Violation> validationProcess(List<Transaction> transactionList) {
        List<Violation> violationList = new ArrayList<>();
        int order = 1;
        for (Transaction transaction : transactionList) {
            if (isAmountInvalid(transaction.getAmount())) {
                violationList.add(new Violation(order, "amount", "Amount  is zero or empty"));
            }
            if (isNarrationInvalid(transaction.getNarration())) {
                violationList.add(new Violation(order, "narration", "Narration is not filled up"));
            }
            if (isTypeInvalid(transaction.getType())) {
                violationList.add(new Violation(order, "type", "Type is not valid"));
            }
            order++;
        }
        return violationList;
    }

}
