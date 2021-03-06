#include <sys/types.h>   // Definicao de tipos
#include <sys/socket.h>  // Biblioteca de estrutara de sockets
#include <netinet/in.h>  // Define os padroes de protocolo IP
#include <arpa/inet.h>   // Converte enderecos hosts
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> // Define constantes POSIX
#include <errno.h>  // Utilizado para capturar mensagens de erro
#include <string.h>
#include <pthread.h>
#include <unistd.h>

#define NCANDIDATOS  6

typedef struct candidato{
    int codigo_votacao;
    char nome_candidato[10];
    char partido[5];
    int num_votos;
} Candidato;

// colocar como variável global (será usadao nas threads)
Candidato candidatos[NCANDIDATOS];

void *ThreadDoCliente(void *socketDoCliente)
 {
    int clientSocket, bytes_recv, bytes_recv2;
    char recv_data[1024], recv_data2[1024];

    printf("\nThread do cliente ira dormir agora\n");
    fflush(stdout);
    clientSocket = (int)socketDoCliente;

    sleep(5); // dorme por 5 segundo para testar multiplas conexões simultaneas

    printf("\nOuvindo cliente (trava enquanto cliente não falar algo)\n");
    // Funcao recv (int socket, void *buffer, size_t size, int flags)
    bytes_recv = recv(clientSocket, recv_data, 1024, 0);
    if (bytes_recv < 0) {
        printf("\nNão consegui ler do cliente, fechando socket do cliente\n");
        fflush(stdout);
        close(clientSocket);
    }
    recv_data[bytes_recv] = '\0';
    printf("\nCliente falou: %s\n", recv_data);
    fflush(stdout);
    if (strcmp(recv_data,"999!") == 0)
    {
        char preparado[500];
        char buffer[12];
        int i;
        strcpy(preparado, "");
        for (i = 0; i<NCANDIDATOS; i++){
            snprintf(buffer, 12,"%d",candidatos[i].codigo_votacao);
            strcat(preparado, buffer);
            strcat(preparado, ",");
            strcat(preparado, candidatos[i].nome_candidato);
            strcat(preparado, ",");
            strcat(preparado, candidatos[i].partido);
            strcat(preparado, ",");
            snprintf(buffer, 12,"%d",candidatos[i].num_votos);
            strcat(preparado, buffer);
            strcat(preparado, ";"); // final de um candidato
        }
        strcat(preparado, "!"); // final da mensagem
        printf("\nSera enviado: %s\n", preparado);
        // Função send(int socket, void*buffer, size_t size, int flags)
        send(clientSocket, preparado, strlen(preparado), 0);
        fflush(stdout);
        close(clientSocket);
        printf("\nDado dos candidatos enviado, socket do cliente fechado\n");

    } else if (strcmp(recv_data,"888!") == 0){

        printf("Enviando resposta: ok!\n");
        fflush(stdout);
        char resposta[4] = "ok!";
        send(clientSocket, resposta, strlen(resposta), 0);

        printf("\nEsperando cliente mandar os votos da urna\n");
        fflush(stdout);
        bytes_recv2 = recv(clientSocket, recv_data2, 1024, 0);
        if (bytes_recv2 < 0) {
            printf("\nNão consegui ler do cliente, fechando socket do cliente\n");
            fflush(stdout);
            close(clientSocket);
        }
        recv_data2[bytes_recv2] = '\0';
        printf("\nCliente falou: %s. Fazendo parser dos dados\n", recv_data2);
        fflush(stdout);

        //Conta quantos candidatos existem para ser atualizados
        int count = 0;
        int i=0;
        int j=0;
        int k=0;
        int codInt;
        int nVotosInt;

        while(recv_data2[j] != '!'){
            if (recv_data2[j] == ',')
                count++;
            j++;
        }
        printf("\nDegub: count = %d\n", count);
        fflush(stdout);
        
        //Computa os votos para cada candidato
        j = 0;
        while(i < count){
            k=0;
            char cod[10];
            char nVotos[10];
            strcpy(cod,"");
            strcpy(nVotos,"");
            
            while(recv_data2[j] != ','){
                cod[k] = recv_data2[j];
                //printf("O valor eh %c \n", cod[k]);
                k++;
                j++;
            }
            k=0;
            j++;
            
            while(recv_data2[j] != ';'){
                nVotos[k] = recv_data2[j];
                //printf("O valor eh %c \n", nVotos[k]);
                k++;
                j++;
            }
            
            j++;
            
            codInt = atoi(cod);
            //printf("O valor eh %d", codInt);
            nVotosInt = atoi(nVotos);
            //printf("O valor eh %d", nVotos);
            
            for(int k = 0; k < NCANDIDATOS; k++){
                if(candidatos[k].codigo_votacao == codInt)
                    candidatos[k].num_votos += nVotosInt;
            }
            
            i++;
        }  
        printf("\nParser concluido, preparando para enviar o numero de votos total.\n");
        fflush(stdout);
        char preparado[250];
        char buffer[12];
        strcpy(preparado, "");
        for (i = 0; i<NCANDIDATOS; i++){
            snprintf(buffer, 12,"%d",candidatos[i].codigo_votacao);
            strcat(preparado, buffer);
            strcat(preparado, ",");
            snprintf(buffer, 12,"%d",candidatos[i].num_votos);
            strcat(preparado, buffer);
            strcat(preparado, ";"); // final de um candidato
        }
        strcat(preparado, "!"); // final da mensagem
        printf("\nSera enviado: %s\n", preparado);
        fflush(stdout);
        // Função send(int socket, void*buffer, size_t size, int flags)
        send(clientSocket,preparado,strlen(preparado),0);
        close(clientSocket);
        printf("\nVoto de cada candidato enviado, socket do cliente fechado\n");
        fflush(stdout);

    } else {

        printf("\nCliente mandou %d bytes de algo inesperado, fechando socket do cliente\n", bytes_recv);
        fflush(stdout);
        close(clientSocket);
    }

    pthread_exit(NULL);
 }

int main(int argc, char *argv[ ])
{
    // Preparando dados dos candidatos
    candidatos[0].codigo_votacao = 1;
    strcpy(candidatos[0].nome_candidato, "Joao");
    strcpy(candidatos[0].partido, "AB");
    candidatos[0].num_votos = 0;
    candidatos[1].codigo_votacao = 2;
    strcpy(candidatos[1].nome_candidato, "Maria");
    strcpy(candidatos[1].partido, "CD");
    candidatos[1].num_votos = 0;
    candidatos[2].codigo_votacao = 3;
    strcpy(candidatos[2].nome_candidato, "Carlos");
    strcpy(candidatos[2].partido, "EF");
    candidatos[2].num_votos = 0;
    candidatos[3].codigo_votacao = 4;
    strcpy(candidatos[3].nome_candidato, "Suzana");
    strcpy(candidatos[3].partido, "GH");
    candidatos[3].num_votos = 0;
    candidatos[4].codigo_votacao = 5;
    strcpy(candidatos[4].nome_candidato, "Sofia");
    strcpy(candidatos[4].partido, "IJ");
    candidatos[4].num_votos = 0;
    candidatos[5].codigo_votacao = 6;
    strcpy(candidatos[5].nome_candidato, "Nulo");
    strcpy(candidatos[5].partido, "-");
    candidatos[4].num_votos = 0;

    /*
     Estrutura de um socket

     struct sockaddr_in {
            short int sin_family; -> Familia do endereço do socket:
         1) AF_INET- ARPA INTERNET  PROTOCOLS
         2) AF_UNIX- UNIX INTERNET PROTOCOLS
         3)AF_ISSO- ISO PROTOCOLS
         4)AF_NS- XEROX NETWORK SYSTEM PROTOCOLS

        unsigned short int sin_port; -> Numero da porta de comunicacao
            struct in_addr sin_addr; -> Endereco IP
            unsigned char sin_zero[8]; -> Zerar a estrutura do socket - nao utilizado

      Um socket (AF_INET) pode ter três tipos de estilo:
     "SOCK_STREAM" (TCP - Stream de Dados - Stream Socket),
     "SOCK_DGRAM" (UDP - Datagrama - Datagram Sockets) ou
     "SOCK_RAW" (Baixo Nivel de Rede, normalmente nao utilizado)
    */


    //Variaveis
    int sock, connected, bytes_recv, bytes_recv2, i, true = 1;
    char send_data [1024] , recv_data[1024], recv_data2[1024];
    struct sockaddr_in server_addr, client_addr;
    int sin_size;

    /* Funcao socket(sin_family,socket_type,protocol_number) retorna um inteiro (socket descriptor), caso erro retorna -1

       O numero do protocolo (protocol_number) pode ser algum dos seguintes:
            0 - IP - Internet Protocol (Default)
            1 - ICMP - Internet Control Message Protocol
            2 - IGMP - Internet Group Multicast Protocol
            3 - GGP - Gateway-Gateway Protocol
            6 - TCP - Transmission Control Protocol
            17 - UDP - User Datagram Protocol
    */

    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1)
    {
        perror("Erro no Socket");
        exit(1);
    }


    /* Funcao setsockopt(int socket, int level, int optname, void*optval, size_t optlen)

    Esta funcao seta o valor (optval) de uma opcao (optname) em um certo nivel (level) de camada de protocolo no socket

    int socket = descriptor do socket

    int level = nivel da camada do protocolo (SOL_SOCKET = Constante de nivel para o socket, outros: IPPROTO_IP, IPPROTO_TCP, IPPROTO_UDP)

    int optname = Opcao desejada para a alteracao

    optval = valor da opcao

    optlen = tamanho do valor

    Neste exemplo iremos alterar o valor no nivel de socket para a opcao SO_REUSEADDR. Por default um socket criado aceita apenas
    uma conexao por endereco, ou seja o valor de SO_REUSEADDR é igual FALSE (0). Para alterar esse valor e permitirmos que o
    mesmo endereco possa receber varias conexoes devemos alterar o valor da opcao SO_REUSEADDR para TRUE (1).

    */

    if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &true,sizeof(int)) == -1)
    {
        perror("Erro Setsockopt");
        exit(1);
    }


    // Configura o endereco de destino
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(40003);
    server_addr.sin_addr.s_addr = INADDR_ANY;
    bzero(&(server_addr.sin_zero),8);


    /* Uma vez com o socket criado precisamos informar o endereço ao socket. Para isso utilizamos a funcao bind

    Funcao bind(int socket, struct sockaddr*addr, size_t length)

    int socket = descriptor do socket

    struct sockaddr*addr = endereco de destino

    size_t length = tamanho do endereco

    A funcao bind retorna 0 em caso de sucesso e -1 em caso de erro
    */

    if (bind(sock, (struct sockaddr *)&server_addr, sizeof(struct sockaddr)) == -1)
    {
        perror("Nao foi possivel realizar o bind");
        exit(1);
    }


    /* Como estamos criando um servidor que ira receber solicitacoes este socket deve ficar "ouvindo" (aguardando conexoes) na
    porta especificada. A funcao listen realiza essa tarefa.

    Funcao listen(int socket, unsigned int n) onde;

    int socket = descriptor do socket

    unsigned int n = tamanho da fila de conexoes pendentes

    Obs: Quando utilizamos o listen devemos utilizar a funcao accept que veremos mais adiante no codigo

    */

    if (listen(sock, 10) == -1)
    {
        perror("Erro de Listen");
        exit(1);
    }

    // Loop para receber varias solicitacoes
    pthread_t thread;
    int rc;
    while(1)
    {

        // Variavel para armazenar o tamanho de endereco do cliente conectado
        sin_size = sizeof(struct sockaddr_in);

        /* Funcao accept(int socket, struct sockaddr*addr, size_t*length_ptr)
      	A funcao accept aceita uma conexao e cria um novo socket para esta conexao
            	
      	int socket = descriptor do socket
            
      	struct sockaddr*addr = endereco de destino (cliente)
            
      	size_t*length_ptr = tamanho do endereco de destino

    	Obs: A funcao accept por padrão fica aguardando a chegada de um pedido de conexao. Para que ela nao fique, devemos
    	configurar o socket no modo sem bloqueio (nonblocking mode set). Neste exemplo ficaremos com o modo padrao (bloqueante)

    	A funcao accept retorna  

        */
        printf("\nServidor TCP esperando por conexoes na porta 40003\n");
        fflush(stdout);
        connected = accept(sock, (struct sockaddr *)&client_addr, &sin_size);
        printf("\nConexão aceita de (%s , %d)\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));
        rc = pthread_create(&thread, NULL, ThreadDoCliente, (void *)connected);
        if (rc){
          printf("ERROR; return code from pthread_create() is %d\n", rc);
          exit(-1);
       }
    }

    printf("\nFechando socket do servidor\n");
    close(sock);
    return 0;
}
