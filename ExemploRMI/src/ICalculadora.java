//Importa��o dos pacotes do Java
import java.rmi.Remote;
import java.rmi.RemoteException;
//Cria��o da interface ICalculadora e para que ela seja remota usa-se o extends
public interface ICalculadora extends Remote{
//M�todos remotos
	public int soma(int a, int b) throws RemoteException;
	public int sub(int a, int b) throws RemoteException;
	public int mult(int a, int b) throws RemoteException;
	public int div(int a, int b) throws RemoteException;
}
