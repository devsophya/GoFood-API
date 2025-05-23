package br.com.gofood.gofood.forgottenPassword.usecases;

import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientNewPasswordUseCase {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientNewPasswordUseCase(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(String email, String code, String newPassword) {
        ClientCollection client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client not found."));

        if (!code.equals(client.getResetCode())) {
            throw new RuntimeException("Invalid code.");
        }

        if (!isStrongPassword(newPassword)) {
            throw new RuntimeException("Weak password. Must have at least 8 characters, uppercase, lowercase, number and symbol.");
        }

        client.setPassword(passwordEncoder.encode(newPassword));
        client.setResetCode(null);
        clientRepository.save(client);
    }

    private boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}
