package com.pixnetics.wallet_backend.payment.client;

import com.pixnetics.wallet_backend.payment.client.dto.ClientCreationRequest;
import com.pixnetics.wallet_backend.payment.client.dto.ClientResponse;
import com.pixnetics.wallet_backend.payment.client.exception.ClientNotFoundException;
import com.pixnetics.wallet_backend.payment.client.exception.UsernameAlreadyExistsException;
import com.pixnetics.wallet_backend.payment.user.User;
import com.pixnetics.wallet_backend.payment.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public ClientResponse getById(UUID id) {
        log.info("Getting a new client");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return new ClientResponse(
                client.getId(),
                client.getUser().getUsername()
        );
    }

    @Transactional
    public ClientResponse create(ClientCreationRequest request){
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

        return new ClientResponse(
                savedClient.getId(),
                savedClient.getUser().getUsername()
        );
    }
}
