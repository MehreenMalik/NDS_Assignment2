import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

public class Client
{
	public static void main(String[]args)
	{
		try
		{
			getRemoteFile();
		}
		catch (MalformedURLException murle)
		{
			System.out.println();
			System.out.println("MalformedURLException");
			System.out.println(murle);
		}
		catch (RemoteException re)
		{
			System.out.println();
			System.out.println("RemoteException");
			System.out.println(re);
		}
		catch (NotBoundException nbe)
		{
			System.out.println();
			System.out.println("NotBoundException");
			System.out.println(nbe);
		}
		catch (java.lang.ArithmeticException ae)
		{
			System.out.println();
			System.out.println("java.lang.ArithmeticException");
			System.out.println(ae);
		}
	}

	public static void getRemoteFile() throws MalformedURLException, RemoteException, NotBoundException
	{
		String filename = "C:\\Users\\Erebus\\Desktop\\source.txt";
		String serverobjectname = "//localhost/remoteobject";
		MyRemoteFileSystem myfile = (MyRemoteFileSystem)Naming.lookup(serverobjectname);
		try
		{
			int c = myfile.openFile(filename);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}