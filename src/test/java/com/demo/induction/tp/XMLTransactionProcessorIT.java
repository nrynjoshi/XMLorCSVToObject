package com.demo.induction.tp;

import com.demo.induction.tp.model.ResponseMessage;
import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.Violation;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionProcessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XMLTransactionProcessorIT {

    @LocalServerPort
    private int port;

    @Test
    public void transactionTest()
            throws Exception {

        // Given
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<ResponseMessage> request = restTemplate.exchange(
                createURLWithPort("/api/transaction/csv/process"),
                HttpMethod.GET, null, ResponseMessage.class);


        // performing test
        assertThat(request.getStatusCode(), Matchers.is(HttpStatus.OK));
        String contentType = request.getHeaders().getContentType().getType();
        assertEquals("application", contentType);

        assertThat("Transaction Object Test ", request.getBody().getTransactions(), hasItem(
                new Transaction("D", new BigDecimal(1920.00), "Salary")
        ));

        assertThat("Validation Object Test ", request.getBody().getViolations(), hasItem(
                new Violation(7, "type", "Type is not valid")
        ));

        assertThat("Transaction Object Test ", request.getBody().getTransactions(), hasItem(
                new Transaction("D", new BigDecimal(1920.00), "Salary")
        ));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
