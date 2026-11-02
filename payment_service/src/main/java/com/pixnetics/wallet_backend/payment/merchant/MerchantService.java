package com.pixnetics.wallet_backend.payment.merchant;

import com.pixnetics.wallet_backend.payment.client.Client;
import com.pixnetics.wallet_backend.payment.client.ClientRepository;
import com.pixnetics.wallet_backend.payment.merchant.dto.MerchantCreationRequest;
import com.pixnetics.wallet_backend.payment.merchant.dto.MerchantResponse;
import com.pixnetics.wallet_backend.payment.merchant.exception.MerchantNotFoundException;
import com.pixnetics.wallet_backend.payment.user.User;
import com.pixnetics.wallet_backend.payment.user.UserRepository;
import com.pixnetics.wallet_backend.payment.user.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MerchantResponse getById(UUID id) {
        log.info("Creating a new client");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new MerchantNotFoundException(id));

        return new MerchantResponse(
                client.getId(),
                client.getUser().getUsername()
        );
    }

    @Transactional
    public MerchantResponse create(MerchantCreationRequest request){
        log.info("Creating a new client");

        if(userRepository.existsByUsername(request.username())){
            throw new UsernameAlreadyExistsException(request.username());
        }

        User user = new User();
        user.setType("CLIENT");
        user.setUsername(request.username());
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        Client client = new Client();
        client.setUser(user);
        client.setPublicKey(request.publicKey().getBytes(StandardCharsets.UTF_8));
        client.setKeyStatus("OK");
        client.setStatus("OK");

        Client savedClient = clientRepository.save(client);

        return new MerchantResponse(
                savedClient.getId(),
                savedClient.getUser().getUsername()
        );
    }
}
