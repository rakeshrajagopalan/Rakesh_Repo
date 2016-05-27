package rmipack;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {

	private static final long serialVersionUID = 283418824790242535L;

	public RMIServer() throws RemoteException {

	}

	@Override
	public void getTime() throws RemoteException {
		System.out.println("The time is: " + Calendar.getInstance().getTime());
	}

	public static void main(String[] args) throws Exception {
		RMIInterface rmiInterface = new RMIServer();
		Naming.rebind("RMIServer", rmiInterface);
	}
}
