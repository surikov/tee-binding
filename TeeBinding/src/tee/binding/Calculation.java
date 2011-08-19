package tee.binding;

public class Calculation<Kind> {

    private boolean lockFirst = false;
    private boolean lockSecond = false;
    private It<Kind> _first = new It<Kind>() {

	@Override
	public void afterChange() {
	    if (lockFirst) {
		return;
	    }
	    lockFirst = true;
	    if (!lockSecond) {
		second().value(calculateSecond());
	    }
	    lockFirst = false;
	}
    };
    private It<Kind> _second = new It<Kind>() {

	@Override
	public void afterChange() {
	    if (lockSecond) {
		return;
	    }
	    lockSecond = true;
	    if (!lockFirst) {
		first().value(calculateFirst());
	    }
	    lockSecond = false;
	}
    };

    public Calculation(It<Kind> f, It<Kind> s) {
	first().tie(f);
	second().tie(s);
    }

    public Kind calculateFirst() {
	return second().value();
    }

    public Kind calculateSecond() {
	return first().value();
    }

    public It<Kind> first() {
	return _first;
    }

    public It<Kind> second() {
	return _second;
    }
    
    public static void main(String[] args) {
	final It<Double> tFahrenheit = new It<Double>().value(0.0);
	final It<Double> tCelsius = new It<Double>().value(0.0);
	new Calculation(tFahrenheit, tCelsius) {

	    @Override
	    public Double calculateFirst() {
		return tCelsius.value() * 9.0 / 5.0 + 32.0;
	    }

	    @Override
	    public Double calculateSecond() {
		return (tFahrenheit.value() - 32) * 5.0 / 9.0;
	    }
	};
	System.out.println("let tFahrenheit=100");
	tFahrenheit.value(100.0);
	System.out.println("now tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
	System.out.println("let tCelsius=100");
	tCelsius.value(100.0);
	System.out.println("now tFahrenheit: " + tFahrenheit.value() + ", tCelsius: " + tCelsius.value());
    }
}
