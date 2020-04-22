//Importa��o das classes
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
        	//Criando um servidor socket passando como par�metro a porta 9090
            ServerSocket server = new ServerSocket(9090);
            //Obtendo o n�mero da porta
            System.out.println("Server started: "+server.getLocalPort());
            
            while(true) //Utiliza��o de la�o de repeti��o para realizar os procedimentos
            {
                client = server.accept();//Verifica��o e aceita��o de conex�o 
                //Fluxo de entrada dos dados do cliente
                DataInputStream in = new DataInputStream(client.getInputStream());
                //Fluxo de sa�da dos dados do cliente
                DataOutputStream out = new DataOutputStream((client.getOutputStream()));
                //Obtendo o endere�o e porta para conex�o do socket
                System.out.println("host recebendo opera��o : "+client.getInetAddress()+":"+client.getPort());
               //Leitura de dados
                String operation = in.readUTF();
                float num1 = in.readFloat();
                float num2 = in.readFloat();
                //Envio de dados 
                out.writeUTF(operations(operation, num1, num2));
                server.close();//Fecha conex�o com servidor

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
                    client.close();//Fecha conex�o com cliente
                } 
                catch (IOException e) //Mensagem de erro
                {
                    JOptionPane.showMessageDialog(null, "Erro!"+e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        }  
    }
    public static String operations(String op, float n1, float n2)//Cria��o de uma classe para tratar as opera��es
    {
        String response;//String utilizada para manipula��o de resposta
        if(op.equalsIgnoreCase("sum"))//Compara��o de string desconsiderando diferen�as de cracteres
        {
            response = String.valueOf(n1 + n2);//String representando os valores float da opera��o adi��o
        }
        else if(op.equalsIgnoreCase("sub"))
        {
            response = String.valueOf(n1 - n2);//String representando os valores float da opera��o subtra��o
        }
        else if(op.equalsIgnoreCase("mul"))
        {
            response = String.valueOf(n1 * n2);//String representando os valores float da opera��o multiplica��o
        }
        else if(op.equalsIgnoreCase("div"))
        {
            if(n2 != 0)//Condi��o para realiza��o da divis�o
            {
                response = String.valueOf(n1 / n2);////String representando os valores float da opera��o divis�o
            }
            //Cria��o de else para indicar que a resposta da divis�o � inv�lida caso n�o atenda a condi��o
            else
            {
                response = "divis�o por 0 � inv�lida!";
            }
        }
        else
        {
            response = "Opera��o Inv�lida!";
          
        }
        return response;//Retorno da resposta da opera��o realizada
        
    }
    
}
