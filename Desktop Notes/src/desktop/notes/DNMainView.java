package desktop.notes;

import java.awt.*;
import javax.swing.*;

/**
 * The main parent view window for the Desktop Notes application
 * @author John
 */
public class DNMainView extends JFrame {

    private String title = "Desktop Notes";
    private final double WIDTH_DIV = 3.5;
    private final int TASKBAR_HEIGHT = 40;
    private final String ICON_FILENAME = "Desktop_Notes_Icon.png";
    private final Color WINDOWS_COLOR = new Color(253, 253, 200);

    private JTextArea txtNotes;

    private DNMainController controller;

    private JButton btnMoveLeft = new JButton("<");
    private JButton btnMoveRight = new JButton(">");

    /**
     * 
     * @param controller controls the communication in the application
     */
    public void setController(DNMainController controller) {
        this.controller = controller;
    }

    /**
     * Construct the main view
     */
    public DNMainView() {
        initComps();
    }

    /**
     * Set the actual desktop notes text
     * @param str String representation of the actual note
     */
    public void setNoteText(String str) {
        txtNotes.setText(str);
    }
    
    /**
     * Get the actual desktop note text
     * @return String representation of the actual note
     */
    public String getNoteText() {
        return txtNotes.getText();
    }

    /**
     * Set the note Font and Font size
     * @param font The selected Font
     * @param fontSize The selected Font size
     */
    public void setNoteFont(Font font, int fontSize) {
        Font newFont = new Font(font.getName(), font.getStyle(), fontSize);
        txtNotes.setFont(newFont);
    }
    
    /**
     * initialize the main windows components
     */
    private void initComps() {
        this.setUndecorated(true);
        this.getContentPane().setBackground(WINDOWS_COLOR);
        this.setIconImage(new ImageIcon(ICON_FILENAME).getImage());
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Insets btnInsets = new Insets(0, 0, 0, 0);

        JPanel pnlTop = new JPanel();
        pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.LINE_AXIS));

        btnMoveLeft = new JButton("<");
        btnMoveLeft.setFont(font);
        btnMoveLeft.setMargin(btnInsets);
        btnMoveLeft.addActionListener(e -> controller.moveLeft());

        btnMoveRight = new JButton(">");
        btnMoveRight.setFont(font);
        btnMoveRight.setMargin(btnInsets);
        btnMoveRight.addActionListener(e -> controller.moveRight());

        JButton btnAddNew = new JButton("+");
        btnAddNew.setFont(font);
        btnAddNew.setMargin(btnInsets);
        btnAddNew.addActionListener(e -> controller.newNote());
        
        JButton btnSave = new JButton("S");
        btnSave.setFont(font);
        btnSave.setMargin(btnInsets);
        btnSave.addActionListener(e -> controller.saveCurrentNote());
        
        JButton btnOpen = new JButton("O");
        btnOpen.setFont(font);
        btnOpen.setMargin(btnInsets);
        btnOpen.addActionListener(e -> controller.openNote());

        JButton btnFont = new JButton("F");
        btnFont.setFont(font);
        btnFont.setMargin(btnInsets);
        btnFont.addActionListener(e -> controller.openFontView());    

        JButton BtnMinimize = new JButton("_");//U+1F5D5
        BtnMinimize.setFont(font);
        BtnMinimize.setMargin(btnInsets);
        BtnMinimize.addActionListener(e -> controller.minimize());

        JButton btnClose = new JButton("X");
        btnClose.setFont(font);
        btnClose.setMargin(btnInsets);
        btnClose.addActionListener(e -> controller.closeApp());

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(font);

        pnlTop.add(btnMoveLeft);
        pnlTop.add(btnMoveRight);
        pnlTop.add(btnAddNew);
        pnlTop.add(btnSave);
        pnlTop.add(btnOpen);
        pnlTop.add(Box.createHorizontalGlue());
        pnlTop.add(lblTitle);
        pnlTop.add(Box.createHorizontalGlue());
        pnlTop.add(btnFont);
        pnlTop.add(BtnMinimize);
        pnlTop.add(btnClose);

        this.setLayout(new BorderLayout());
        this.add(pnlTop, BorderLayout.NORTH);
        
        JPanel pnlBottom = new JPanel(new BorderLayout(10, 10));
        
        txtNotes = new JTextArea();
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setMargin(new Insets(5, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(txtNotes,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        pnlBottom.add(scrollPane, BorderLayout.CENTER);
        this.add(pnlBottom, BorderLayout.CENTER);
        
        
    }

    /**
     * Calculate and locate the position of the main view window
     * @param left whether it is left or right aligned on the screen
     * @return where to put the main window on the screen
     */
    private Rectangle calcLocAndPos(boolean left) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        int winWidth = (int) ((double) screenWidth / WIDTH_DIV);
        int winHeight = screenHeight - TASKBAR_HEIGHT;
        int xPos = (left) ? 0 : screenWidth - winWidth;
        return new Rectangle(xPos, 0, winWidth, winHeight);
    }

    /**
     * Move the main view right
     * @return The position to put the main view
     */
    public Rectangle moveViewLeft() {
        this.btnMoveLeft.setVisible(false);
        this.btnMoveRight.setVisible(true);
        Rectangle rect = calcLocAndPos(true);
        this.setBounds(rect);
        return rect;
    }

    /**
     * Move the main view left
     * @return The position to put the main view
     */
    public Rectangle moveViewRight() {
        this.btnMoveRight.setVisible(false);
        this.btnMoveLeft.setVisible(true);
        Rectangle rect = calcLocAndPos(false);
        this.setBounds(rect);
        return rect;
    }
}
