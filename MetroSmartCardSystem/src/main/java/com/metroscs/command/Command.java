package com.metroscs.command;

import com.metroscs.exception.MetroSCSException;

public interface Command {
	
	public void execute() throws MetroSCSException;
}
