import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteFileSystem extends Remote
{
	public int openFile(String name) throws IOException;
	public int deleteFile(String name) throws IOException;
	public int renameFile(String name) throws IOException;
	public int createDirectory(String name) throws IOException;
}