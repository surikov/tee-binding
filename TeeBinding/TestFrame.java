package tee.swingtest;

import javax.swing.*;
import java.awt.*;
import tee.binding.*;
import javax.swing.event.*;

class BindableSlider extends JSlider {
    int minValue=-50;
    int maxValue=+200;
    private Numeric bindableValue = new Numeric().value(0).afterChange(new Task() {
	@Override public void job() {
	    if (bindableValue != null && bindableValue.value() >= minValue && bindableValue.value() <= maxValue) {
		setValue(bindableValue.value().intValue());
	    }
	}
    });
    public Numeric bindableValue() {
	return bindableValue;
    }
    public BindableSlider() {
	super();
	this.setMinimum(minValue);
	this.setMaximum(maxValue);
	addChangeListener(new ChangeListener() {
	    @Override public void stateChanged(ChangeEvent e) {
		bindableValue.value(getValue());
	    }
	});
    }
}

class BindableSpinner extends JSpinner {
   int minValue=-50;
    int maxValue=+200;
    SpinnerNumberModel model = new SpinnerNumberModel();
    private Numeric bindableValue = new Numeric().value(0).afterChange(new Task() {
	@Override public void job() {
	    if (bindableValue != null && bindableValue.value() >= minValue && bindableValue.value() <= maxValue) {
		model.setValue(bindableValue.value());
	    }
	}
    });
    public Numeric bindableValue() {
	return bindableValue;
    }
    public BindableSpinner() {
	super();
	this.setModel(model);
	model.setMaximum(maxValue);
	model.setMinimum(minValue);
	addChangeListener(new ChangeListener() {
	    @Override public void stateChanged(ChangeEvent e) {
		bindableValue.value(((Number) model.getValue()).intValue());
	    }
	});
    }
}

class BindableLabel extends JLabel {
    private Note bindableValue = new Note().value("").afterChange(new Task() {
	@Override public void job() {
	    if (bindableValue != null) {
		setText(bindableValue.value());
	    }
	}
    });
    public Note bindableValue() {
	return bindableValue;
    }
    public BindableLabel() {
	super();
    }
}

public class TestFrame extends JFrame {
    JLabel fahrenheitLabel = new JLabel("Fahrenheit");
    BindableSlider fahrenheitSlider = new BindableSlider();//-58,+214);
    BindableSpinner fahrenheitSpinner = new BindableSpinner();//-58,+214);
    JPanel fahrenheitPanel = new JPanel(new BorderLayout());
    JLabel celsiusLabel = new JLabel("Celsius");
    BindableSlider celsiusSlider = new BindableSlider();//-50,+101);
    BindableSpinner celsiusSpinner = new BindableSpinner();//-50,+101);
    JPanel celsiusPanel = new JPanel(new BorderLayout());
    BindableLabel descriptionLabel = new BindableLabel();
    JPanel descriptionPanel = new JPanel(new BorderLayout());
    public TestFrame() {
	composeComponents();
	bindComponents();
    }
    void bindComponents() {
	Numeric celsius = new Numeric().value(0);
	Numeric fahrenheit = celsius.multiply(9.0).divide(5.0).plus(32.0);
	Note description = new Note().value("Temperature: ").append((new Fork<String>()
	    .condition(new Toggle().less(celsius, -5))
	    .then("Frost")
	    .otherwise(new Fork<String>()
		.condition(new Toggle().less(celsius, +15))
		.then("Cold")
		.otherwise(new Fork<String>()
		    .condition(new Toggle().less(celsius, +30))
		    .then("Warm")
		    .otherwise("Hot")
		    ))));
	fahrenheitSlider.bindableValue().bind(fahrenheit);
	fahrenheitSpinner.bindableValue().bind(fahrenheit);
	celsiusSlider.bindableValue().bind(celsius);
	celsiusSpinner.bindableValue().bind(celsius);
	descriptionLabel.bindableValue().bind(description);
/*
descriptionLabel.bindableValue().bind(
	Note.iF(n.less(-5))
	.then("Frost")
	.otherwise(
		Note.iF(n.less(+15))
		.then("Cold")
		.otherwise(
			Note.iF(n.less(+30))
			.then("Warm")
			.otherwise("Hot")
			)
		)
	);
*/	    
	    
    }
    void composeComponents() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	this.add(fahrenheitPanel, BorderLayout.NORTH);
	fahrenheitPanel.add(celsiusPanel, BorderLayout.CENTER);
	celsiusPanel.add(descriptionPanel, BorderLayout.CENTER);

	JPanel f = new JPanel(new FlowLayout());
	f.add(fahrenheitLabel);
	f.add(fahrenheitSlider);
	f.add(fahrenheitSpinner);
	fahrenheitSpinner.setPreferredSize(new Dimension(70, 22));
	fahrenheitPanel.add(f, BorderLayout.NORTH);

	JPanel c = new JPanel(new FlowLayout());
	c.add(celsiusLabel);
	c.add(celsiusSlider);
	c.add(celsiusSpinner);
	celsiusSpinner.setPreferredSize(new Dimension(70, 22));
	celsiusPanel.add(c, BorderLayout.NORTH);

	descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
    }
    public static void main(String[] args) {
	TestFrame t = new TestFrame();
	t.setSize(500, 200);
	t.setVisible(true);
    }
}
