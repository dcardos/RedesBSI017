package urnaEletronica;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindowController {

    @FXML private Label userFeedback;

    private Main mMain;

    public void setMain(Main main) throws IOException {
        mMain = main;

        userFeedback.setText("Carregando candidatos, aguarde.");
        //ClienteConexao.conecta();

        ArrayList<Candidato> candidatos = new ArrayList<>();
        // TODO: mock data, get this from the server
        Candidato candidato1 = new Candidato(11, "Mario", "PMI", 0);
        Candidato candidato2 = new Candidato(22, "Megaman", "IMM", 0);
        Candidato candidato3 = new Candidato(33, "Donkey", "FMDM", 0);
        candidatos.add(candidato1);
        candidatos.add(candidato2);
        candidatos.add(candidato3);

        userFeedback.setText("Candidatos carregados. Escolha abaixo:");

        final ToggleGroup grupoCandidatos = new ToggleGroup();

        for (Candidato candidato : candidatos) {
            candidato.getRbCandidato().setToggleGroup(grupoCandidatos);
        }



    }

    public void closeWindow() {
        mMain.getPrimaryStage().close();
    }
}
