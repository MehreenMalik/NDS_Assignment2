import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import javax.swing.*;

class ClientGUI extends JFrame implements ActionListener
{
	JButton openFileButton = new JButton("Submit");
	JButton deleteFileButton = new JButton("Submit");
	JButton renameFileButton = new JButton("Submit");
	JButton createDirectoryButton = new JButton("Submit");
	JPanel display = new JPanel();

	static JTextField openFileTextField = new JTextField("C:\\Users\\Erebus\\Desktop\\Test\\source.txt",25);
	static JTextField deleteFileTextField = new JTextField("C:\\Users\\Erebus\\Desktop\\Test\\poop",25);
	static JTextField renameFileTextField = new JTextField("C:\\Users\\Erebus\\Desktop\\Test\\example.txt",25);
	static JTextField newNameTextField = new JTextField("C:\\Users\\Erebus\\Desktop\\Test\\",25);
	static JTextField createDirectoryTextField = new JTextField("C:\\Users\\Erebus\\Desktop\\Test\\example",25);
	static JTextArea DisplayArea = new JTextArea(50, 50);

	ClientGUI()
	{
		super("Assignment 2");
		Container contentPane = getContentPane();
		JPanel panel = new JPanel();


		JLabel openFileLabel = new JLabel("Enter a name to open:");
		JLabel deleteFileLabel = new JLabel("Enter a name to delete:");
		JLabel renameFileLabel = new JLabel("Enter a file/dir to rename:");
		JLabel newNameFileLabel = new JLabel("Enter the new name:");
		JLabel createDirectoryLabel = new JLabel("Enter a new directory name:");

		panel.add(openFileLabel);
		panel.add(openFileTextField);
		panel.add(openFileButton);
		openFileButton.addActionListener(this);

		panel.add(deleteFileLabel);
		panel.add(deleteFileTextField);
		panel.add(deleteFileButton);
		deleteFileButton.addActionListener(this);


		panel.add(renameFileLabel);
		panel.add(renameFileTextField);
		panel.add(newNameFileLabel);
		panel.add(newNameTextField);
		panel.add(renameFileButton);
		renameFileButton.addActionListener(this);


		panel.add(createDirectoryLabel);
		panel.add(createDirectoryTextField);
		panel.add(createDirectoryButton);
		createDirectoryButton.addActionListener(this);

		display.add(DisplayArea);
		panel.add(display);
		contentPane.add(panel);

		setSize(550,300);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			DisplayArea.setText("");
			showDir(new File("C:\\Users\\Erebus\\Desktop\\test"));
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}

		if(e.getSource()==openFileButton)
		{
			try
			{
				openFile();
			}
			catch (MalformedURLException | RemoteException | NotBoundException e1)
			{
				e1.printStackTrace();
			}
		}

		if(e.getSource()==deleteFileButton)
		{
			try
			{
				deleteFile();
			}
			catch (MalformedURLException | RemoteException | NotBoundException e1)
			{
				e1.printStackTrace();
			}
		}

		if(e.getSource()==renameFileButton)
		{
			try
			{
				renameFile();
			}
			catch (MalformedURLException | RemoteException | NotBoundException e1)
			{
				e1.printStackTrace();
			}
		}

		if(e.getSource()==createDirectoryButton)
		{
			try
			{
				createDirectory();
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void showDir(File file) throws IOException
	{
		System.out.print('-');
		System.out.println(file.getName());
		if (file.isDirectory())
		{
			for (int i = 0; i < 1; i++)
			{
				File[] files = file.listFiles();
				for (int k = 0; k < files.length; k++)
				{
					showDir(files[k]);
					DisplayArea.append(files[k].toString()+"\n");
				}
			}
		}
	}

	public static void openFile() throws MalformedURLException, RemoteException, NotBoundException
	{
		String filename = openFileTextField.getText();
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

	public static void deleteFile() throws MalformedURLException, RemoteException, NotBoundException
	{
		String filename = deleteFileTextField.getText();
		String serverobjectname = "//localhost/remoteobject";
		MyRemoteFileSystem myfile = (MyRemoteFileSystem)Naming.lookup(serverobjectname);
		try
		{
			int c = myfile.deleteFile(filename);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void renameFile() throws MalformedURLException, RemoteException, NotBoundException
	{
		String filename = renameFileTextField.getText();
		String newFilename = newNameTextField.getText();
		String serverobjectname = "//localhost/remoteobject";
		MyRemoteFileSystem myfile = (MyRemoteFileSystem)Naming.lookup(serverobjectname);
		try
		{
			int c = myfile.renameFile(filename,newFilename);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void createDirectory() throws MalformedURLException, RemoteException, NotBoundException
	{
		String filename = createDirectoryTextField.getText();
		String serverobjectname = "//localhost/remoteobject";
		MyRemoteFileSystem myfile = (MyRemoteFileSystem)Naming.lookup(serverobjectname);
		try
		{
			int c = myfile.createDirectory(filename);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}