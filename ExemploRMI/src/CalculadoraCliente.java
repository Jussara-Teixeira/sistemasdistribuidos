import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//Definição da Classe
public class CalculadoraCliente {
//Implementação do método main	
	public static void main(String[] args) {		
		Registry reg = null;
		ICalculadora c;
//Utilização da Scanner para auxiliar na leitura dos dados informados
		Scanner s = new Scanner (System.in);
//Criação de um menu com as opções de operações
		System.out.println("Escolha uma opção: \n "
				+ "1-Soma \n"
				+ "2-Subtração \n"
				+ "3-Multiplicação\n"
				+ "4-Divisão");
		try {
           //Obtendo informação pra registro local usando a porta padrão
			reg = LocateRegistry.getRegistry(1099);
			c = (ICalculadora) reg.lookup("calculadora");
            //Utilização da choice para manipulação de eventos
			int choice = s.nextInt();
			int a,b;
            // Switch para as opções e informação das operações soma, sub, mult e div escolhidas pelo usuário
			switch(choice) {
			//Opção de soma
			case 1:{
				System.out.println("Digite o número a: ");
				a=s.nextInt();
				System.out.println("Digite o número b: ");
				b=s.nextInt();
				System.out.println("Resultado: ");
             //Chamada da função soma fazendo referência aos valores a e b
				System.out.println(c.soma(a, b));
				break;	
			}
            //Opção de subtração
			case 2:{
				System.out.println("Digite o número a: ");
				a=s.nextInt();
				System.out.println("Digite o número b: ");
				b=s.nextInt();
				System.out.println("Resultado:");
               //Chamada da função sub fazendo referência aos valores a e b
				System.out.println(c.sub(a, b));
				break;	
			}
			//Opção de multiplicação
			case 3:{
				System.out.println("Digite o número a: ");
				a=s.nextInt();
				System.out.println("Digite o número b: ");
				b=s.nextInt();
				System.out.println("Resultado: ");
                //Chamada da função mult fazendo referência aos valores a e b
				System.out.println(c.mult(a, b));
				break;
			}
			//Opção de divisão
			case 4:{
				System.out.println("Digite o número a: ");
				a=s.nextInt();
				System.out.println("Digite o número b: ");
				b=s.nextInt();
				System.out.println("Resultado:");
                //Chamada da função div fazendo referência aos valores a e b
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
