package tee.binding;

public class TyNumber extends TyValue<Double> {

    //private TyValue<Double> _double = null;
    private TyValue<String> _string = null;
    private TyNumber me = this;

    public TyNumber() {
    }

    public TyNumber(Double i) {
	super(i);
    }

    public TyNumber(Integer i) {
	super(i.doubleValue());
    }

    public TyNumber(TyValue<Double> i) {
	super(i);
    }

    public void set(int newValue) {
	set((double) newValue);
    }
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

    public TyNumber plus(Double value) {
	final Double fvalue = value;
	return new TyNumber(new TyCalculation<Double>(this, new TyValue<Double>(get() + fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().get() - fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() + fvalue;
	    }
	}.second());
    }

    public TyNumber plus(TyNumber value) {
	final TyValue<Double> fvalue = value;
	final TyCalculation<Double> clc = new TyCalculation<Double>(this, new TyValue<Double>(get() + fvalue.get())) {

	    @Override
	    public Double calculateFirst() {
		return second().get() - fvalue.get();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() + fvalue.get();
	    }
	};
	new TyValue<Double>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().set(clc.first().get() + fvalue.get());
	    }
	};
	return new TyNumber(clc.second());
    }

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

    public TyNumber multiply(Double value) {
	final Double fvalue = value;
	return new TyNumber(new TyCalculation<Double>(this, new TyValue<Double>(get() * fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().get() / fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() * fvalue;
	    }
	}.second());
    }

    public TyNumber multiply(TyNumber value) {
	final TyValue<Double> fvalue = value;
	final TyCalculation<Double> clc = new TyCalculation<Double>(this, new TyValue<Double>(get() * fvalue.get())) {

	    @Override
	    public Double calculateFirst() {
		return second().get() / fvalue.get();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() * fvalue.get();
	    }
	};
	new TyValue<Double>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().set(clc.first().get() * fvalue.get());
	    }
	};
	return new TyNumber(clc.second());
    }

    public TyNumber divide(Double value) {
	final Double fvalue = value;
	return new TyNumber(new TyCalculation<Double>(this, new TyValue<Double>(get() / fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().get() * fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() / fvalue;
	    }
	}.second());
    }


    public TyNumber divide(TyNumber value) {
	final TyNumber fvalue = value;
	final TyCalculation<Double> clc = new TyCalculation<Double>(this, new TyValue<Double>(get() / fvalue.get())) {

	    @Override
	    public Double calculateFirst() {
		return second().get() * fvalue.get();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() / fvalue.get();
	    }
	};
	new TyNumber(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().set(clc.first().get() / fvalue.get());
	    }
	};
	return new TyNumber(clc.second());
    }

    public TyNumber minus(Double value) {
	final Double fvalue = value;
	return new TyNumber(new TyCalculation<Double>(this, new TyValue<Double>(get() - fvalue)) {

	    @Override
	    public Double calculateFirst() {
		return second().get() + fvalue;
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() - fvalue;
	    }
	}.second());
    }

    public TyNumber minus(TyNumber value) {
	final TyValue<Double> fvalue = value;
	final TyCalculation<Double> clc = new TyCalculation<Double>(this, new TyValue<Double>(get() - fvalue.get())) {

	    @Override
	    public Double calculateFirst() {
		return second().get() + fvalue.get();
	    }

	    @Override
	    public Double calculateSecond() {
		return first().get() - fvalue.get();
	    }
	};
	new TyValue<Double>(fvalue) {

	    @Override
	    public void afterChange() {
		clc.second().set(clc.first().get() - fvalue.get());
	    }
	};
	return new TyNumber(clc.second());
    }

    public TyValue<String> asString() {
	if (_string == null) {
	    _string = new TyValue<String>("" + get()) {

		@Override
		public void afterChange() {
		    if (_string != null) {
			if (_string.get() == null) {
			    me.set(null);
			} else {
			    try {
				me.set(Double.parseDouble(_string.get()));
			    } catch (Throwable t) {
				//ignore
			    }
			}
		    }
		}
	    };
	    new TyValue<Double>(this) {

		@Override
		public void afterChange() {
		    _string.set("" + get());
		}
	    };
	}
	return _string;
    }
    /*public static TyNumber fromDouble(TyValue<Double> i) {
    TyNumber ii = new TyNumber(i.get().DoubleValue());
    return ii;
    }*/

    public static void main(String a[]) {
	//TyNumber ii = new TyNumber(2.0);
	/*TyValue<Double> dbl = new TyValue<Double>(ii.asDouble());
	TyValue<String> ss = new TyValue<String>(ii.asString());
	System.out.prDoubleln(ii.get() + ", " + dbl.get() + ", " + ss.get());
	dbl.set(444444.0);
	System.out.prDoubleln(ii.get() + ", " + dbl.get() + ", " + ss.get());
	ii.set(2);
	System.out.prDoubleln(ii.get() + ", " + dbl.get() + ", " + ss.get());
	ss.set("33");
	System.out.prDoubleln(ii.get() + ", " + dbl.get() + ", " + ss.get());*/
	/*TyValue<Double> plu = new TyValue<Double>(3.0);
	TyValue<Double> nn = ii.maximum(plu);
	System.out.println(ii.get() + ", " + nn.get() + ", " + plu.get());
	//System.out.prDoubleln(">");
	nn.set(21.0);
	System.out.println(ii.get() + ", " + nn.get() + ", " + plu.get());
	ii.set(122.0);
	System.out.println(ii.get() + ", " + nn.get() + ", " + plu.get());
	plu.set(500.0);
	System.out.println(ii.get() + ", " + nn.get() + ", " + plu.get());*/
	TyNumber tCelsius = new TyNumber(0);
	TyNumber tFahrenheit = new TyNumber(tCelsius).multiply(9.0).divide(5.0).plus(32.0);
	System.out.println("tFahrenheit: " + tFahrenheit.get() + ", tCelsius: " + tCelsius.get());
	tFahrenheit.set(100);
	System.out.println("tFahrenheit: " + tFahrenheit.get() + ", tCelsius: " + tCelsius.get());
	tCelsius.set(100);
	System.out.println("tFahrenheit: " + tFahrenheit.get() + ", tCelsius: " + tCelsius.get());
/*
	TyNumber n1 = new TyNumber(0);
	TyNumber n2 = new TyNumber(0);
	n1.bind(n2.plus(9.0).negativePositive(1.0, 2.0));
	System.out.println("1: " + n1.get() + ", 2: " + n2.get());
	n1.set(100);
	System.out.println("1: " + n1.get() + ", 2: " + n2.get());
	n2.set(100);
	System.out.println("1: " + n1.get() + ", 2: " + n2.get());
*/
    }
}
