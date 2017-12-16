
package desktop.notes;

import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Open Note View allowing selection of existing notes
 * @author John
 */
public class DNOpenNoteView extends DNAbstractView{
    private String[] fileNames;
    private JComboBox<String> cboFiles;

    /**
     * 
     * @param parentRect Rectangle representing parent window location
     * @param fileNames String array of all file names
     * @param controller controls the communication in the application
     */
    public DNOpenNoteView(Rectangle parentRect, String[] fileNames, DNMainController controller) {
        super(parentRect, controller);
        this.fileNames = fileNames;
        layoutComps();
    }
    
    /**
     * Get the selected file name from the ComboBox
     * @return The selected file name
     */
    public String getSelFile() {
        return cboFiles.getSelectedItem().toString();
    }
    
    /**
     * Set the selected file name for the ComboBox
     * @param selFile The file name to select
     */
    public void setSelFile(String selFile) {
        cboFiles.setSelectedItem(selFile);
    }

    /**
     * layout the open note view components
     */
    @Override
    protected void layoutComps() {
        JLabel lblNewFileName = new JLabel("Select Note:");
        cboFiles = new JComboBox<>(fileNames);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> controller.openNoteOK());
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> controller.openNoteCancel());

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNewFileName)
                        .addComponent(cboFiles))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNewFileName)
                        .addComponent(cboFiles))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOK)
                        .addComponent(btnCancel))
        );
        this.pack();
        this.setBounds(super.calcPos());
    }
    
}
