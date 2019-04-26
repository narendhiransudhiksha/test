package com.rest.wallet.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseException {

	@ExceptionHandler(AccountException.class)
	public void handleBadAccountRequest(HttpServletResponse response, AccountException exception) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), exception.getLocalizedMessage());
	}

	@ExceptionHandler(TransactionException.class)
	public void handleBadTransactionRequest(HttpServletResponse response, TransactionException exception)
			throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage());
	}

}
