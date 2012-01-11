package tee.binding.it;

import tee.binding.it.Toggle;
import tee.binding.it.Note;
import tee.binding.it.It;
import java.text.*;
import java.util.*;
import tee.binding.Calculation;

import tee.binding.task.Task;

public class Numeric extends It<Double> {
    private It<String> _string = null;
    //private Numeric me = this;
    private Numeric _otherwise = null;
    @Override protected void adjust() {
	if (Double.isNaN(this._value)) {
	    this._value = 0.0;
	}
	if (Double.isInfinite(this._value)) {
	    this._value = Double.MAX_VALUE;
	}
    }
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
    @Override
    public Numeric bind(It<Double> it) {
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
    @Override
    public Numeric afterChange(Task newValue) {
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
	    @Override
	    public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return second().value() - fvalue.value();
	    }
	    @Override
	    public Double calculateSecond() {
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
	    @Override
	    public void doTask() {
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
	    @Override
	    public Double calculateFirst() {
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
	    @Override
	    public Double calculateSecond() {
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
	    @Override
	    public void doTask() {
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
	    @Override
	    public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return second().value() * fvalue.value();
	    }
	    @Override
	    public Double calculateSecond() {
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
	    @Override
	    public void doTask() {
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
	    @Override
	    public Double calculateFirst() {
		if (second() == null) {
		    return 0.0;
		}
		if (fvalue == null) {
		    return 0.0;
		}
		return second().value() + fvalue.value();
	    }
	    @Override
	    public Double calculateSecond() {
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
	    @Override
	    public void doTask() {
		clc.second().value(clc.first().value() - fvalue.value());
	    }
	});
	return new Numeric().bind(clc.second());
    }
    public Toggle same(final Numeric bb) {
	final Numeric me = this;
	final Toggle retvalue = new Toggle().value(me.value().equals(bb.value()));
	new Numeric().bind(me).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(me.value().equals(bb.value()));
	    }
	});
	new Numeric().bind(bb).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(me.value().equals(bb.value()));
	    }
	});
	return retvalue;
    }
    public Toggle less(final Numeric bb) {
	//return new Toggle().less(this, it);
	final Numeric aa = this;
	//final Numeric bb = b;
	final Toggle retvalue = new Toggle().value(aa.value() < bb.value());
	new Numeric().bind(aa).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() < bb.value());
	    }
	});
	new Numeric().bind(bb).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() < bb.value());
	    }
	});
	return retvalue;
    }
    public Toggle more(final Numeric bb) {
	//return new Toggle().more(this, it);
	final Numeric aa = this;
	//final Numeric bb = b;
	final Toggle retvalue = new Toggle().value(aa.value() > bb.value());
	new Numeric().bind(aa).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() > bb.value());
	    }
	});
	new Numeric().bind(bb).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() > bb.value());
	    }
	});
	return retvalue;
    }
    public Toggle moreOrEquals(final Numeric bb) {
	//return new Toggle().moreOrEquals(this, it);
	final Numeric aa = this;
	//final Numeric bb = b;
	final Toggle retvalue = new Toggle().value(aa.value() >= bb.value());
	new Numeric().bind(aa).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() >= bb.value());
	    }
	});
	new Numeric().bind(bb).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() >= bb.value());
	    }
	});
	return retvalue;
    }
    public Toggle lessOrEquals(final Numeric bb) {
	//return new Toggle().lessOrEquals(this, it);
	final Numeric aa = this;
	//final Numeric bb = b;
	final Toggle retvalue = new Toggle().value(aa.value() <= bb.value());
	new Numeric().bind(aa).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() <= bb.value());
	    }
	});
	new Numeric().bind(bb).afterChange(new Task() {
	    @Override public void doTask() {
		retvalue.value(aa.value() <= bb.value());
	    }
	});
	return retvalue;
    }
    public Toggle equals(double it) {
	return same(new Numeric().value(it));
    }
    public Toggle less(double it) {
	//return new Toggle().less(this, it);
	return less(new Numeric().value(it));
    }
    public Toggle more(double it) {
	return more( new Numeric().value(it));
    }
    public Toggle moreOrEquals(double it) {
	return moreOrEquals(new Numeric().value( it));
    }
    public Toggle lessOrEquals(double it) {
	return lessOrEquals(new Numeric().value( it));
    }
    public Toggle same(int it) {
	//return new Toggle().equals(this, it);
	return same(new Numeric().value(it));
    }
    public Toggle less(int it) {
	//return new Toggle().less(this, it);
	return less(new Numeric().value(it));
    }
    public Toggle more(int it) {
	//return new Toggle().more(this, it);
	return more( new Numeric().value(it));
    }
    public Toggle moreOrEquals(int it) {
	//return new Toggle().moreOrEquals(this, it);
	return moreOrEquals(new Numeric().value( it));
    }
    public Toggle lessOrEquals(int it) {
	//return new Toggle().lessOrEquals(this, it);
	return lessOrEquals(new Numeric().value( it));
    }
    public static double string2double(String s) {
	double dd = 0;
	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
	try {
	    dd = numberFormat.parse(s.replaceAll(",", "\\.")).doubleValue();
	}
	catch (Throwable t) {
	    t.printStackTrace();
	}
	return dd;
    }
    public Note asNote() {
	return new Note().bind(asString());
    }
    public It<String> asString() {
	if (_string == null) {
	    final Numeric me = this;
	    _string = new It<String>().afterChange(new Task() {
		@Override
		public void doTask() {
		    if (_string != null) {
			if (_string.value() == null || _string.value().length() < 1) {
			    me.value(0);
			}
			else {
			    try {
				me.value(string2double(_string.value()));
			    }
			    catch (Throwable t) {
				//t.printStackTrace();
				System.out.println("can't parse numeric " + _string.value());
			    }
			}
		    }
		}
	    }).value("" + value());
	    new It<Double>().bind(this).afterChange(new Task() {
		@Override
		public void doTask() {
		    _string.value("" + value());
		}
	    });
	}
	return _string;
    }
    /*
    public static Fork<Double> iF(Toggle it) {
    return new Fork<Double>().condition(it);
    }*/
    public Numeric when(final Toggle it) {
	final Numeric me = this;
	final Numeric when = new Numeric();
	new Toggle().bind(it).afterChange(new Task() {
	    @Override
	    public void doTask() {
		//retvalue.value(me.value() + appendNote.value());
		if (it.value()) {
		    when.unbind(when._otherwise);
		    when.bind(me);
		}
		else {
		    if (when._otherwise == null) {
			when._otherwise = new Numeric();
		    }
		    when.unbind(me);
		    when.bind(when._otherwise);
		}
	    }
	});
	return when;
    }
    public Numeric otherwise(Numeric it) {
	if (_otherwise == null) {
	    _otherwise = new Numeric();
	}
	_otherwise.bind(it);
	return this;
    }
    public Numeric otherwise(double it) {
	if (_otherwise == null) {
	    _otherwise = new Numeric();
	}
	_otherwise.value(it);
	return this;
    }
    public Numeric otherwise(int it) {
	if (_otherwise == null) {
	    _otherwise = new Numeric();
	}
	_otherwise.value(it);
	return this;
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
	System.out.println("/condition example");
	Numeric temperature = new Numeric().value(-10);

	Note frost = new Note().value("Frost");
	Note cold = new Note().value("Cold");
	Note warm = new Note().value("Warm");
	Note hot = new Note().value("Hot");

	/*
	Note description = new Note().bind(Note.iF(temperature.less(-5))
	.then(frost)
	.otherwise(Note.iF(temperature.less(+15))
	.then(cold)
	.otherwise(Note.iF(temperature.less(+30))
	.then(warm)
	.otherwise(hot))));
	 */

	Note description = frost.when(temperature.less(-5))
		.otherwise(cold.when(temperature.less(+15))
		    .otherwise(warm.when(temperature.less(+30))
			.otherwise(hot)));


	System.out.println("/n = -10");
	System.out.println(description.value());
	System.out.println("/let n = +10");
	temperature.value(10);
	System.out.println(description.value());
	System.out.println("/let n = +40");
	temperature.value(40);
	System.out.println(description.value());
	System.out.println("/let n = +20");
	temperature.value(20);
	System.out.println(description.value());


    }
}
