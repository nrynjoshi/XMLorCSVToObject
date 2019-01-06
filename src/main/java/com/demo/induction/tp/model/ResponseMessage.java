package com.demo.induction.tp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ResponseMessage {

    private int statusCode=200;
    private String message="Operation completed";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Transaction> transactions;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Violation> violations;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Boolean balanced=false;

    public ResponseMessage() {
    }

    public ResponseMessage(List<Transaction> transactions, List<Violation> violations, boolean isBalanced) {
        this.transactions = transactions;
        this.violations = violations;
        this.balanced = isBalanced;
    }

    public ResponseMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public Boolean getBalanced() {
        return balanced;
    }

    public void setBalanced(Boolean balanced) {
        this.balanced = balanced;
    }
}
