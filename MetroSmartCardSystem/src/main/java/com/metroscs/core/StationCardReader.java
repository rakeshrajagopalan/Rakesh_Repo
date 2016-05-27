package com.metroscs.core;

import com.metroscs.command.Command;
import com.metroscs.exception.MetroSCSException;

public class StationCardReader {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void readCard() throws MetroSCSException {
		command.execute();
	}

}
