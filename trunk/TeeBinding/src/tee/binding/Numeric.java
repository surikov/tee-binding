package tee.binding;

public class Numeric extends It<Double> {
    private It<String> _string = null;
    private Numeric me = this;
    @Override public Numeric value(Double newValue) {
	super.value(newValue);
	return this;
    }
    public Numeric value(Integer newValue) {
	super.value(newValue.doubleValue());
	return this;
    }
    public Numeric bind(Numeric it) {
	super.bind(it);
	return this;
    }
    @Override public Numeric bind(It<Double> it) {
	super.bind(it);
	return this;
    }
    public Numeric plus(int value) {
	Numeric n = new Numeric().value(value);
	return plus(n);
    }
    public Numeric plus(double value) {
	Numeric n = new Numeric().value(value);
	return plus(n);
    }
    @Override public Numeric afterChange(Task newValue) {
	super.afterChange(newValue);
	return this;
    }
    public Numeric plus(It<Double> value) {
	Numeric n = new Numeric().bind(value);
	return plus(n);
    }
    public Numeric plus(Numeric value) {
	final It<Double> fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() + fvalue.value())) {
	    @Override public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return second().value() - fvalue.value();
	    }
	    @Override public Double calculateSecond() {
		if (first() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return first().value() + fvalue.value();
	    }
	};
	new It<Double>().bind(fvalue).afterChange(new Task() {
	    @Override public void job() {
		clc.second().value(clc.first().value() + fvalue.value());
	    }
	});
	return new Numeric().bind(clc.second());
    }
    public Numeric multiply(double value) {
	Numeric n = new Numeric().value(value);
	return multiply(n);
    }
    public Numeric multiply(int value) {
	Numeric n = new Numeric().value(value);
	return multiply(n);
    }
    public Numeric multiply(It<Double> value) {
	Numeric n = new Numeric().bind(value);
	return multiply(n);
    }
    public Numeric multiply(Numeric value) {
	final It<Double> fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() * fvalue.value())) {
	    @Override public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		if (fvalue.value() == 0) {
		    return 0.0;
		}
		return second().value() / fvalue.value();
	    }
	    @Override public Double calculateSecond() {
		if (first() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return first().value() * fvalue.value();
	    }
	};
	new It<Double>().bind(fvalue).afterChange(new Task() {
	    @Override public void job() {
		clc.second().value(clc.first().value() * fvalue.value());
	    }
	});
	return new Numeric().bind(clc.second());
    }
    public Numeric divide(double value) {
	Numeric n = new Numeric().value(value);
	return divide(n);
    }
    public Numeric divide(int value) {
	Numeric n = new Numeric().value(value);
	return divide(n);
    }
    public Numeric divide(It<Double> value) {
	Numeric n = new Numeric().bind(value);
	return divide(n);
    }
    public Numeric divide(Numeric value) {
	final Numeric fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() / fvalue.value())) {
	    @Override public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return second().value() * fvalue.value();
	    }
	    @Override public Double calculateSecond() {
		if (first() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		if (fvalue.value() == 0) {
		    return 0.0;
		}
		return first().value() / fvalue.value();
	    }
	};
	new Numeric().afterChange(new Task() {
	    @Override public void job() {
		clc.second().value(clc.first().value() / fvalue.value());
	    }
	}).bind(fvalue);
	return new Numeric().bind(clc.second());
    }
    public Numeric minus(double value) {
	Numeric n = new Numeric().value(value);
	return minus(n);
    }
    public Numeric minus(int value) {
	Numeric n = new Numeric().value(value);
	return minus(n);
    }
    public Numeric minus(It<Double> value) {
	Numeric n = new Numeric().bind(value);
	return minus(n);
    }
    public Numeric minus(Numeric value) {
	final It<Double> fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() - fvalue.value())) {
	    @Override public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return second().value() + fvalue.value();
	    }
	    @Override public Double calculateSecond() {
		if (first() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return first().value() - fvalue.value();
	    }
	};
	new It<Double>().bind(fvalue).afterChange(new Task() {
	    @Override public void job() {
		clc.second().value(clc.first().value() - fvalue.value());
	    }
	});
	return new Numeric().bind(clc.second());
    }
    public It<String> asString() {
	if (_string == null) {
	    _string = new It<String>().afterChange(new Task() {
		@Override public void job() {
		    if (_string != null) {
			if (_string.value() == null) {
			    me.value(0);
			} else {
			    try {
				me.value(Double.parseDouble(_string.value()));
			    } catch (Throwable t) {
				//ignore
			    }
			}
		    }
		}
	    }).value("" + value());
	    new It<Double>().bind(this).afterChange(new Task() {
		@Override public void job() {
		    _string.value("" + value());
		}
	    });
	}
	return _string;
    }
    public static void main(String a[]) {
	
	System.out.println("\nNumeric\n");
	Numeric tCelsius = new Numeric().value(0);
	Numeric tFahrenheit = tCelsius.multiply(9.0).divide(5.0).plus(32.0);
	System.out.println("/tFahrenheit is tCelsius * 9 / 5 + 32");
	System.out.println("tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
	System.out.println("/let tFahrenheit = 100 ");
	tFahrenheit.value(100);
	System.out.println("tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
	tCelsius.value(100);
	System.out.println("/let tCelsius = 100 ");
	System.out.println("tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
    }
}
