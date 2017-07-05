package urnaEletronica;

import javafx.scene.control.RadioButton;

/**
 * Created by Danilo on 20/06/2017.
 */
public class Candidato {
    private int codigo_votacao;
    private String nome_candidato;
    private String partido;
    private int num_votos;
    private RadioButton rbCandidato;
    private int num_VotosUrna;

    public Candidato(int codigo_votacao, String nome_candidato, String partido) {
        this.codigo_votacao = codigo_votacao;
        this.nome_candidato = nome_candidato;
        this.partido = partido;
        this.num_VotosUrna = 0;
        setRBCandidato();
    }

    public Candidato(int codigo_votacao, String nome_candidato, String partido, int num_votos) {
        this.codigo_votacao = codigo_votacao;
        this.nome_candidato = nome_candidato;
        this.partido = partido;
        this.num_votos = num_votos;
        this.num_VotosUrna = 0;
        setRBCandidato();
    }

    private void setRBCandidato() {
        this.rbCandidato = new RadioButton(this.nome_candidato + " - " + this.partido);
    }

    public RadioButton getRbCandidato() {
        return rbCandidato;
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

    public void incrementNum_votos() {
        this.num_VotosUrna++;
    }

    public int getNum_VotosUrna() {
        return num_VotosUrna;
    }

    public void setNum_VotosUrna(int num_VotosUrna) {
        this.num_VotosUrna = num_VotosUrna;
    }
}
