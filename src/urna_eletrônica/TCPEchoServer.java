/*
 *  Criado pelos alunos:
 * 
 *  Murilo Pratavieira                      No USP 8123082
 *  Danilo Guarnieri Cardoso                No USP 10442277
 *  Gustavo Cesar Leite De Oliveira Santos  No USP 
 * 
 */

package urna_eletrônica;

import java.net.*; // Para Socket, ServerSocket
import java.io.*; // Para IOException e Input/OutputStream

public class TCPEchoServer {

	private static final int BUFSIZE = 32; // Tamanho do buffer de recepcao
	public TCPEchoServer (String[] args) throws IOException {

            if (args.length != 1)
                   throw new IllegalArgumentException("Parametro(s): <Porta>");

            int servPort = Integer.parseInt(args[0]);

            // Cria um server socket para aceitar conexoes do cliente
            ServerSocket servSock = new ServerSocket(servPort);
            System.out.println("Servidor pronto para aceitar conexoes...");	
            int recvMsgSize; // Tamanho da mensagem de recepção
            byte[] byteBuffer = new byte[BUFSIZE]; // Buffer de recpcao

            for (;;) { // Sempre em execucao, aceitando conexoes
                    Socket clntSock = servSock.accept(); // Aceita a conexao com o cliente
                    System.out.println("Atendimento do cliente " + 
                            clntSock.getInetAddress().getHostAddress() + " na porta " + clntSock.getPort());
                    InputStream in = clntSock.getInputStream();
                    OutputStream out = clntSock.getOutputStream();
                    // Recebe dados até que a conexao com o cliente seja finalizada, indicada pelo -1 
                    while ((recvMsgSize = in.read(byteBuffer)) != -1)
                           out.write(byteBuffer, 0, recvMsgSize);
                    clntSock.close(); // Fecha o socket.
            } // Fim do For
	}
}
