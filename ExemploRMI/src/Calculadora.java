import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//Classe calculadora para implementar ICalculadora
public class Calculadora  implements ICalculadora {
//Método referente a operação soma que retorna valores inteiros. 
	public int soma(int a, int b) throws RemoteException {
//retorno da soma dos métodos
		return a + b;
	}
//Método referente a operação subtração que retorna valores inteiros. 
	public int sub(int a, int b) throws RemoteException {
//retorno da subtração dos métodos
		return a - b;
	}
//Método referente a operação multiplicação que retorna valores inteiros.
	public int mult(int a, int b) throws RemoteException {
//retorno da multiplicação dos métodos
		return a * b;
	}
//Método referente a operação divisão que retorna valores inteiros.
	public int div(int a, int b) throws RemoteException {
//retorno da divisão dos métodos
		return a / b;
	}
//Implementação do método main
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		try {
			System.out.println("Servidor Conectado");
//Criação do registro
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("calculadora", new Calculadora());
			
		} catch (Exception e) {
			System.exit(0);
			}
				
		}
	
}

