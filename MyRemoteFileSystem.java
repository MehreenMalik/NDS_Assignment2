import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteFileSystem extends Remote
{
	public int openFile(String name) throws RemoteException, IOException;
}