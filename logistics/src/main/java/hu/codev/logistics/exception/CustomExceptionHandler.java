package hu.codev.logistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest req){
		
		MyError myError = new MyError(e.getMessage(), 1004);
		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(myError);
	}
	
	@ExceptionHandler(IdMustBeEmptyException.class)
	public ResponseEntity<MyError> handleIdIsNotEmpty(IdMustBeEmptyException e, WebRequest req){
		
		MyError myError = new MyError(e.getMessage(), 1003);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(myError);
	}
}
