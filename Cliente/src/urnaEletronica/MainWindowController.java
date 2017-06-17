package urnaEletronica;

public class MainWindowController {

    private Main mMain;

    public void setMain(Main main) {
        mMain = main;
    }

    public void closeWindow() {
        mMain.getPrimaryStage().close();
    }
}
