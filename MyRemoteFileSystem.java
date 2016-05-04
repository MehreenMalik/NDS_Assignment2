import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteFileSystem extends Remote
{
	public int openFile(String name) throws RemoteException, IOException;
	//public int deleteFile(String name) throws RemoteException, IOException;
	//public int renameFile(String name) throws RemoteException, IOException;
	//public int createDirectory(String name) throws RemoteException, IOException;
}