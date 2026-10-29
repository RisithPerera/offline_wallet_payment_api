package com.pixnetics.wallet_backend.payment.terminal;

import com.pixnetics.wallet_backend.payment.terminal.dto.TerminalCreationRequest;
import com.pixnetics.wallet_backend.payment.terminal.dto.TerminalResponse;
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
public class TerminalController {
    private final TerminalService terminalService;

    @GetMapping("/{id}")
    public ResponseEntity<TerminalResponse> getById(@PathVariable UUID id) {
        log.info("Get Client by ID: {}", id);
        return ResponseEntity.ok(terminalService.getById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<TerminalResponse> getByUsername(@PathVariable UUID username) {
        log.info("Get Client by username: {}", username);
        return ResponseEntity.ok(terminalService.getById(username));
    }

    @PostMapping
    public ResponseEntity<TerminalResponse> create(@Valid @RequestBody TerminalCreationRequest request) {
        log.info("Creating a new client");

        TerminalResponse response = terminalService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/clients/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
