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
		//start the server
		MyRemoteFileSystemImpl rfs = new MyRemoteFileSystemImpl();
		String serverobjectname = "//localhost/remoteobject";
		try 
		{
			Naming.rebind(serverobjectname, rfs);
			System.out.println("MyRemoteFileSystem RMI Server is running...");
		}
		catch (RemoteException | MalformedURLException e) 
		{
			e.printStackTrace();
		}
	}
}