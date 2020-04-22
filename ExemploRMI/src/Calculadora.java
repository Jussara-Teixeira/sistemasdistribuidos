import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//Classe calculadora para implementar ICalculadora
public class Calculadora  implements ICalculadora {
//M�todo referente a opera��o soma que retorna valores inteiros. 
	public int soma(int a, int b) throws RemoteException {
//retorno da soma dos m�todos
		return a + b;
	}
//M�todo referente a opera��o subtra��o que retorna valores inteiros. 
	public int sub(int a, int b) throws RemoteException {
//retorno da subtra��o dos m�todos
		return a - b;
	}
//M�todo referente a opera��o multiplica��o que retorna valores inteiros.
	public int mult(int a, int b) throws RemoteException {
//retorno da multiplica��o dos m�todos
		return a * b;
	}
//M�todo referente a opera��o divis�o que retorna valores inteiros.
	public int div(int a, int b) throws RemoteException {
//retorno da divis�o dos m�todos
		return a / b;
	}
//Implementa��o do m�todo main
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		try {
			System.out.println("Servidor Conectado");
//Cria��o do registro
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("calculadora", new Calculadora());
			
		} catch (Exception e) {
			System.exit(0);
			}
				
		}
	
}

