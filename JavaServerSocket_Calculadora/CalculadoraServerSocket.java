//Importação das classes
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;


public class CalculadoraServerSocket {
    
    public static void main(String[] args) {
    
        Socket client = null;

        try 
        {
        	//Criando um servidor socket passando como parâmetro a porta 9090
            ServerSocket server = new ServerSocket(9090);
            //Obtendo o número da porta
            System.out.println("Server started: "+server.getLocalPort());
            
            while(true) //Utilização de laço de repetição para realizar os procedimentos
            {
                client = server.accept();//Verificação e aceitação de conexão 
                //Fluxo de entrada dos dados do cliente
                DataInputStream in = new DataInputStream(client.getInputStream());
                //Fluxo de saída dos dados do cliente
                DataOutputStream out = new DataOutputStream((client.getOutputStream()));
                //Obtendo o endereço e porta para conexão do socket
                System.out.println("host recebendo operação : "+client.getInetAddress()+":"+client.getPort());
               //Leitura de dados
                String operation = in.readUTF();
                float num1 = in.readFloat();
                float num2 = in.readFloat();
                //Envio de dados 
                out.writeUTF(operations(operation, num1, num2));
                server.close();//Fecha conexão com servidor

            }  
        }   
        catch(IOException e) 
        {
           JOptionPane.showMessageDialog(null, "Erro!"+e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            if(client != null) //Se client for diferente de null
            {
                try 
                {
                    client.close();//Fecha conexão com cliente
                } 
                catch (IOException e) //Mensagem de erro
                {
                    JOptionPane.showMessageDialog(null, "Erro!"+e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        }  
    }
    public static String operations(String op, float n1, float n2)//Criação de uma classe para tratar as operações
    {
        String response;//String utilizada para manipulação de resposta
        if(op.equalsIgnoreCase("sum"))//Comparação de string desconsiderando diferenças de cracteres
        {
            response = String.valueOf(n1 + n2);//String representando os valores float da operação adição
        }
        else if(op.equalsIgnoreCase("sub"))
        {
            response = String.valueOf(n1 - n2);//String representando os valores float da operação subtração
        }
        else if(op.equalsIgnoreCase("mul"))
        {
            response = String.valueOf(n1 * n2);//String representando os valores float da operação multiplicação
        }
        else if(op.equalsIgnoreCase("div"))
        {
            if(n2 != 0)//Condição para realização da divisão
            {
                response = String.valueOf(n1 / n2);////String representando os valores float da operação divisão
            }
            //Criação de else para indicar que a resposta da divisão é inválida caso não atenda a condição
            else
            {
                response = "divisão por 0 é inválida!";
            }
        }
        else
        {
            response = "Operação Inválida!";
          
        }
        return response;//Retorno da resposta da operação realizada
        
    }
    
}
