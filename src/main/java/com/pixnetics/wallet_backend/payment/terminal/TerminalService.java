package com.pixnetics.wallet_backend.payment.terminal;

import com.pixnetics.wallet_backend.payment.merchant.Merchant;
import com.pixnetics.wallet_backend.payment.merchant.MerchantRepository;
import com.pixnetics.wallet_backend.payment.merchant.exception.MerchantNotFoundException;
import com.pixnetics.wallet_backend.payment.terminal.dto.TerminalCreationRequest;
import com.pixnetics.wallet_backend.payment.terminal.dto.TerminalResponse;
import com.pixnetics.wallet_backend.payment.terminal.exception.TerminalNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TerminalService {
    private final TerminalRepository terminalRepository;
    private final MerchantRepository merchantRepository;

    @Transactional(readOnly = true)
    public TerminalResponse getById(UUID id) {
        log.info("Getting terminal by ID: {}", id);
        Terminal terminal = terminalRepository.findById(id)
                .orElseThrow(() -> new TerminalNotFoundException(id));

        return toResponse(terminal);
    }

    @Transactional
    public TerminalResponse create(UUID merchantId, TerminalCreationRequest request) {
        log.info("Creating terminal for merchant: {}", merchantId);

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new MerchantNotFoundException(merchantId));

        Terminal terminal = new Terminal();
        terminal.setMerchant(merchant);
        terminal.setName(request.name());
        terminal.setCertificateSerialNumber(request.certificateSerialNumber());
        terminal.setStatus("ACTIVE");

        return toResponse(terminalRepository.save(terminal));
    }

    private TerminalResponse toResponse(Terminal terminal) {
        return new TerminalResponse(
                terminal.getId(),
                terminal.getName(),
                terminal.getCertificateSerialNumber(),
                terminal.getStatus()
        );
    }
}
