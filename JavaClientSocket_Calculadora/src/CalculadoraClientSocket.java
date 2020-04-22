
//Importa��o das classes
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class CalculadoraClientSocket {

	public static void main(String args[]) {

		Socket socket = null;
		
		try {
			//Cria��o de um  menu para escolha da opera��o a ser realizada.
			String menu = "";
			String operation = String.format(
			"%6sMENU\n" 
			+ "%d - SOMA\n" 
			+ "%d - SUBTRA��O\n" 
			+ "%d - MULTIPLICA��O\n"	
			+ "%d - DIVIS�O\n" + "%d - SAIR\n", "", 1, 2, 3, 4, 5);
            //Utiliza��o do do-while para execu��o das instru��es
			do {
				try {
					menu = JOptionPane.showInputDialog(null, operation);
                    //Checanco se foi escolhida alguma das op��es de opera��o
					if (Integer.parseInt(menu) >= 1 && Integer.parseInt(menu) <= 4) {
						//Obtendo ip do localhost
						InetAddress hostServer = InetAddress.getByName("localhost");
						socket = new Socket(hostServer, 9090);//Passando como par�metros o ip e porta do servidor
                        //Fluxo de entrada dos dados do socket
						DataInputStream in = new DataInputStream(socket.getInputStream());
						 //Fluxo de sa�da dos dados do socket
						DataOutputStream out = new DataOutputStream((socket.getOutputStream()));
                        //Cria��o dos casos utilizando o switch, tratando a convers�o das op��es do menu
						switch (Integer.parseInt(menu)) {
						case 1:
							out.writeUTF("sum");//Gravando o fluxo e sa�da da soma
							break;
						case 2:
							out.writeUTF("sub");//Gravando o fluxo e sa�da da subtra��o
							break;
						case 3:
							out.writeUTF("mul");//Gravando o fluxo e sa�da da multiplica��o
							break;
						case 4:
							out.writeUTF("div");//Gravando o fluxo e sa�da da divis�o
							break;
						}//Cria��o do for para o usu�rio digitar os n�meros
						for (int x = 0; x < 2; x++) {
							try {  
								//Retornando valores do tipo float
								out.writeFloat(Float.parseFloat(JOptionPane.showInputDialog(null,
										"Digite um n�mero" + (x + 1) + "� n�mero : ")));
							//Mensagem de erro caso usu�rio digite n�meros negativos
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "N�mero inv�lido!\n Digite outro n�mero!");
								x--;
							}
						}
                        //Leitura da resposta
						String resposta = in.readUTF();
						JOptionPane.showMessageDialog(null, "Resposta: " + resposta);
						in.close();//Fechando leitura
					//Condi��o para sair da calculadora
					} else if (Integer.parseInt(menu) == 5) {
						JOptionPane.showMessageDialog(null, "Fim da aplica��o!");
					} //Mensagem caso o usu�rio digite um n�mero maior que cinco
					else {
						JOptionPane.showMessageDialog(null, "Op��o inv�lida!");
					}
                  //Caso em que usu�rio digite letras ou nomes
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalido!\nDigite um n�mero!");
					menu = "0";
				}
			} while (Integer.parseInt(menu) != 5);
        //Mensagem de erro caso o n�mero seja diferente de 5
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro!" + e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		//Tratando a quest�o do socket ser diferente de null
		} finally {
			if (socket != null) {
				try {
					socket.close();//Fechando conex�o do socket
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro!" + e.getMessage(), "Exception",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
