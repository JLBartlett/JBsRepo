package desktop.notes;

import java.awt.*;
import javax.swing.*;

/**
 * Font view window to select font and size
 * @author John
 */
public class DNFontView extends DNAbstractView {

    private String[] fontNames;
    private JComboBox<String> cboFont;
    private JComboBox<Integer> cboFontSize;

    /**
     * 
     * @param parentRect Rectangle representing parent window location
     * @param fontNames String array of all fonts
     * @param controller controls the communication in the application
     */
    public DNFontView(Rectangle parentRect, String[] fontNames, 
            DNMainController controller) {
        super(parentRect, controller);
        this.fontNames = fontNames;
        layoutComps();
    }
    
    /**
     * Set the selected font in the font combo box
     * @param selFont The name of the selected font
     */
    public void setSelFont(String selFont) {
        cboFont.setSelectedItem(selFont);
    }
    
    /**
     * Set the selected font size in the font size combo box
     * @param fontSize The size of the selected font
     */
    public void setSelFontSize(int fontSize) {
        cboFontSize.setSelectedItem(fontSize);
    }

    /**
     * Get the selected font from the combo box
     * @return The selected font
     */
    public String getStrFont() {
        return (String) cboFont.getSelectedItem();
    }

    /**
     * Get the selected font size from the combo box
     * @return The selected font size
     */
    public int getIntFontSize() {
        return (Integer) cboFontSize.getSelectedItem();
    }

    /**
     * Layout the font view components 
     */
    @Override
    public final void layoutComps() {

        JLabel lblFont = new JLabel("Font:");
        cboFont = new JComboBox<>(fontNames);
        cboFont.setMaximumRowCount(20);

        JLabel lblFontSize = new JLabel("Font Size:");
        Integer[] strFontSize = new Integer[]{10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        cboFontSize = new JComboBox<>(strFontSize);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> controller.fontOK());
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> controller.fontCancel());

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFont)
                        .addComponent(cboFont))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFontSize)
                        .addComponent(cboFontSize))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFont)
                        .addComponent(cboFont))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFontSize)
                        .addComponent(cboFontSize))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOK)
                        .addComponent(btnCancel))
        );
        this.pack();
        this.setBounds(super.calcPos());
    }

}
