package br.com.gofood.gofood.forgottenPassword.usecases;

import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.exceptions.ClientNotFoundCPFException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ClientForgotPasswordUseCase {

    private final ClientRepository clientRepository;
    private final EmailUseCase emailUseCase;

    public ClientForgotPasswordUseCase(ClientRepository clientRepository, EmailUseCase emailUseCase) {
        this.clientRepository = clientRepository;
        this.emailUseCase = emailUseCase;
    }

    public void execute(String email) {
        ClientCollection client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundCPFException());

        String code = String.valueOf(new Random().nextInt(9000) + 1000);
        client.setResetCode(code);
        client.setResetCodeExpiration(LocalDateTime.now().plusMinutes(10));
        clientRepository.save(client);

        String htmlContent = """
    <!DOCTYPE html>
    <html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Recuperação de Senha - Develfood</title>
        <style>
            body {font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; margin: 0; padding: 0;}
            .container {width: 100%%; max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);}
            .header {background-color: #C20C18; padding: 20px; text-align: center; color: #fff;}
            .header h1 {font-size: 36px; margin: 0;}
            .content {padding: 30px; text-align: center;}
            .content h2 {font-size: 24px; color: #333;}
            .content p {font-size: 16px; line-height: 1.5; color: #555;}
            .code {font-size: 24px; font-weight: bold; color: #C20C18; padding: 10px; border-radius: 8px; background-color: #f4f4f9; margin-top: 20px;}
            .footer {background-color: #f9f9f9; padding: 20px; text-align: center; font-size: 14px; color: #333;}
            .footer p {margin: 0;}
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1>Recuperação de Senha - Develfood</h1>
            </div>
            <div class="content">
                <h2>Olá, %s!</h2>
                <p>Recebemos uma solicitação para recuperação de senha em sua conta no <strong>Develfood</strong>.</p>
                <p>Para continuar o processo, por favor, insira o código abaixo na tela de redefinição de senha:</p>
                <div class="code">%s</div>
                <p>Este código é válido por 10 minutos. Se você não solicitou a recuperação, ignore este e-mail.</p>
            </div>
            <div class="footer">
                <p>Equipe Develfood</p>
            </div>
        </div>
    </body>
    </html>
""".formatted(client.getFirstName(), code);

        emailUseCase.send(email, "Recuperação de Senha - Develfood", htmlContent);
    }
}
