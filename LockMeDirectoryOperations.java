package com.business.operations;

import com.exceptions.DirectoryNotFoundException;
import com.exceptions.FailCreateDirectoryException;
import com.exceptions.FileDoesNotExistException;
import com.exceptions.IsNotDirectoryException;
import com.exceptions.NullDirectoryException;

public interface LockMeDirectoryOperations {
	
	public void displayDirectory(String directoryName) throws NullDirectoryException;
	
	public void makeDirectory(String directoryName) throws FailCreateDirectoryException;
	
	public void validDirectory(String directoryName) throws IsNotDirectoryException, DirectoryNotFoundException;
	
	
}
