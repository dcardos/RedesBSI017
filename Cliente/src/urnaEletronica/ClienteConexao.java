package urnaEletronica;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by Danilo on 16/06/2017.
 */
public class ClienteConexao {

    public String conexaoRecebeCandidatos() throws IOException{
        // IP do servidor
        String server = "cosmos.lasdpc.icmc.usp.br";

        // Converte String de mensagem para bytes codificacao padrao
        byte[] byteBuffer = "999!".getBytes();   // carregar candidatos

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
        String messageString = new String();
        while (messageString.indexOf('!')==-1) {
            if ((bytesRcvd = in.read(byteBuffer)) == -1)
                throw new SocketException("Conexao fechada inesperadamente");

            messageString += new String(byteBuffer, 0, bytesRcvd);
            System.out.println("totalBytesRcvd = " + messageString.length() + " mensagem parcial: " + messageString);
        }

        System.out.println("Mensagem recebida: " + messageString);

        socket.close();

        return messageString;
    }

    public String conexaoEnviaRecebeVotos(ArrayList<Candidato> candidatos) throws IOException{
        // IP do servidor
        String server = "cosmos.lasdpc.icmc.usp.br";

        // Converte String de mensagem para bytes codificacao padrao
        StringBuilder dados = new StringBuilder();
        for (Candidato candidato : candidatos) {
            dados.append(candidato.getCodigo_votacao());
            dados.append(",");
            dados.append(candidato.getNum_VotosUrna());
            dados.append(";");
        }
        dados.append("!");
        byte[] byteBufferDados = dados.toString().getBytes();   // carregar candidatos
        byte[] byteBufferOpCode = "888!".getBytes();

        // recebe porta por argumento opcional, default é a porta 7
        // porta do servidor
        int servPort = 40003;

        // Cria o socket que sera utilizado para se conectar ao servidor na porta especificada
        Socket socket = new Socket(server, servPort);
        System.out.println("Conectado ao servidor... enviando votos");

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        out.write(byteBufferOpCode); // Envia a mensagem opcode

        String messageString = new String();
        int bytesRcvd;
        // le resposta "ok!" do servidor
        while (messageString.indexOf('!')==-1) {
            if ((bytesRcvd = in.read(byteBufferOpCode)) == -1)
                throw new SocketException("Conexao fechada inesperadamente!");
            messageString += new String(byteBufferOpCode, 0, bytesRcvd);
            System.out.println("totalBytesRcvd = " + messageString.length() + " mensagem parcial: " + messageString);
        }
        // verifica ok do servidor para enviar votos
        if (messageString.equals("ok!")) {
            out.write(byteBufferDados); // Envia os dados da votação da urna
        } else {
            throw new SocketException("Não consegui saber se servidor estava pronto para receber votos!");
        }
        // recebe numero de votos atualizados
        messageString = new String();
        while (messageString.indexOf('!')==-1) {
            if ((bytesRcvd = in.read(byteBufferOpCode)) == -1)
                throw new SocketException("Conexao fechada inesperadamente");
            messageString += new String(byteBufferOpCode, 0, bytesRcvd);
            System.out.println("totalBytesRcvd = " + messageString.length() + " mensagem parcial- " + messageString);
        }
        socket.close();
        return messageString;
    }
}
