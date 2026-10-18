package com.pixnetics.wallet_backend.payment;

import com.pixnetics.wallet_backend.payment.entities.Client;
import com.pixnetics.wallet_backend.payment.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final ClientRepository clientRepository;

    @GetMapping("/clients/{id}")
    public String createMerchant() {
        log.info("Creating a new client");
        clientRepository.save(new Client());
        return "Client created!";
    }

    @PostMapping("/client")
    public String createClient(@RequestBody Client client) {
        log.info("Creating a new client");
        clientRepository.save(client);
        return "Client created!";
    }
}
