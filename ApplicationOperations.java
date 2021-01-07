package com.application.operations;

public class ApplicationOperations implements ExitApplication {

	@Override
	public void exitApp() {
		System.out.println("Exiting Application...");
		System.exit(0);
	}

}
