import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//Defini��o da Classe
public class CalculadoraCliente {
//Implementa��o do m�todo main	
	public static void main(String[] args) {		
		Registry reg = null;
		ICalculadora c;
//Utiliza��o da Scanner para auxiliar na leitura dos dados informados
		Scanner s = new Scanner (System.in);
//Cria��o de um menu com as op��es de opera��es
		System.out.println("Escolha uma op��o: \n "
				+ "1-Soma \n"
				+ "2-Subtra��o \n"
				+ "3-Multiplica��o\n"
				+ "4-Divis�o");
		try {
           //Obtendo informa��o pra registro local usando a porta padr�o
			reg = LocateRegistry.getRegistry(1099);
			c = (ICalculadora) reg.lookup("calculadora");
            //Utiliza��o da choice para manipula��o de eventos
			int choice = s.nextInt();
			int a,b;
            // Switch para as op��es e informa��o das opera��es soma, sub, mult e div escolhidas pelo usu�rio
			switch(choice) {
			//Op��o de soma
			case 1:{
				System.out.println("Digite o n�mero a: ");
				a=s.nextInt();
				System.out.println("Digite o n�mero b: ");
				b=s.nextInt();
				System.out.println("Resultado: ");
             //Chamada da fun��o soma fazendo refer�ncia aos valores a e b
				System.out.println(c.soma(a, b));
				break;	
			}
            //Op��o de subtra��o
			case 2:{
				System.out.println("Digite o n�mero a: ");
				a=s.nextInt();
				System.out.println("Digite o n�mero b: ");
				b=s.nextInt();
				System.out.println("Resultado:");
               //Chamada da fun��o sub fazendo refer�ncia aos valores a e b
				System.out.println(c.sub(a, b));
				break;	
			}
			//Op��o de multiplica��o
			case 3:{
				System.out.println("Digite o n�mero a: ");
				a=s.nextInt();
				System.out.println("Digite o n�mero b: ");
				b=s.nextInt();
				System.out.println("Resultado: ");
                //Chamada da fun��o mult fazendo refer�ncia aos valores a e b
				System.out.println(c.mult(a, b));
				break;
			}
			//Op��o de divis�o
			case 4:{
				System.out.println("Digite o n�mero a: ");
				a=s.nextInt();
				System.out.println("Digite o n�mero b: ");
				b=s.nextInt();
				System.out.println("Resultado:");
                //Chamada da fun��o div fazendo refer�ncia aos valores a e b
				System.out.println(c.div(a, b));
				break;
			}

			}
            //Comando para fechar a classe Scanner
			s.close(); 
			
		} catch (RemoteException | NotBoundException e) {
			System.out.println(e);
			System.exit(0);
	}
	}		

}
