package desktop.notes;

import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * New Note View allowing entry of a new file name
 * @author John
 */
public class DNNewNoteView extends DNAbstractView {

    /**
     * 
     * @param parentRect Rectangle representing parent window location
     * @param controller controls the communication in the application
     */
    public DNNewNoteView(Rectangle parentRect, DNMainController controller) {
        super(parentRect, controller);
        layoutComps();
    }

    /**
     * layout the new note view components
     */
    @Override
    protected void layoutComps() {

        JLabel lblNewFileName = new JLabel("New Filename:");
        JTextField txtFileName = new JTextField(15);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> controller.newNoteOK(txtFileName.getText()));
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> controller.newNoteCancel());

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNewFileName)
                        .addComponent(txtFileName))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNewFileName)
                        .addComponent(txtFileName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOK)
                        .addComponent(btnCancel))
        );
        this.pack();
        this.setBounds(super.calcPos());
    }

}
