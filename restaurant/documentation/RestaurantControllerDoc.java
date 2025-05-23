/*


package br.com.gofood.gofood.modules.restaurant.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Restaurant", description = "API para gerenciamento de restaurantes")
public interface RestaurantControllerDoc {

    @PreAuthorize("hasRole('RESTAURANT')")
    @Operation(
            summary = "Criação de um restaurante",
            description = "Essa função cria um novo restaurante com os dados fornecidos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    @SecurityRequirement(name = "jwt_auth")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> create(
            @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateRestaurantRequest.class)
                    )
            )
            CreateRestaurantRequest request
    );
}


*/