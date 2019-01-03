package com.demo.induction.tp;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class CsvTransactionProcessorTest {

    private CSVTransactionProcessor transactionProcessor;
    private String filePath = "./testfile/test.csv";

    @Before
    public void setUp() {
        transactionProcessor = new CSVTransactionProcessor();
        try {
            transactionProcessor.importTransactions(new FileInputStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransactionList() {
        List<Transaction> transactionList = transactionProcessor.getImportedTransactions();

        assertEquals("Total Transaction Test ", 8, transactionList.size());

        assertThat("Transaction Object Test ", transactionList, hasItem(
                new Transaction("D", new BigDecimal(1920.00), "Salary")
        ));

    }

    @Test
    public void testValidation() {
        List<Violation> violationList = transactionProcessor.validate();
        assertEquals("Total Validation Test ", 6, violationList.size());

        assertThat("Validation Object Test ", violationList, hasItem(
                new Violation(7, "type", "Type is not valid")
        ));
    }

    @Test
    public void testBalanced() {
        assertTrue("Balanced Amount Test  ", transactionProcessor.isBalanced());
    }


}
