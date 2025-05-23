package br.com.gofood.gofood.forgottenPassword.usecases;

import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.exceptions.ClientNotFoundCPFException;
import br.com.gofood.gofood.exceptions.RestaurantNotFoundCNPJException;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class RestaurantForgotPasswordUseCase {
    private final RestaurantRepository restaurantRepository;
    private final EmailUseCase emailUseCase;

    public RestaurantForgotPasswordUseCase(RestaurantRepository restaurantRepository, EmailUseCase emailUseCase) {
        this.restaurantRepository = restaurantRepository;
        this.emailUseCase = emailUseCase;
    }

    public void execute(String email) {
        RestaurantCollection restaurantCollection = restaurantRepository.findByEmail(email)
                .orElseThrow(() -> new RestaurantNotFoundCNPJException());

        String code = String.valueOf(new Random().nextInt(9000) + 1000);
        restaurantCollection.setResetCode(code);
        restaurantCollection.setResetCodeExpiration(LocalDateTime.now().plusMinutes(10));
        restaurantRepository.save(restaurantCollection);

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
            .header {background-color: #071A40; padding: 20px; text-align: center; color: #fff;}
            .header h1 {font-size: 36px; margin: 0;}
            .content {padding: 30px; text-align: center;}
            .content h2 {font-size: 24px; color: #333;}
            .content p {font-size: 16px; line-height: 1.5; color: #555;}
            .code {font-size: 24px; font-weight: bold; color: #071A40; padding: 10px; border-radius: 8px; background-color: #f4f4f9; margin-top: 20px;}
            .footer {background-color: #f9f9f9; padding: 20px; text-align: center; font-size: 14px; color: #333;}
            .footer p {margin: 0;}
            ul {text-align: left; list-style-type: disc; margin-left: 30px; color: #333;}
            ul li {font-size: 16px; line-height: 1.5; color: #555;}
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1>Recuperação de Senha - Develfood</h1>
            </div>
            <div class="content">
                <h2>Olá, %s!</h2>
                <p>Recebemos uma solicitação para recuperação de senha da conta de seu restaurante no <strong>Develfood</strong>.</p>
                <p>Para continuar o processo, por favor, insira o código abaixo no site:</p>
                <div class="code">%s</div>
                <p>Este código é válido por 10 minutos. Caso não tenha feito essa solicitação, por favor, ignore este e-mail.</p>
            </div>
            <div class="footer">
                <p>Se precisar de ajuda, temos um chatbot disponível para sanar eventuais dúvidas.</p>
                <p>Atenciosamente,<br>Equipe Develfood</p>
            </div>
        </div>
    </body>
    </html>
""".formatted(restaurantCollection.getName(), code);
        emailUseCase.send(email, "Recuperação de Senha - Develfood", htmlContent);
    }
}

