package com.business.operations;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exceptions.FailCreateFileException;
import com.exceptions.FileDoesNotExistException;
import com.exceptions.InvalidFileNameException;
import com.exceptions.NullDirectoryException;

public class LockMeFileHandler implements LockMeFileOperations {
	
	public static final String regex = "^[a-zA-Z0-9](?:[a-zA-Z0-9 ._-]*[a-zA-Z0-9])?\\.[a-zA-Z0-9_-]+$";

	private String pathName;

	public LockMeFileHandler(String pathName) {
		super();
		this.pathName = pathName;
	}

	public LockMeFileHandler() {
		super();
	}
	
	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	@Override
	public void addFile(String fileName) throws InvalidFileNameException, FailCreateFileException {
		String filePath = pathName + "/" + fileName;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		if(matcher.matches()) {
			try {
				File f = new File(filePath);
				if (f.createNewFile()) {
					System.out.println("File creation succeeded\n");
				} else {
					System.out.println("File already exists\n");
				}
			} catch (IOException e) {
				throw new FailCreateFileException("Error occured\n");
			}
		}else {
			throw new InvalidFileNameException("Invalid filename\n");
		}

	}

	@Override
	public void deleteFile(String fileName) throws InvalidFileNameException, FileDoesNotExistException {
		String filePath = pathName + "/" + fileName;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		if(matcher.matches()) {
			File f = new File(filePath);
			if (f.exists()) {
				f.delete();
				System.out.println("File deleted\n");
			} else {
				throw new FileDoesNotExistException("File does not exist\n");
			}
		}else {
			throw new InvalidFileNameException("Invalid filename\n");
		}
	}

	@Override
	public void searchFile(String fileName) throws InvalidFileNameException, NullDirectoryException, FileDoesNotExistException{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		if(matcher.matches()) {
			File f = new File(pathName);
			List<String> children = Arrays.asList(f.list());
			if (children == null) {
				throw new NullDirectoryException("Directory is empty\n");
			} else {
				if(children.contains(fileName)) {
					System.out.println("File " + fileName + " is found\n");
				}else {
					throw new FileDoesNotExistException("File is not found\n");
				}
			}
		}else {
			throw new InvalidFileNameException("Invalid filename\n");
		}
	}

}
