//Importação das classes java do pacote io.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;//Importação da Classe URL
//Importação de classe que usa suporte de segurança e recursos HTTPS.
import javax.net.ssl.HttpsURLConnection;
//Criação da Classe CalculadoraClientHTTP do tipo pública
public class CalculadoraClientHTTP {
    //Classe principal
	public static void main(String[] args) {
    //Definição de uma String result vazia 		
	String result="";
    try {
       //Instanciando a classe URL
       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
       //Instanciando a HttpsURLConnection para efetuar a conexão com endereço informado
       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(10000);//Especificação do tempo de leitura
        conn.setConnectTimeout(15000);//Especificação do tempo para abrir o link
        conn.setRequestMethod("POST");//Especificação do método POST
        conn.setDoInput(true);//Conexão de URL para entrada com valor true
        conn.setDoOutput(true) ;//Conexão de URL para saída com valor true

        //ENVIO DOS PARAMETROS
      //Escrita de dados passando como parametro a obtenção dos dados
        OutputStream os = conn.getOutputStream();
      //fluxo de escrita e armazenamento
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        //Escrita de valores e especificação do tipo de operação a ser realizada
        writer.write("oper1=25&oper2=15&operacao=2"); //1-somar 2-subtrair 3-dividir 4-multiplicar
        writer.flush();//Liberação do fluxo de escrita
        writer.close();//Fecha a  conexão e escrita do arquivo
        os.close();//Fecha a escrita dos dados
        
        //Código de resposta retornado pelo servidor remoto
        int responseCode=conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            //RECEBIMENTO DOS PARAMETROS
        	//Leitura do fluxo de  entrada de dados e armazenamento no Buffer  
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            //Tratando as mudanças de caracteres
            StringBuilder response = new StringBuilder();
            String responseLine = null;//Definição das respostas das linhas como null
          //Enquanto a resposta da linha lida for diferente de null
            while ((responseLine = br.readLine()) != null) {
           //Realiza a adição da resposta
                response.append(responseLine.trim());
            }
            //retorno da resposta
            result = response.toString();//
            //Mostra o valor  das operações realizadas
            System.out.println("Resposta do Servidor PHP="+result);
        }
        //catch caso haja erros de comunicação
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
