package urnaEletronica;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindowController {

    @FXML private Label userFeedback;
    @FXML private AnchorPane mainPane;
    @FXML private BarChart<String, Number> barChart;
    @FXML private Button btnVotar;
    @FXML private Button btnVotarBranco;
    @FXML private Button btnVotarNulo;
    @FXML private Button btnAtualizar;

    private Main mMain;
    private ArrayList<Candidato> mCandidatos;
    private ToggleGroup mGrupoCandidatos;

    public void setMain(Main main) throws IOException {
        mMain = main;

        btnVotar.setDisable(true);
        btnVotarBranco.setDisable(true);
        btnVotarNulo.setDisable(true);

        userFeedback.setText("Carregando mCandidatos, aguarde.");
//        ClienteConexao.conecta();

        mCandidatos = new ArrayList<>();
        // TODO: mock data, get this from the server
        Candidato candidato1 = new Candidato(11, "Mario", "PMI", 0);
        Candidato candidato2 = new Candidato(22, "Megaman", "IMM", 0);
        Candidato candidato3 = new Candidato(33, "Donkey", "FMDM", 0);
        mCandidatos.add(candidato1);
        mCandidatos.add(candidato2);
        mCandidatos.add(candidato3);

        userFeedback.setText("Candidatos carregados. Escolha abaixo:");

        mGrupoCandidatos = new ToggleGroup();

        Double i = 0.0;
        for (Candidato candidato : mCandidatos) {
            candidato.getRbCandidato().setToggleGroup(mGrupoCandidatos);
            mainPane.getChildren().add(candidato.getRbCandidato());
            AnchorPane.setTopAnchor(candidato.getRbCandidato(), 130 + 30*i);
            AnchorPane.setLeftAnchor(candidato.getRbCandidato(), 46.0);
            i++;
        }

        btnVotar.setDisable(false);
        btnVotarBranco.setDisable(false);
        btnVotarNulo.setDisable(false);

        atualizarGrafico();
    }

    public  void atualizarGrafico() {
        System.out.println("pegar número de votos atuais do servitor");
        // TODO atualizar candidatos, requisitar do servidor

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<String, Number>(xAxis, yAxis);
        xAxis.setLabel("Candidatos");
        yAxis.setLabel("Número de votos");

        XYChart.Series series = new XYChart.Series();
        for (Candidato candidato : mCandidatos) {
            series.getData().add(new XYChart.Data(candidato.getNome_candidato(), candidato.getNum_votos()));
        }
        barChart.getData().add(series);

    }

    public void votar() {
        for (Candidato candidato : mCandidatos) {
            if (candidato.getRbCandidato().isSelected()) {
                System.out.println("candidato: " + candidato.getNome_candidato() + " codigo: " + candidato.getCodigo_votacao());
                // TODO: enviar codigo votação ao servidor, excluir codigo abaixo
                candidato.incrementNum_votos();
            }
        }
    }


    public void votarEmBranco() {
        System.out.println("Voto para o candidato mais votado");
        int codigoCandidatoMaisVotado = -1;
        int numVotosDoCandidatoMaisVotado = -1;
        for (Candidato candidato : mCandidatos) {
            if (candidato.getNum_votos() > numVotosDoCandidatoMaisVotado) {
                numVotosDoCandidatoMaisVotado = candidato.getNum_votos();
                codigoCandidatoMaisVotado = candidato.getCodigo_votacao();
            }
        }
        if (codigoCandidatoMaisVotado > -1) {
            System.out.println("Enviar ao servidor o código: " + codigoCandidatoMaisVotado);
            // TODO:  enviar ao servidor
        }
    }

    public void votarNulo() {
        System.out.println("Voto nulo");
        // TODO: enviar voto nulo ao servidor
    }

    public void closeWindow() {
        mMain.getPrimaryStage().close();
    }
}
