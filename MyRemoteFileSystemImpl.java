import java.awt.Desktop;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteFileSystemImpl extends UnicastRemoteObject implements MyRemoteFileSystem
{
	//constructor that calls super()
	MyRemoteFileSystemImpl() throws RemoteException 
	{
		super();
	}

	//implement the code for the remotely invokable method
	public int openFile(String name) throws IOException 
	{
		//code to open the file
		File file = new File(name);
		
        if(!Desktop.isDesktopSupported())  //Check if Desktop is supported by Platform or not
        {
            System.out.println("Desktop is not supported");
        }
         
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) 
        {
        	desktop.open(file);
    	}
		return 0;
	}
	
	//main method for the class that starts the server
	public static void main(String[]args) throws RemoteException
	{
		//start the server by instantiating an instance of the class
		MyRemoteFileSystemImpl rfs = new MyRemoteFileSystemImpl();
		//create a string that represents the location of the registry and the name of a remote object 
		//used by a client to invoke the service
		String serverobjectname = "//localhost/remoteobject";
		//we assume that the registry resides localhost (or it can be the ip address of another node
		//proceed to register the remote object with the registry
		try 
		{
			Naming.rebind(serverobjectname, rfs);
		}
		catch (RemoteException | MalformedURLException e) 
		{
			e.printStackTrace();
		}
	}
}