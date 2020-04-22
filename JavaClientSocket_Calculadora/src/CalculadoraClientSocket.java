
//Importação das classes
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
			//Criação de um  menu para escolha da operação a ser realizada.
			String menu = "";
			String operation = String.format(
			"%6sMENU\n" 
			+ "%d - SOMA\n" 
			+ "%d - SUBTRAÇÃO\n" 
			+ "%d - MULTIPLICAÇÃO\n"	
			+ "%d - DIVISÃO\n" + "%d - SAIR\n", "", 1, 2, 3, 4, 5);
            //Utilização do do-while para execução das instruções
			do {
				try {
					menu = JOptionPane.showInputDialog(null, operation);
                    //Checanco se foi escolhida alguma das opções de operação
					if (Integer.parseInt(menu) >= 1 && Integer.parseInt(menu) <= 4) {
						//Obtendo ip do localhost
						InetAddress hostServer = InetAddress.getByName("localhost");
						socket = new Socket(hostServer, 9090);//Passando como parâmetros o ip e porta do servidor
                        //Fluxo de entrada dos dados do socket
						DataInputStream in = new DataInputStream(socket.getInputStream());
						 //Fluxo de saída dos dados do socket
						DataOutputStream out = new DataOutputStream((socket.getOutputStream()));
                        //Criação dos casos utilizando o switch, tratando a conversão das opções do menu
						switch (Integer.parseInt(menu)) {
						case 1:
							out.writeUTF("sum");//Gravando o fluxo e saída da soma
							break;
						case 2:
							out.writeUTF("sub");//Gravando o fluxo e saída da subtração
							break;
						case 3:
							out.writeUTF("mul");//Gravando o fluxo e saída da multiplicação
							break;
						case 4:
							out.writeUTF("div");//Gravando o fluxo e saída da divisão
							break;
						}//Criação do for para o usuário digitar os números
						for (int x = 0; x < 2; x++) {
							try {  
								//Retornando valores do tipo float
								out.writeFloat(Float.parseFloat(JOptionPane.showInputDialog(null,
										"Digite um número" + (x + 1) + "º número : ")));
							//Mensagem de erro caso usuário digite números negativos
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Número inválido!\n Digite outro número!");
								x--;
							}
						}
                        //Leitura da resposta
						String resposta = in.readUTF();
						JOptionPane.showMessageDialog(null, "Resposta: " + resposta);
						in.close();//Fechando leitura
					//Condição para sair da calculadora
					} else if (Integer.parseInt(menu) == 5) {
						JOptionPane.showMessageDialog(null, "Fim da aplicação!");
					} //Mensagem caso o usuário digite um número maior que cinco
					else {
						JOptionPane.showMessageDialog(null, "Opção inválida!");
					}
                  //Caso em que usuário digite letras ou nomes
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalido!\nDigite um número!");
					menu = "0";
				}
			} while (Integer.parseInt(menu) != 5);
        //Mensagem de erro caso o número seja diferente de 5
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro!" + e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		//Tratando a questão do socket ser diferente de null
		} finally {
			if (socket != null) {
				try {
					socket.close();//Fechando conexão do socket
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro!" + e.getMessage(), "Exception",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
