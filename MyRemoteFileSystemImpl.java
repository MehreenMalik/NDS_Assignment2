import java.awt.Desktop;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteFileSystemImpl extends UnicastRemoteObject implements MyRemoteFileSystem
{
	MyRemoteFileSystemImpl() throws RemoteException
	{
		super();
	}

	public int openFile(String name) throws IOException
	{
		File file = new File(name);

        if(!Desktop.isDesktopSupported())
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

	public int deleteFile(String name) throws IOException
	{
		File file = new File(name);

		if(file.delete())
		{
			System.out.println(file.getName() + " is deleted!");
		}
		else
		{
			System.out.println("Delete operation is failed.");
		}
		return 0;
	}

	public int renameFile(String name) throws IOException
	{
		File oldFileName = new File(name);
		File newfile =new File("C:\\Users\\Erebus\\Desktop\\Test\\newD");

		if(oldFileName.renameTo(newfile))
		{
			System.out.println("Rename succesful");
		}
		else
		{
			System.out.println("Rename failed");
		}
		return 0;
	}

	public int createDirectory(String name) throws IOException
	{
		File file = new File(name);

		boolean successful = file.mkdir();
		if (successful)
		{
			System.out.println("directory was created successfully");
		}
		else
		{
			System.out.println("failed trying to create the directory");
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