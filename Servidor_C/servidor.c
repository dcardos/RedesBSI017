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

#define NCANDIDATOS  5

typedef struct candidato{
    int codigo_votacao;
    char nome_candidato[10];
    char partido[5];
    int num_votos;
} Candidato;

// colocar como variável global (será usadao nas threads)
Candidato candidatos[NCANDIDATOS];

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

    printf("\nServidor TCP esperando por conexoes na porta 40003\n");
    fflush(stdout);


    // Loop para receber varias solicitacoes
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

        connected = accept(sock, (struct sockaddr *)&client_addr, &sin_size);
        printf("\nConexão aceita de (%s , %d)\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));

        // Loop para manter a troca de mensagens
        int flag = 1;
        while (flag == 1)
        {
            printf("\nOuvindo cliente (trava enquanto cliente não falar algo)\n");
            // Funcao recv (int socket, void *buffer, size_t size, int flags)
            bytes_recv = recv(connected, recv_data, 1024, 0);
            if (bytes_recv < 0) {
                printf("\nNão consegui ler do cliente, fechando socket do cliente\n");
                fflush(stdout);
                close(connected);
                flag = 0;
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
                send(connected,preparado,strlen(preparado),0);
                fflush(stdout);
                close(connected);
                printf("\nDado dos candidatos enviado, socket do cliente fechado\n");
                flag = 0;
            } else {
                printf("\nCliente mandou %d bytes de algo inesperado, fechando socket do cliente\n", bytes_recv);
                fflush(stdout);
                close(connected);
                flag = 0;
            }
            
            if (strcmp(recv_data,"888!") == 0){
                bytes_recv2 = recv(connected, recv_data2, 1024, 0);
                
                if (bytes_recv2 < 0) {
                    printf("\nNão consegui ler do cliente, fechando socket do cliente\n");
                    fflush(stdout);
                    close(connected);
                    flag = 0;
                }
                recv_data2[bytes_recv2] = '\0';
                printf("\nCliente falou: %s\n", recv_data2);
                fflush(stdout);
                
                //Conta quantos candidatos existem para ser atualizados
                int count = 0;
                for(int i=0; i < strlen(recv_data2); i++){
                    if(strcmp(recv_data2, ";") == 0)
                        count++;
                }
                
                
                int i=0;
                int j=0;
                int codInt;
                int nVotosInt;
                
                //Computa os votos para cada candidato
                while(i != count){
                    char cod[10];
                    char nVotos[10];
                    strcpy(cod,"");
                    strcpy(nVotos,"");
                    
                    while(strcmp(recv_data2[j], ",") == 1){
                        strcat(cod,recv_data2[j]);
                        j++;
                    }
                    
                    j++;
                    
                    while(strcmp(recv_data2[j], ";") == 1){
                        strcat(nVotos,recv_data2[j]);
                        j++;
                    }
                    
                    j++;
                    
                    codInt = atoi(cod);
                    nVotosInt = atoi(nVotos);
                    
                    for(int k = 0; k < NCANDIDATOS; k++){
                        if(candidatos[i].codigo_votacao == codInt)
                            candidatos[i].num_votos += nVotosInt;
                    }
                    
                    
                    i++;
                }
               
            }
            // TODO: fazer caso do 888 (cliente quer enviar voto da urna)
            printf("\nDegub: final do loop infinito\n");
            fflush(stdout);
        }
    }
    printf("\nFechando socket do servidor\n");
    close(sock);
    return 0;
}
