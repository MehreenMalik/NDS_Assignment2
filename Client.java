import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

public class Client extends JFrame
{	
	//constructor
	Client()
	{
		super();
		JLabel fileNameLabel = new JLabel("Enter the file location:");
		Content pane = new JContentPane();
		
		Client.getContentPane().add(fileNameLabel, BorderLayout.CENTER);

		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String[]args)
	{
		JFrame Client = new JFrame("Assignment 2");
		Client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
	//method that needs to open a file from the File Server node
	public static void getRemoteFile() throws MalformedURLException, RemoteException, NotBoundException
	{
		String serverobjectname = "//localhost/remoteobject";
		//lookup remote object on the registry. Notice the name of the interface is used below
		MyRemoteFileSystem myfile = (MyRemoteFileSystem)Naming.lookup(serverobjectname);
		//proceed with remote invocation
		//where filename is a string representing the name of the file.
		String filename = new String("/Users/Desktop/source.txt");
		try 
		{
			int c = myfile.openFile(filename);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//the server returns the result which is captured by the int c.
	}
}