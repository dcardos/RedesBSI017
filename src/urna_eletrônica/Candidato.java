/*
 *  Criado pelos alunos:
 * 
 *  Murilo Pratavieira                      No USP 8123082
 *  Danilo Guarnieri Cardoso                No USP 10442277
 *  Gustavo Cesar Leite De Oliveira Santos  No USP 
 * 
 */
package urna_eletrônica;

public class Candidato {
    
    private int codigo_votacao;
    private String nome_candidato;
    private String partido;
    private int num_votos;
    
    public Candidato(Candidato candidato) {
        candidato.codigo_votacao = 0;
        candidato.nome_candidato = "Maurício";
        candidato.partido = "Partido Sério";      
        candidato.num_votos = 0;
    }
       
    public int getCodigoVotacao() { 
        return codigo_votacao;
    } 
    public void setCodigoVotacao(int codigo_votacao) { 
        this.codigo_votacao = codigo_votacao;
    } 
    
    public String getNomeCandidato() { 
        return nome_candidato;
    } 
    public void setNomeCanditato(String nome_candidato) { 
        this.nome_candidato = nome_candidato;
    } 
    
    public String getNomePartido() { 
        return partido;
    } 
    public void setNomePartido(String partido) { 
        this.partido = partido;
    } 
    
    public int getNumVotos() { 
        return num_votos;
    } 
    public void setNumVotos(int num_votos) { 
        this.num_votos = num_votos;
    } 
    
    
}
