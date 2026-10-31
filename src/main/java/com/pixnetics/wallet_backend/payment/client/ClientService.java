package com.pixnetics.wallet_backend.payment.client;

import com.pixnetics.wallet_backend.payment.client.dto.ClientCreationRequest;
import com.pixnetics.wallet_backend.payment.client.dto.ClientResponse;
import com.pixnetics.wallet_backend.payment.client.exception.ClientNotFoundException;
import com.pixnetics.wallet_backend.payment.user.User;
import com.pixnetics.wallet_backend.payment.user.UserRepository;
import com.pixnetics.wallet_backend.payment.user.exception.UsernameAlreadyExistsException;
import com.pixnetics.wallet_backend.payment.wallet.WalletService;
import com.pixnetics.wallet_backend.payment.wallet.dto.WalletCreationRequest;
import com.pixnetics.wallet_backend.payment.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;

    @Transactional(readOnly = true)
    public ClientResponse getById(UUID id) {
        log.info("Getting a new client");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return new ClientResponse(
                client.getId(),
                client.getUser().getUsername(),
                walletService.getWalletsByUserId(client.getId())
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

        WalletCreationRequest initialWalletRequest = new WalletCreationRequest("MAIN", "Main", "EUR");

        WalletResponse initialWallet = walletService.create(savedClient.getUser(), initialWalletRequest);

        return new ClientResponse(
                savedClient.getId(),
                savedClient.getUser().getUsername(),
                List.of(initialWallet)
        );
    }
}
