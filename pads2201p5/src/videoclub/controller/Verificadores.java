package videoclub.controller;

import java.util.Calendar;

import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;

class NoVacio extends InputVerifier {
	@Override
	public boolean verify(JComponent input) {
		if (input instanceof JTextField) {
			JTextField text = (JTextField) input;
			return (0 == text.getText().length());
		}
		else if (input instanceof JComboBox) {
			JComboBox<?> combo = (JComboBox<?>) input;
			return (-1 == combo.getSelectedIndex());
		}
		return true;
	}
}

class AñoValido extends InputVerifier {
	@Override
	public boolean verify(JComponent input) {
		JSpinner spin = (JSpinner) input;
		int año = Calendar.getInstance().get(Calendar.YEAR);
		return (int)spin.getValue() <= año;
	}
}

