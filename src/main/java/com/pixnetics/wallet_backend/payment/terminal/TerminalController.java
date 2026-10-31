package com.pixnetics.wallet_backend.payment.terminal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/terminal")
@RequiredArgsConstructor
public class TerminalController {
    private final TerminalService terminalService;

    @PostMapping("/pay")
    public String pay() {
        return "Pay done!";
    }
}
