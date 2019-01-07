package com.demo.induction.tp.api;

import com.demo.induction.tp.model.ResponseMessage;
import com.demo.induction.tp.services.TransactionComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/api/transaction",produces = "application/json")
public class TransactionProcessorAPI {


    private final String CSVfilePath = "test.csv";
    private final String XMLfilePath = "test.xml";

    @Autowired
    private TransactionComposer transactionComposer;

    @GetMapping(value = "/csv/process")
    public ResponseEntity<ResponseMessage> processCSV() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(CSVfilePath);
        return new ResponseEntity<>(transactionComposer.componseCSV(is), HttpStatus.OK);
    }

    @GetMapping(value = "/xml/process")
    public ResponseEntity<ResponseMessage> processXML(){
        InputStream is = getClass().getClassLoader().getResourceAsStream(XMLfilePath);
        return new ResponseEntity<>(transactionComposer.componseXML(is), HttpStatus.OK);
    }


}
