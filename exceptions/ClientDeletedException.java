package br.com.gofood.gofood.exceptions;

public class ClientDeletedException extends RuntimeException{
    public ClientDeletedException() {
        super ("This client is already deleted/deactivated from the system.");
    }
}
//S
