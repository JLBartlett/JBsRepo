package desktop.notes;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 * Abstract View for all child views
 *
 *
 * @author John
 *
 */
public abstract class DNAbstractView extends JDialog {

    private Rectangle parentRectangle;
    protected DNMainController controller;
    private String iconFilename = "Desktop_Notes_Icon.png";
    private Color windowColour = new Color(253, 253, 185);

    /**
     *
     * @param parentRect Rectangle representing position of parent window
     * @param controller controls the flow of communication in the program
     */
    protected DNAbstractView(Rectangle parentRect, DNMainController controller) {
        this.parentRectangle = parentRect;
        this.controller = controller;
        initComponents();
    }

    /**
     * Initialize common attributes among child views
     */
    private void initComponents() {
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setUndecorated(true);
        this.getContentPane().setBackground(windowColour);
        this.setIconImage(new ImageIcon(iconFilename).getImage());
        this.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    /**
     * All child views perform their own layout
     */
    protected abstract void layoutComps();

    /**
     * Calculate the position based on parent window position
     *
     * @return The position for the child view
     */
    protected Rectangle calcPos() {
        int width = this.getWidth();
        int height = this.getHeight();
        int childX = (int) (parentRectangle.getX() + ((parentRectangle.getWidth() - width) / 2));
        int childY = (int) (parentRectangle.getY() + ((parentRectangle.getHeight() - height) / 2));
        return new Rectangle(childX, childY, width, height);
    }

}
