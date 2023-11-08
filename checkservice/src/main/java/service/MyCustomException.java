package service;

public class MyCustomException extends Exception {
	
	MyCustomException(Exception e) {
		super(e);
	}

}
