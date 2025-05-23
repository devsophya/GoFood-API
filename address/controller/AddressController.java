package br.com.gofood.gofood.address.controller;

import br.com.gofood.gofood.address.collections.AddressCollection;
import br.com.gofood.gofood.address.dto.AddressDeleteRequestDTO;
import br.com.gofood.gofood.address.dto.AddressResponseDTO;
import br.com.gofood.gofood.address.dto.AddressUpdateRequestDTO;
import br.com.gofood.gofood.address.usecases.CreateAddressUseCase;
import br.com.gofood.gofood.address.usecases.DeleteAddressUseCase;
import br.com.gofood.gofood.address.usecases.GetAddressUseCase;
import br.com.gofood.gofood.address.usecases.UpdateAddressUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final CreateAddressUseCase createAddressUseCase;
    private final GetAddressUseCase getAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;


        @PostMapping("/create/{clientId}")
        public ResponseEntity<AddressCollection> createAddress(@PathVariable String clientId,
                                                               @RequestBody AddressCollection addressCollection) {
            AddressCollection savedAddress = createAddressUseCase.execute(addressCollection, clientId);
            return ResponseEntity.ok(savedAddress);
        }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientAddresses(@PathVariable String clientId) {
        return ResponseEntity.ok(getAddressUseCase.execute(clientId));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @PathVariable String clientId,
            @RequestBody AddressUpdateRequestDTO updateRequest) {
        AddressCollection updatedAddress = updateAddressUseCase.execute(clientId, updateRequest);
        return ResponseEntity.ok(new AddressResponseDTO(updatedAddress));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAddress(@RequestBody @Valid AddressDeleteRequestDTO request) {
        deleteAddressUseCase.execute(request);
        return ResponseEntity.ok("Address deleted successfully");
    }}
//S
