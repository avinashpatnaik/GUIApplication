import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JLabel;

public class PausedCellRenderer extends DefaultListCellRenderer{

	public ActionController actionController;

	public PausedCellRenderer(ActionController actionController){
	    this.actionController = actionController;
	}

	public Component getListCellRendererComponent(final JList list, final Object value, final int index,
			final boolean isSelected, final boolean cellHasFocus) {
		final JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		
		c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		if(actionController.getElementAt(index).isPaused()) {
			 c.setForeground(Color.red);
		}
		if(actionController.getElementAt(index).isPaused() == false) {
			 c.setForeground(Color.black);
		}
		if(index == 0) {
			c.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
		}
		if(isSelected) {
			c.setBackground(new Color(220, 220, 220));
		}
		
		return c;
	}
}
