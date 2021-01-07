package com.prototype;

import com.application.operations.ApplicationOperations;
import com.business.operations.*;
import com.exceptions.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LockMePrototype {

	public static void main(String[] args) {

		boolean featureValid = false, operationValid = false;

		int operation;

		String directoryName, fileName, input;

		ApplicationOperations appOperations = new ApplicationOperations();
		LockMeDirectoryHandler directoryHandler = new LockMeDirectoryHandler();
		
		final String defaultDirectory = LockMeDirectoryHandler.getLocation();

		directoryName = defaultDirectory;
		
		LockMeFileHandler fileHandler = new LockMeFileHandler(directoryName);

		Scanner scan = new Scanner(System.in);

		System.out.println("*************************************\n");
		System.out.println("Welcome to LockMe.com Prototype \n");
		System.out.println("*************************************");
		System.out.println("Developer: Nicholas Auyeung");

		while (featureValid == false) {
			try {
				System.out.println("\n-----------------------------------");
				System.out.println("Main Menu");
				System.out.println("-----------------------------------\n");
				System.out.println("Current directory: " + directoryName + "\n");
				System.out.println("Enter the following feature you would like to access: ");
				System.out.println("(1) Display existing files in current directory");
				System.out.println("(2) Directory management");
				System.out.println("(3) File management");
				System.out.println("(4) Exit application");
				operationValid = false;
				operation = scan.nextInt();
				switch (operation) {
				case 1:
					try {
						directoryHandler.displayDirectory(directoryName);
					} catch (NullDirectoryException e1) {

					}
					break;
				case 2:
					while (operationValid == false) {
						try {
							System.out.println("-----------------------------------");
							System.out.println("Directory Management");
							System.out.println("-----------------------------------\n");

							System.out.println("Current directory: " + directoryName + "\n");
							System.out.println("Enter the following operation that you would like to perform: ");
							System.out.println("(1) Switch to an existing directory");
							System.out.println("(2) Create a new directory");
							System.out.println("(3) Return to main menu");
							operation = scan.nextInt();
							switch (operation) {
							case 1:
								System.out.println("Enter an existing directory you would like to switch to: ");
								System.out.println("Type 'default' to return to the default directory");
								input = scan.next();
								try {
									if(input.compareTo("default") == 0) {
										directoryName = defaultDirectory;
										fileHandler.setPathName(directoryName);
									}else {
										directoryHandler.validDirectory(directoryName + "/" + input);
										directoryName = defaultDirectory + "/" + input;
										fileHandler.setPathName(directoryName);
									}
								} catch (IsNotDirectoryException| DirectoryNotFoundException e) {
									System.out.println(e.getMessage());									
								}
								break;
							case 2:
								System.out.println("Enter the name of the directory you would like to create: ");
								input = scan.next();
								try {
									directoryHandler.makeDirectory(input);
									directoryName = defaultDirectory + "/" + input;
									fileHandler.setPathName(directoryName);
								} catch (FailCreateDirectoryException e) {
									System.out.println(e.getMessage());
								}
								break;
							case 3:
								System.out.println("Returning to main menu...");
								operationValid = true;
								break;
							default:
								System.out.println("Not a valid operation");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Not a valid operation");
							scan.next();
						}
					}
					break;
				case 3:
					while (operationValid == false) {
						try {
							System.out.println("-----------------------------------");
							System.out.println("File Management");
							System.out.println("-----------------------------------");
	
							System.out.println("Current directory: " + directoryName + "\n");
							System.out.println("Enter the following operation that you would like to perform: ");
							System.out.println("(1) Add a file");
							System.out.println("(2) Delete a file");
							System.out.println("(3) Search for a file");
							System.out.println("(4) Return to main menu");
							operation = scan.nextInt();
							switch (operation) {
							case 1:
								System.out.println("Enter the name of the file you would like to create: ");
								fileName = scan.next();
	
								try {
									fileHandler.addFile(fileName);
								} catch (InvalidFileNameException | FailCreateFileException e) {
									System.out.println(e.getMessage());	
								}
								break;
							case 2:
								System.out.println("Enter the name of the file you would like to delete: ");
								fileName = scan.next();
								try {
									fileHandler.deleteFile(fileName);
								} catch (InvalidFileNameException | FileDoesNotExistException e) {
									System.out.println(e.getMessage());	
								}
								break;
							case 3:
								System.out.println("Enter the name of the file you would like to search for: ");
								fileName = scan.next();
								try {
									fileHandler.searchFile(fileName);
								} catch (InvalidFileNameException | NullDirectoryException | FileDoesNotExistException e) {
									System.out.println(e.getMessage());	
								}
								break;
							case 4:
								System.out.println("Returning to main menu...");
								operationValid = true;
								break;
							default:
								System.out.println("Not a valid operation");
								break;
							}
						}catch (InputMismatchException e) {
							System.out.println("Not a valid feature");
							scan.next();
						}
					}
					break;
				case 4:
					appOperations.exitApp();
					featureValid = true;
					break;
				default:
					System.out.println("Not a valid feature");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Not a valid feature");
				scan.next();
			}
		}

		scan.close();
	}

}
