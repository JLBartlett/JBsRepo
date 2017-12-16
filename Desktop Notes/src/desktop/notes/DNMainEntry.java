package desktop.notes;

/**
 * Application main entry point
 * @author John
 */
public class DNMainEntry {

    /**
     * Create model, main view and controller classes and open application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DNMainModel model = new DNMainModel();
        DNMainView view = new DNMainView();
        DNMainController controller = new DNMainController(model, view);
        view.setController(controller);
        controller.openApp();
    }
}
