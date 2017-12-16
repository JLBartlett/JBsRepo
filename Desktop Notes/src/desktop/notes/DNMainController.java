package desktop.notes;

import javax.swing.*;
import java.awt.*;

/**
 * The main controller for the whole program following the MVC architectural
 * pattern.
 *
 * @author John
 */
public class DNMainController {

    private final DNMainModel model;
    private final DNMainView mainView;
    private Rectangle viewPos;
    private DNFontView fontView;
    private DNNewNoteView newNoteView;
    private DNOpenNoteView openNoteView;

    /**
     *
     * @param model The model looks after the application data
     * @param view The main view of the application
     */
    public DNMainController(DNMainModel model, DNMainView view) {
        this.model = model;
        this.mainView = view;
        this.mainView.setNoteText(model.getDesktopNote());
        this.mainView.setNoteFont(model.getFont(), model.getFontSize());
        this.mainView.setVisible(true);
        this.moveRight();
    }

    /**
     * Shows the main application
     */
    public void openApp() {
        this.mainView.setVisible(true);
    }

    /**
     * Close the main application
     */
    public void closeApp() {
        System.exit(0);
    }

    /**
     * Move the main view left
     */
    public void moveLeft() {
        this.viewPos = this.mainView.moveViewLeft();
    }

    /**
     * Move the main view right
     *
     */
    public void moveRight() {
        this.viewPos = this.mainView.moveViewRight();
    }

    /**
     * minimize the main view
     */
    public void minimize() {
        this.mainView.setState(JFrame.ICONIFIED);
    }

    /*
     * --------------- SAVE CURRENT NOTE OPERATION ---------------
     */
    public void saveCurrentNote() {
        int okCancel = JOptionPane.showConfirmDialog(mainView,
                "Are you sure you want to save the current note?",
                "Save Current Note",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (okCancel == JOptionPane.OK_OPTION) {
            model.saveNote(mainView.getNoteText());
        }
    }

    /*
     * --------------- OPEN A NOTE OPERATIONS ---------------
     */
    public void openNote() {
        openNoteView = new DNOpenNoteView(viewPos, model.getFileNames(), this);
        openNoteView.setSelFile(model.getCurrentFilename());
        openNoteView.setVisible(true);
    }

    public void openNoteOK() {
        mainView.setNoteText(model.getSelNoteFromMap(openNoteView.getSelFile()).toString());
        openNoteView.setVisible(false);
    }

    public void openNoteCancel() {
        openNoteView.setVisible(false);
    }

    /*
     * --------------- CREATE NEW NOTE OPERATIONS ---------------
     */
    public void newNote() {
        newNoteView = new DNNewNoteView(viewPos, this);
        newNoteView.setVisible(true);
    }

    public void newNoteOK(String userEntry) {
        model.createNewNote(userEntry);
        mainView.setNoteText(model.getDesktopNote());
        newNoteView.setVisible(false);
    }

    public void newNoteCancel() {
        newNoteView.setVisible(false);
    }

    /*
     * --------------- FONT VIEW OPERATIONS ---------------
     */
    public void openFontView() {
        fontView = new DNFontView(viewPos, model.getFontNames(), this);
        fontView.setSelFont(model.getSelFont());
        fontView.setSelFontSize(model.getFontSize());
        fontView.setVisible(true);
    }

    public void fontOK() {
        model.setFontSize(fontView.getIntFontSize());
        model.setSelFont(fontView.getStrFont());
        this.mainView.setNoteFont(model.getFont(), model.getFontSize());
        fontView.setVisible(false);

    }

    public void fontCancel() {
        fontView.setVisible(false);
    }
}
