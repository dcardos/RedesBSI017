package urnaEletronica;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Danilo on 16/06/2017.
 */
public class ClienteConexao {

    public String conexaoRecebeCandidatos() throws IOException{
        // IP do servidor
        String server = "cosmos.lasdpc.icmc.usp.br";

        // Converte String de mensagem para bytes codificacao padrao
        byte[] byteBuffer = "888".getBytes();   // carregar candidatos

        // recebe porta por argumento opcional, default é a porta 7
        // porta do servidor
        int servPort = 40003;

        // Cria o socket que sera utilizado para se conectar ao servidor na porta especificada
        Socket socket = new Socket(server, servPort);

        System.out.println("Conectado ao servidor... enviando a mensagem");

        InputStream in = socket.getInputStream();

        OutputStream out = socket.getOutputStream();

        out.write(byteBuffer); // Envia a mensagem codificada ao servidor

        // Recebe a mesma mensagem de volta
        int totalBytesRcvd = 0;

        int bytesRcvd;

        // TODO: saber quando parar de ler (qual resposta?)
        while (totalBytesRcvd < byteBuffer.length) {

            if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1)
                throw new SocketException("Conexao fechada inesperadamente");

            totalBytesRcvd += bytesRcvd;
        }

        System.out.println("Mensagem recebida: " + new String(byteBuffer));

        socket.close();

        return new String(byteBuffer);
    }
}
