package com.caio.job_board.exception;

public class ExistsEmailException extends RuntimeException{

    public ExistsEmailException(String menssage){
        super(menssage);
    }
}
