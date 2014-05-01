package videoclub.view.MiJClass;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MiJPanel extends JPanel {

	private Image imagen;

	public MiJPanel() {
		this.setOpaque(false);
	}
	
	public MiJPanel(LayoutManager layout) {
		super(layout);
		this.setOpaque(false);
	}

	public MiJPanel(String imagepath) {
		if (imagepath != null) {
			imagen = new ImageIcon(imagepath).getImage();
		}
		this.setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		if (imagen != null) {
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
		}
		super.paint(g);
	}
	
	public <T extends Component> T añadir(T componente) {
		super.add(componente);
		return componente;
	}
}