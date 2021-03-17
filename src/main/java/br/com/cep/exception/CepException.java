package br.com.cep.exception;

public class CepException extends RuntimeException{

    public CepException(String message){
        super(message, null, false, false);
    }

}
