package com.pixnetics.wallet_backend.payment.wallet;

import com.pixnetics.wallet_backend.payment.client.exception.ClientNotFoundException;
import com.pixnetics.wallet_backend.payment.user.User;
import com.pixnetics.wallet_backend.payment.user.UserRepository;
import com.pixnetics.wallet_backend.payment.wallet.dto.WalletCreationRequest;
import com.pixnetics.wallet_backend.payment.wallet.dto.WalletResponse;
import com.pixnetics.wallet_backend.payment.wallet.exception.WalletAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Transactional
    public WalletResponse create(UUID userId, WalletCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ClientNotFoundException(userId));

        return create(user, request);
    }

    @Transactional
    public WalletResponse create(User user, WalletCreationRequest request) {

        if ("MERCHANT".equals(user.getType()) && walletRepository.existsByUser_Id(user.getId())) {
            throw new WalletAlreadyExistsException(user.getId().toString());
        }

        if (!"CLIENT".equals(user.getType()) && !"MERCHANT".equals(user.getType())) {
            throw new IllegalArgumentException("Only clients and merchants can own wallets");
        }

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setWalletType(request.type());
        wallet.setName(request.name());
        wallet.setCurrency(request.currency());
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setStatus("ACTIVE");

        Wallet saved = walletRepository.save(wallet);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<WalletResponse> getWalletsByUserId(UUID userId) {
        return walletRepository.findAllByUser_Id(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WalletResponse toResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getName(),
                wallet.getWalletType(),
                wallet.getBalance(),
                wallet.getCurrency()
        );
    }
}
