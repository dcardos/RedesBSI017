/*
 *  Criado pelos alunos:
 * 
 *  Murilo Pratavieira                      No USP 8123082
 *  Danilo Guarnieri Cardoso                No USP 10442277
 *  Gustavo Cesar Leite De Oliveira Santos  No USP 
 * 
 */

package urna_eletrônica;

import java.net.*; // Para Socket

import java.io.*; // Para IOException e Input/OutputStream

public class TCPEchoClient {
	public TCPEchoClient (String[] args) throws IOException {
		if ((args.length < 2) || (args.length > 3)) 
			throw new IllegalArgumentException ("Parametros(s): <Servidor> <mensagem> [<Porta>]");
		String server = args[0];

		// Converte String de entrar para bytes codificacao padrao 
		byte[] byteBuffer = args[1].getBytes();

		int servPort=(args.length == 3) ? Integer.parseInt(args[2]) :7;

		// Cria o socket que sera utilizado para se conectar ao servidor na porta especificada
		Socket socket = new Socket(server, servPort);

		System.out.println("Conectado ao servidor... enviando a mensagem");
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		out.write(byteBuffer); // Envia a mensagem codificada ao servidor

		// Recebe a mesma mensagem de volta 
		int totalBytesRcvd = 0;

		int bytesRcvd;

		while (totalBytesRcvd < byteBuffer.length) {
			if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1)
				throw new SocketException("Conexao fechada inesperadamente");
			totalBytesRcvd += bytesRcvd;
		}

		System.out.println("Mensagem recebida: " + new String(byteBuffer));
		socket.close();
	}
}
