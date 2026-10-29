package com.pixnetics.wallet_backend.payment.terminal;

import com.pixnetics.wallet_backend.payment.terminal.dto.TerminalCreationRequest;
import com.pixnetics.wallet_backend.payment.terminal.dto.TerminalResponse;
import com.pixnetics.wallet_backend.payment.terminal.exception.TerminalNotFoundException;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class TerminalService {
    private final TerminalRepository terminalRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public TerminalResponse getById(UUID id) {
        log.info("Getting a new client");
        Client client = terminalRepository.findById(id)
                .orElseThrow(() -> new TerminalNotFoundException(id));

        return new TerminalResponse(
                client.getId(),
                client.getUser().getUsername()
        );
    }

   /* @Transactional(readOnly = true)
    public ClientResponse getByUsername(UUID id) {
        log.info("Getting a new client");
        Client client = clientRepository.findByU(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return new ClientResponse(
                client.getId(),
                client.getUser().getUsername()
        );
    }
*/
    @Transactional
    public TerminalResponse create(TerminalCreationRequest request){
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

        Client savedClient = terminalRepository.save(client);

        return new TerminalResponse(
                savedClient.getId(),
                savedClient.getUser().getUsername()
        );
    }
}
