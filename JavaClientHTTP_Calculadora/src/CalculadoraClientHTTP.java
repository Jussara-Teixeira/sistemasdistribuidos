//Importa��o das classes java do pacote io.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;//Importa��o da Classe URL
//Importa��o de classe que usa suporte de seguran�a e recursos HTTPS.
import javax.net.ssl.HttpsURLConnection;
//Cria��o da Classe CalculadoraClientHTTP do tipo p�blica
public class CalculadoraClientHTTP {
    //Classe principal
	public static void main(String[] args) {
    //Defini��o de uma String result vazia 		
	String result="";
    try {
       //Instanciando a classe URL
       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
       //Instanciando a HttpsURLConnection para efetuar a conex�o com endere�o informado
       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(10000);//Especifica��o do tempo de leitura
        conn.setConnectTimeout(15000);//Especifica��o do tempo para abrir o link
        conn.setRequestMethod("POST");//Especifica��o do m�todo POST
        conn.setDoInput(true);//Conex�o de URL para entrada com valor true
        conn.setDoOutput(true) ;//Conex�o de URL para sa�da com valor true

        //ENVIO DOS PARAMETROS
      //Escrita de dados passando como parametro a obten��o dos dados
        OutputStream os = conn.getOutputStream();
      //fluxo de escrita e armazenamento
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        //Escrita de valores e especifica��o do tipo de opera��o a ser realizada
        writer.write("oper1=25&oper2=15&operacao=2"); //1-somar 2-subtrair 3-dividir 4-multiplicar
        writer.flush();//Libera��o do fluxo de escrita
        writer.close();//Fecha a  conex�o e escrita do arquivo
        os.close();//Fecha a escrita dos dados
        
        //C�digo de resposta retornado pelo servidor remoto
        int responseCode=conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            //RECEBIMENTO DOS PARAMETROS
        	//Leitura do fluxo de  entrada de dados e armazenamento no Buffer  
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            //Tratando as mudan�as de caracteres
            StringBuilder response = new StringBuilder();
            String responseLine = null;//Defini��o das respostas das linhas como null
          //Enquanto a resposta da linha lida for diferente de null
            while ((responseLine = br.readLine()) != null) {
           //Realiza a adi��o da resposta
                response.append(responseLine.trim());
            }
            //retorno da resposta
            result = response.toString();//
            //Mostra o valor  das opera��es realizadas
            System.out.println("Resposta do Servidor PHP="+result);
        }
        //catch caso haja erros de comunica��o
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
