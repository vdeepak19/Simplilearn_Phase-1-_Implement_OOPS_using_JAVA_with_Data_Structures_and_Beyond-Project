package com.business.operations;

import com.exceptions.FailCreateFileException;
import com.exceptions.FileDoesNotExistException;
import com.exceptions.InvalidFileNameException;
import com.exceptions.NullDirectoryException;

public interface LockMeFileOperations {

	public void addFile(String fileName) throws FailCreateFileException, InvalidFileNameException;

	public void deleteFile(String fileName) throws FileDoesNotExistException, InvalidFileNameException;

	public void searchFile(String fileName) throws NullDirectoryException, FileDoesNotExistException, InvalidFileNameException;

}
