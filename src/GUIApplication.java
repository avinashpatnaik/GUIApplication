import javax.swing.SwingUtilities;

public class GUIApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUIUserFrame();
			}
		});
	}

}
