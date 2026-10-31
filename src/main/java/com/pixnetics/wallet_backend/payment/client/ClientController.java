package com.pixnetics.wallet_backend.payment.client;

import com.pixnetics.wallet_backend.payment.client.dto.ClientCreationRequest;
import com.pixnetics.wallet_backend.payment.client.dto.ClientResponse;
import com.pixnetics.wallet_backend.payment.wallet.WalletService;
import com.pixnetics.wallet_backend.payment.wallet.dto.WalletCreationRequest;
import com.pixnetics.wallet_backend.payment.wallet.dto.WalletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final WalletService walletService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable UUID id) {
        log.info("Get Client by ID: {}", id);
        return ResponseEntity.ok(clientService.getById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<ClientResponse> getByUsername(@PathVariable UUID username) {
        log.info("Get Client by username: {}", username);
        return ResponseEntity.ok(clientService.getById(username));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientCreationRequest request) {
        log.info("Creating a new client");

        ClientResponse response = clientService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/clients/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/{id}/wallet")
    public ResponseEntity<WalletResponse> createWallet(@PathVariable UUID id,
                                                 @Valid @RequestBody WalletCreationRequest request) {
        log.info("Creating a new wallet");
        WalletResponse response = walletService.create(id, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/details")
    public String getDetails() {
        log.info("Creating a new client");
        return "Client created!";
    }
}
