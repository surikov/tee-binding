package tee.binding;

public class Numeric extends It<Double> {

    //private TyValue<Double> _double = null;
    private It<String> _string = null;
    private Numeric me = this;
    /*
    public TyNumber() {
    }
    
    public TyNumber(Double i) {
    super(i);
    }
    
    public TyNumber(Integer i) {
    super(i.doubleValue());
    }
    
    public TyNumber(It<Double> i) {
    super(i);
    }
     */

    @Override
    public Numeric value(Double newValue) {
	super.value(newValue);
	return this;
    }
 public Numeric value(Integer newValue) {
	super.value(newValue.doubleValue());
	return this;
    }
    public Numeric tie(Numeric it) {
	super.tie(it);
	return this;
    }

    @Override
    public Numeric tie(It<Double> it) {
	super.tie(it);
	return this;
    }
    /*
    public void set(int newValue) {
    value((double) newValue);
    }*/
    /*public TyValue<Double> asDouble() {
    if (_double == null) {
    _double = new TyValue<Double>((double) get()) {
    
    @Override
    public void afterChange(Double newValue) {
    if (_double != null) {
    if (_double.get() == null) {
    me.set(null);
    } else {
    me.set(_double.get().DoubleValue());
    }
    }
    }
    };
    new TyValue<Double>(this) {
    
    @Override
    public void afterChange(Double newValue) {
    _double.set((double) newValue);
    }
    };
    }
    return _double;
    }*/

    public Numeric plus(Integer value) {
	Double d = new Double(value);
	return plus(d);
    }

    public Numeric plus(Double value) {
	final Double fvalue = value;
	return new Numeric().tie(new Calculation<Double>(this, new Numeric().value(value() + fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().value() - fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() + fvalue;
	    }
	}.second());
    }

    public Numeric plus(Numeric value) {
	final It<Double> fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() + fvalue.value())) {

	    @Override
	    public Double calculateFirst() {
		return second().value() - fvalue.value();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() + fvalue.value();
	    }
	};
	new It<Double>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().value(clc.first().value() + fvalue.value());
	    }
	};
	return new Numeric().tie(clc.second());
    }
    /*
    public TyNumber maximum(Double value) {
    final Double fvalue = value;
    Double n = get();
    if (n > fvalue) {
    n = fvalue;
    }
    TyValue<Double> m = new TyValue<Double>(n) {
    
    @Override
    public void afterChange() {
    if (get() > fvalue) {
    set(fvalue);
    }
    }
    };
    TyCalculation<Double> clc = new TyCalculation<Double>(this, m) {
    
    @Override
    public Double calculateFirst() {
    return get();
    }
    
    @Override
    public Double calculateSecond() {
    Double n = get();
    if (n > fvalue) {
    n = fvalue;
    }
    return n;
    }
    };
    return new TyNumber(clc.second());
    }
    
    public TyNumber maximum(TyNumber value) {
    final TyValue<Double> fvalue = value;
    Double n = get();
    if (n > fvalue.get()) {
    n = fvalue.get();
    }
    TyValue<Double> m = new TyValue<Double>(n) {
    
    @Override
    public void afterChange() {
    if (get() > fvalue.get()) {
    set(fvalue.get());
    }
    }
    };
    new TyValue<Double>(fvalue) {
    
    @Override
    public void afterChange() {
    if (me.get() > get()) {
    me.set(get());
    }
    }
    };
    final TyCalculation<Double> clc = new TyCalculation<Double>(this, m) {
    
    @Override
    public Double calculateFirst() {
    return get();
    }
    
    @Override
    public Double calculateSecond() {
    Double n = get();
    if (n > fvalue.get()) {
    n = fvalue.get();
    }
    return n;
    }
    };
    return new TyNumber(clc.second());
    }
     */

    /*
    public TyNumber minimum(Double value) {
    final Double fvalue = value;
    Double n = get();
    if (n < fvalue) {
    n = fvalue;
    }
    TyValue<Double> m = new TyValue<Double>(n) {
    
    @Override
    public void afterChange() {
    if (get() < fvalue) {
    set(fvalue);
    }
    }
    };
    TyCalculation<Double> clc = new TyCalculation<Double>(this, m) {
    
    @Override
    public Double calculateFirst() {
    return get();
    }
    
    @Override
    public Double calculateSecond() {
    Double n = get();
    if (n < fvalue) {
    n = fvalue;
    }
    return n;
    }
    };
    return new TyNumber(clc.second());
    }
    
    public TyNumber minimum(TyNumber value) {
    final TyValue<Double> fvalue = value;
    Double n = get();
    if (n < fvalue.get()) {
    n = fvalue.get();
    }
    TyValue<Double> m = new TyValue<Double>(n) {
    
    @Override
    public void afterChange() {
    if (get() < fvalue.get()) {
    set(fvalue.get());
    }
    }
    };
    new TyValue<Double>(fvalue) {
    
    @Override
    public void afterChange() {
    if (me.get() < get()) {
    me.set(get());
    }
    }
    };
    final TyCalculation<Double> clc = new TyCalculation<Double>(this, m) {
    
    @Override
    public Double calculateFirst() {
    return get();
    }
    
    @Override
    public Double calculateSecond() {
    Double n = get();
    if (n < fvalue.get()) {
    n = fvalue.get();
    }
    return n;
    }
    };
    return new TyNumber(clc.second());
    }
     */
    public Numeric multiply(Double value) {
	final Double fvalue = value;
	return new Numeric().tie(new Calculation<Double>(this, new Numeric().value(value() * fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().value() / fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() * fvalue;
	    }
	}.second());
    }

    public Numeric multiply(Numeric value) {
	final It<Double> fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() * fvalue.value())) {

	    @Override
	    public Double calculateFirst() {
		return second().value() / fvalue.value();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() * fvalue.value();
	    }
	};
	new It<Double>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().value(clc.first().value() * fvalue.value());
	    }
	};
	return new Numeric().tie(clc.second());
    }

    public Numeric divide(Double value) {
	final Double fvalue = value;
	return new Numeric().tie(new Calculation<Double>(this, new Numeric().value(value() / fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().value() * fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() / fvalue;
	    }
	}.second());
    }

    public Numeric divide(Numeric value) {
	final Numeric fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() / fvalue.value())) {

	    @Override
	    public Double calculateFirst() {
		return second().value() * fvalue.value();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() / fvalue.value();
	    }
	};
	new Numeric() {

	    @Override
	    public void afterChange() {
		clc.second().value(clc.first().value() / fvalue.value());
	    }
	}.tie(fvalue);
	return new Numeric().tie(clc.second());
    }

    public Numeric minus(Double value) {
	final Double fvalue = value;
	return new Numeric().tie(new Calculation<Double>(this, new Numeric().value(value() - fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().value() + fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() - fvalue;
	    }
	}.second());
    }

    public Numeric minus(Numeric value) {
	final It<Double> fvalue = value;
	final Calculation<Double> clc = new Calculation<Double>(this, new Numeric().value(value() - fvalue.value())) {

	    @Override
	    public Double calculateFirst() {
		return second().value() + fvalue.value();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().value() - fvalue.value();
	    }
	};
	new It<Double>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().value(clc.first().value() - fvalue.value());
	    }
	};
	return new Numeric().tie(clc.second());
    }

    public It<String> asString() {
	if (_string == null) {
	    _string = new It<String>() {

		@Override
		public void afterChange() {
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
	    }.value("" + value());
	    new It<Double>(this) {

		@Override
		public void afterChange() {
		    _string.value("" + value());
		}
	    };
	}
	return _string;
    }

    public static void main(String a[]) {
	Numeric tCelsius = new Numeric().value(0);
	Numeric tFahrenheit = new Numeric().tie(tCelsius.multiply(9.0).divide(5.0).plus(32.0));
	System.out.println("tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
	tFahrenheit.value(100);
	System.out.println("tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
	tCelsius.value(100);
	System.out.println("tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());

	Numeric n1 = new Numeric().value(100);
	Numeric n2 = new Numeric().tie(new Numeric().value(1).plus(n1).plus(1000));
	System.out.println(n2.value());
	n1.value(200);
	System.out.println(n2.value());
	n1.value(300);
	System.out.println(n2.value());
    }
}
