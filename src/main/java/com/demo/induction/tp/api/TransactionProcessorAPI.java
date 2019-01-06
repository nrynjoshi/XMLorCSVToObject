package com.demo.induction.tp.api;

import com.demo.induction.tp.model.ResponseMessage;
import com.demo.induction.tp.services.TransactionComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping(value = "/api/transaction",produces = "application/json")
public class TransactionProcessorAPI {


    private final String CSVfilePath = "./testfile/test.csv";
    private final String XMLfilePath = "./testfile/test.xml";

    @Autowired
    private TransactionComposer transactionComposer;

    @GetMapping(value = "/csv/process")
    public ResponseEntity<ResponseMessage> processCSV() throws Exception {
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        return new ResponseEntity<>(transactionComposer.componseCSV(new FileInputStream(CSVfilePath)), HttpStatus.OK);
    }

    @GetMapping(value = "/xml/process")
    public ResponseEntity<ResponseMessage> processXML() throws FileNotFoundException {
        return new ResponseEntity<>(transactionComposer.componseXML(new FileInputStream(XMLfilePath)), HttpStatus.OK);
    }


}
