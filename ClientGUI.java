import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import javax.swing.*;

class ClientGUI extends JFrame implements ActionListener
{
	JButton openFileButton = new JButton("openFileButton");
	ClientGUI()
	{
		super("Assignment 2");
		Container contentPane = getContentPane();
		JPanel panel = new JPanel();

		openFileButton.addActionListener(this);

		panel.add(openFileButton);
		contentPane.add(panel);

		setSize(300,300);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==openFileButton)
		{
			try
			{
				getRemoteFile();
			}
			catch (MalformedURLException | RemoteException | NotBoundException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[]agrs)
	{
		JFrame frame = new ClientGUI();
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