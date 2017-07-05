import java.net.*; // Para Socket, ServerSocket
import java.io.*; // Para IOException e Input/OutputStream
import java.util.ArrayList;

public class Servidor {

    private static final int BUFSIZE = 32; // Tamanho do buffer de recepcao
    private static ArrayList<CandidatoUrna> mCandidatos;

    public static void main (String[] args) throws IOException, Exception{

        if (args.length != 1)
            throw new IllegalArgumentException("Parametro(s): <Porta>");

        mCandidatos = new ArrayList<>();

        // candidatos no servidor
        mCandidatos.add(new CandidatoUrna(1, "Joao", "AB", 0));
        mCandidatos.add(new CandidatoUrna(2, "Maria", "CD", 0));
        mCandidatos.add(new CandidatoUrna(3, "Carlos", "EF", 0));
        mCandidatos.add(new CandidatoUrna(4, "Suzana", "GH", 0));
        mCandidatos.add(new CandidatoUrna(5, "Sofia", "IJ", 0));

        int servPort = Integer.parseInt(args[0]);

        // Cria um server socket para aceitar conexoes do cliente
        ServerSocket servSock = new ServerSocket(servPort);
        System.out.println("Servidor pronto para aceitar conexoes...");
        int recvMsgSize; // Tamanho da mensagem de recepção
        byte[] byteBuffer = new byte[BUFSIZE]; // Buffer de recpcao

        for (;;) { // Sempre em execucao, aceitando conexoes

            Socket clntSock = servSock.accept(); // Aceita a conexao com o cliente

            System.out.println("Atendimento do cliente " + clntSock.getInetAddress().getHostAddress() + " na porta " + clntSock.getPort());

            InputStream in = clntSock.getInputStream();

            OutputStream out = clntSock.getOutputStream();

            // Recebe dados até que "!" seja recebido

            int bytesRcvd;
            String messageString = new String();
            while (messageString.indexOf('!')==-1) {
                if ((bytesRcvd = in.read(byteBuffer)) == -1)
                    throw new SocketException("Conexao fechada inesperadamente");

                messageString += new String(byteBuffer, 0, bytesRcvd);

                System.out.println("totalBytesRcvd = " + messageString.length() + " mensagem parcial: " + messageString);
            }

            System.out.println("Cliente falou: " + messageString);
            if (messageString.equals("999!")) {
                // Converte String de mensagem para bytes codificacao padrao
                StringBuilder dados = new StringBuilder();
                for (CandidatoUrna candidato : mCandidatos) {
                    dados.append(candidato.getCodigo_votacao());
                    dados.append(",");
                    dados.append(candidato.getNome_candidato());
                    dados.append(",");
                    dados.append(candidato.getPartido());
                    dados.append(",");
                    dados.append(candidato.getNum_votos());
                    dados.append(";");
                }
                dados.append("!");
                byte[] byteBufferDados = dados.toString().getBytes();   // carregar candidatos
                System.out.println("Enviando ao cliente os dados: " + dados);
                // envia os dados
                out.write(byteBufferDados);
            } else if (messageString.equals("888!")) {
                byteBuffer = "ok!".getBytes();
                out.write(byteBuffer);

                messageString = new String();
                while (messageString.indexOf('!')==-1) {
                    if ((bytesRcvd = in.read(byteBuffer)) == -1)
                        throw new SocketException("Conexao fechada inesperadamente");

                    messageString += new String(byteBuffer, 0, bytesRcvd);

                    System.out.println("totalBytesRcvd = " + messageString.length() + " mensagem parcial: " + messageString);
                }
                // computa votos recebidos
                computaVotos(messageString);
            } else {
                System.out.println("OpCode ou mensagem indecifravel!");
            }

            clntSock.close(); // Fecha o socket.
        } // Fim do For

    }

    public static void computaVotos(String cadeia) throws Exception {
        if (cadeia == null)
            throw new Exception("Cadeia vazia!");
        else if (!cadeia.contains(","))
            throw new Exception("Cadeia fora do padrão, sem vírgulas");
        else if (!cadeia.contains(";"))
            throw new Exception("Cadeia fora do padrão, sem ponto e vírgulas");
        else if (!cadeia.contains("!"))
            throw new Exception("Cadeia fora do padrão, sem exclamação");
        String[] parts = cadeia.split(",|;");
        int i = 0;
        boolean end = false;
        while(!end){
            int codigoVotacao = Integer.parseInt(parts[2*i]);
            int numVotos = Integer.parseInt(parts[1+2*i]);
            for(CandidatoUrna candidato : mCandidatos){
                if (candidato.getCodigo_votacao() == codigoVotacao) {
                    candidato.incrementNum_votos(numVotos);
                }
            }
            if (parts[2+2*i].equals("!"))
                end = true;
            i++;
        }
    }
}

class CandidatoUrna {
    private int codigo_votacao;
    private String nome_candidato;
    private String partido;
    private int num_votos;

    public CandidatoUrna(int codigo_votacao, String nome_candidato, String partido) {
        this.codigo_votacao = codigo_votacao;
        this.nome_candidato = nome_candidato;
        this.partido = partido;
    }

    public CandidatoUrna(int codigo_votacao, String nome_candidato, String partido, int num_votos) {
        this.codigo_votacao = codigo_votacao;
        this.nome_candidato = nome_candidato;
        this.partido = partido;
        this.num_votos = num_votos;
    }

    public int getCodigo_votacao() {
        return codigo_votacao;
    }

    public void setCodigo_votacao(int codigo_votacao) {
        this.codigo_votacao = codigo_votacao;
    }

    public String getNome_candidato() {
        return nome_candidato;
    }

    public String getPartido() {
        return partido;
    }

    public int getNum_votos() {
        return num_votos;
    }

    public void setNum_votos(int num_votos) {
        this.num_votos = num_votos;
    }

    public void incrementNum_votos(int votosAMais) {
        this.num_votos += votosAMais;
    }
}