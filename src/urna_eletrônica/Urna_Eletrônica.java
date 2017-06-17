/*
 *  Criado pelos alunos:
 * 
 *  Murilo Pratavieira                      No USP 8123082
 *  Danilo Guarnieri Cardoso                No USP 10442277
 *  Gustavo Cesar Leite De Oliveira Santos  No USP 
 * 
 */

package urna_eletrônica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Murilo Pratavieira
 */
public class Urna_Eletrônica {
   
    public static void main(String[] args) throws IOException {
        TCPEchoClient client;
        String[] cod_cliente = new String[10];
        
        System.out.println("Digite o cliente!");      
        Scanner scan= new Scanner(System.in);                       
        cod_cliente[0] = scan.nextLine(); 
        
        
        
        client = new TCPEchoClient(cod_cliente);
        Interface1 interface1 = new Interface1();                
        interface1.setVisible(true);                                    
    }
    
    
}
