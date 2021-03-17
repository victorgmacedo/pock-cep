package br.com.cep.exception;

public class CidadeException extends RuntimeException{

    public CidadeException(String message){
        super(message, null, false, false);
    }

}
