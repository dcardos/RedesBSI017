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
import javafx.util.StringConverter;

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
    @FXML private Button btnCarregarCandidatos;
    @FXML private Button btnEncerrar;

    private Main mMain;
    private ArrayList<Candidato> mCandidatos;
    private ToggleGroup mGrupoCandidatos;
    private ClienteConexao mClienteConexao;

    public MainWindowController() {
        mCandidatos = new ArrayList<>();
        mClienteConexao = new ClienteConexao();
        mGrupoCandidatos = new ToggleGroup();
    }

    public void setMain(Main main) throws IOException {
        mMain = main;

        btnVotar.setDisable(true);
        btnVotarBranco.setDisable(true);
        btnVotarNulo.setDisable(true);
        btnAtualizar.setDisable(true);

        userFeedback.setText("Por favor, primeiramente carregue os candidatos.");

//        atualizarGrafico();
    }

    public void adicionaCandidatos() throws Exception {
        btnCarregarCandidatos.setDisable(true);

        String dados = mClienteConexao.conexaoRecebeCandidatos();
//        String dados = "1,Joao,AB,0;2,Maria,CD,0;3,Carlos,EF,0;4,Suzana,GH,0;5,Sofia,IJ,0;!";
        System.out.println("Fazer parser de " + dados);
        parser(dados);

        Double i = 0.0;
        for (Candidato candidato : mCandidatos) {
            candidato.getRbCandidato().setToggleGroup(mGrupoCandidatos);
            mainPane.getChildren().add(candidato.getRbCandidato());
            AnchorPane.setTopAnchor(candidato.getRbCandidato(), 130 + 30*i);
            AnchorPane.setLeftAnchor(candidato.getRbCandidato(), 46.0);
            i++;
        }

        userFeedback.setText("Candidatos carregados. Escolha abaixo:");

        btnVotar.setDisable(false);
        btnVotarBranco.setDisable(false);
        btnVotarNulo.setDisable(false);
        btnAtualizar.setDisable(false);
    }

    public void parser(String cadeia) throws Exception {
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
            int codigoVotacao = Integer.parseInt(parts[4*i]);
            String nome = parts[1+4*i];
            String partido = parts[2+4*i];
            int numVotos = Integer.parseInt(parts[3+4*i]);
            mCandidatos.add(new Candidato(codigoVotacao, nome, partido, numVotos));
            if (parts[4+4*i].equals("!"))
                end = true;
            i++;
        }
    }

    public void computaVotos(String cadeia) throws Exception {
        if (cadeia == null)
            throw new Exception("Cadeia vazia!!");
        else if (!cadeia.contains(","))
            throw new Exception("Cadeia fora do padrão, sem vírgulas!");
        else if (!cadeia.contains(";"))
            throw new Exception("Cadeia fora do padrão, sem ponto e vírgulas!");
        else if (!cadeia.contains("!"))
            throw new Exception("Cadeia fora do padrão, sem exclamação!");
        String[] parts = cadeia.split(",|;");
        int i = 0;
        boolean end = false;
        while(!end){
            int codigoVotacao = Integer.parseInt(parts[2*i]);
            int numVotos = Integer.parseInt(parts[1+2*i]);
            for(Candidato candidato : mCandidatos){
                if (candidato.getCodigo_votacao() == codigoVotacao) {
                    candidato.setNum_votos(numVotos);
                }
            }
            if (parts[2+2*i].equals("!"))
                end = true;
            i++;
        }
    }

    public void zerarUrna() {
        for (Candidato candidato : mCandidatos) {
            candidato.setNum_VotosUrna(0);
        }
    }

    public void atualizarGrafico() throws Exception {
        // enviando votos atuais para o servidor
        String votosAtualizados = mClienteConexao.conexaoEnviaRecebeVotos(mCandidatos);
        computaVotos(votosAtualizados);
        // zerando votos nesta urna (Ja enviados)
        zerarUrna();
        btnEncerrar.setDisable(false);

        barChart.getData().clear();
        barChart.setLegendVisible(false);

        final CategoryAxis xAxis = (CategoryAxis)barChart.getXAxis();
        final NumberAxis yAxis = (NumberAxis)barChart.getYAxis();
        xAxis.setLabel("Candidatos");
        yAxis.setLabel("Número de votos");
        IntegerStringConverter integerStringConverter = new IntegerStringConverter();
        yAxis.setTickLabelFormatter(integerStringConverter);
        XYChart.Series serie = new XYChart.Series();

        if (mCandidatos != null) {
            ArrayList<XYChart.Data<String, Number>> dataCandidatos = new ArrayList<>();
            for (Candidato candidato : mCandidatos) {
                XYChart.Data<String, Number> data = new XYChart.Data(candidato.getNome_candidato(), candidato.getNum_votos());
                dataCandidatos.add(data);
                serie.getData().add(data);
            }
            barChart.getData().add(serie);

            int i = 1;
            for (XYChart.Data<String, Number> data : dataCandidatos){
                data.getNode().setStyle("-fx-bar-fill: CHART_COLOR_"+i+";");
                i++;
            }
        }
    }

    public void votar() {
        for (Candidato candidato : mCandidatos) {
            if (candidato.getRbCandidato().isSelected()) {
                System.out.println("candidato: " + candidato.getNome_candidato() + " codigo: " + candidato.getCodigo_votacao());
                candidato.incrementNum_votos();
                btnEncerrar.setDisable(true);
            }
        }
    }


    public void votarEmBranco() throws Exception{
        atualizarGrafico();
        System.out.println("Voto para o candidato mais votado");
        int numVotosDoCandidatoMaisVotado = -1;
        Candidato candidatoMaisVotado = null;
        for (Candidato candidato : mCandidatos) {
            if (candidato.getNum_votos() > numVotosDoCandidatoMaisVotado) {
                numVotosDoCandidatoMaisVotado = candidato.getNum_votos();
                candidatoMaisVotado = candidato;
            }
        }
        if (candidatoMaisVotado != null) {
            System.out.println("Computando um voto para: " + candidatoMaisVotado.getNome_candidato());
            candidatoMaisVotado.incrementNum_votos();
            btnEncerrar.setDisable(true);
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

class IntegerStringConverter extends StringConverter<Number> {

    public IntegerStringConverter() {
    }

    @Override
    public String toString(Number object) {
        if(object.intValue()!=object.doubleValue())
            return "";
        return ""+(object.intValue());
    }

    @Override
    public Number fromString(String string) {
        Number val = Double.parseDouble(string);
        return val.intValue();
    }
}
