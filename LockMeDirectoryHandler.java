package com.business.operations;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.exceptions.*;

public class LockMeDirectoryHandler implements LockMeDirectoryOperations {

	final static private String location = System.getProperty("user.dir");
	
	public LockMeDirectoryHandler() {
		super();
	}

	public static String getLocation() {
		return location;
	}



	@Override
	public void displayDirectory(String directoryName) throws NullDirectoryException{
		File dir = new File(directoryName);
		List<String> children = Arrays.asList(dir.list());
		if(children == null) {
			throw new NullDirectoryException("Directory is empty\n");
		}else {
			children.stream().sorted().forEach(System.out::println);
		}
	}
	
	@Override
	public void makeDirectory(String directoryName) throws FailCreateDirectoryException {
		File f = new File(directoryName);
		if (f.mkdir()) {
			System.out.println("Directory creation successful\n");
		} else {
			throw new FailCreateDirectoryException("Directory creation failed\n");
		}

	}

	@Override
	public void validDirectory(String directoryName) throws IsNotDirectoryException, DirectoryNotFoundException {
		File f = new File(directoryName);
		if (f.exists()) {
			if (f.isDirectory()) {
				System.out.println("Directory exists\n");
			} else {
				throw new IsNotDirectoryException("This is not a directory\n");
			}
		} else {
			throw new DirectoryNotFoundException("Directory not found\n");
		}
	}

}
