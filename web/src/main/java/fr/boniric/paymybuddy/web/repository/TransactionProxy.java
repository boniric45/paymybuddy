package fr.boniric.paymybuddy.web.repository;

import fr.boniric.paymybuddy.api.model.Transaction;
import fr.boniric.paymybuddy.web.custom.CustomProperties;
import fr.boniric.paymybuddy.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TransactionProxy {

    @Autowired
    private CustomProperties props;

    public Transaction saveTransaction(Transaction transaction) {


            String baseApiUrl = props.getApiURL();
            String geTransactionURL =  baseApiUrl+"/transaction";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Transaction> request = new HttpEntity<Transaction>(transaction);
            ResponseEntity<Transaction> response = restTemplate.exchange(
                    geTransactionURL,
                    HttpMethod.POST,
                    request,
                    Transaction.class);

            log.debug("Create Transaction call " + response.getStatusCode().toString());
        return response.getBody();
        }

    }


