package videoclub.view.MiJClass;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class JSpinnerOnlyNumbers extends JSpinner {
	
	public JSpinnerOnlyNumbers(){
		this(0.0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.MIN_VALUE);
	}
	
	public JSpinnerOnlyNumbers(double value, double minimum, double maximum, double stepSize) {
		super(new SpinnerNumberModel(value, minimum, maximum, stepSize));
		JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)this.getEditor();
		DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false);
	}

	
	public JSpinnerOnlyNumbers(int value, int minimum, int maximum, int stepSize) {
		super(new SpinnerNumberModel(value, minimum, maximum, stepSize));
		JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)this.getEditor();
		DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false);
	}
	
}
