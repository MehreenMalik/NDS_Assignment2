import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

public class Client extends JFrame implements ActionListener
{	
	//global declarations as needed
	JButton submitButton = new JButton("Submit");
	JTextField fileNameTextFIeld = new JTextField(20);
	static String filename;
	
	//constructor
	Client()
	{
		super("Assignment 2");
		
		Container contentPane = getContentPane();
		JPanel panel = new JPanel();
		
		JLabel fileNameLabel = new JLabel("Enter the file location: ");
		fileNameTextFIeld.setText("/Users/Erebus/Desktop/source.txt");
		submitButton.addActionListener(this);
		
		panel.add(fileNameLabel, BorderLayout.CENTER);
		panel.add(fileNameTextFIeld, BorderLayout.CENTER);
		panel.add(submitButton, BorderLayout.CENTER);
		contentPane.add(panel);
		
		setSize(250,250);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==submitButton)
		{
			String filename = fileNameTextFIeld.getText().toString();
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
	}
	
	public static void main(String[]args)
	{
		JFrame frame = new Client();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//method that needs to open a file from the File Server node
	public static void getRemoteFile() throws MalformedURLException, RemoteException, NotBoundException
	{
		String serverobjectname = "//localhost/remoteobject";
		//lookup remote object on the registry. Notice the name of the interface is used below
		MyRemoteFileSystem myfile = (MyRemoteFileSystem)Naming.lookup(serverobjectname);
		//proceed with remote invocation
		//where filename is a string representing the name of the file.
		//String filename = new String("/Users/Desktop/source.txt");
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