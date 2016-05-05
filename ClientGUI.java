import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.Dimension;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;

public class ClientGUI extends JFrame implements ActionListener
{
	private JPanel contentPane;

	static JTextArea DisplayArea = new JTextArea();

	static JButton openFileButton = new JButton("Submit");
	static JButton deleteFileButton = new JButton("Submit");
	static JButton createDirectoryButton = new JButton("Submit");
	static JButton renameFileButton = new JButton("Submit");

	static JTextField deleteFileTextField;
	static JTextField renameFileTextField;
	static JTextField createDirectoryTextField;
	static JTextField openFileTextField;
	static JTextField newNameTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI()
	{
		setSize(new Dimension(750, 500));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel openFileLabel = new JLabel("Enter a file/dir name to open:");
		JLabel deleteFileLabel = new JLabel("Enter a file/dir name to delete:");
		JLabel renameFileLabel = new JLabel("Enter a file/dir name to rename:");
		JLabel createDirectoryLabel = new JLabel("Enter a new directory name:");

		deleteFileTextField = new JTextField();
		deleteFileTextField.setText("C:\\Users\\Erebus\\Desktop\\Test\\poop");
		deleteFileTextField.setColumns(28);

		renameFileTextField = new JTextField();
		renameFileTextField.setToolTipText("Enter old file name");
		renameFileTextField.setText("C:\\Users\\Erebus\\Desktop\\Test\\example.txt");
		renameFileTextField.setColumns(28);

		createDirectoryTextField = new JTextField();
		createDirectoryTextField.setText("C:\\Users\\Erebus\\Desktop\\Test\\example");
		createDirectoryTextField.setColumns(28);

		openFileTextField = new JTextField();
		openFileTextField.setText("C:\\Users\\Erebus\\Desktop\\Test\\source.txt");
		openFileTextField.setColumns(28);

		openFileButton.addActionListener(this);
		deleteFileButton.addActionListener(this);
		renameFileButton.addActionListener(this);
		createDirectoryButton.addActionListener(this);

		newNameTextField = new JTextField();
		newNameTextField.setToolTipText("Enter new file name");
		newNameTextField.setText("C:\\Users\\Erebus\\Desktop\\Test\\example.txt");
		newNameTextField.setColumns(28);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(DisplayArea, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(openFileLabel)
								.addComponent(deleteFileLabel)
								.addComponent(renameFileLabel)
								.addComponent(createDirectoryLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(deleteFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(openFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(createDirectoryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(renameFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(newNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(openFileButton)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(18)
											.addComponent(createDirectoryButton))
										.addComponent(renameFileButton)))
								.addComponent(deleteFileButton))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(openFileLabel)
						.addComponent(openFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(openFileButton))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(deleteFileLabel)
						.addComponent(deleteFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteFileButton))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(renameFileLabel)
						.addComponent(renameFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(newNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(renameFileButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(createDirectoryLabel)
						.addComponent(createDirectoryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createDirectoryButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(DisplayArea, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void actionPerformed(ActionEvent e)
		{

			DisplayArea.setText("");

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

			try
			{
				showDir(new File("C:\\Users\\Erebus\\Desktop\\Test"));
			}
			catch(IOException e2)
			{
				e2.printStackTrace();
			}

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
						System.out.println("test");
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
