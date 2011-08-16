package tee.binding;

public class TyCalculation<Kind> {

    private boolean lockFirst = false;
    private boolean lockSecond = false;
    private TyValue<Kind> _first = new TyValue<Kind>() {

	@Override
	public void afterChange() {
	    if (lockFirst) {
		return;
	    }
	    lockFirst = true;
	    if (!lockSecond) {
		second().set(calculateSecond());
	    }
	    lockFirst = false;
	}
    };
    private TyValue<Kind> _second = new TyValue<Kind>() {

	@Override
	public void afterChange() {
	    if (lockSecond) {
		return;
	    }
	    lockSecond = true;
	    if (!lockFirst) {
		first().set(calculateFirst());
	    }
	    lockSecond = false;
	}
    };

    public TyCalculation(TyValue<Kind> f, TyValue<Kind> s) {
	first().tie(f);
	second().tie(s);
    }

    public Kind calculateFirst() {
	return second().get();
    }

    public Kind calculateSecond() {
	return first().get();
    }

    public TyValue<Kind> first() {
	return _first;
    }

    public TyValue<Kind> second() {
	return _second;
    }
    
    public static void main(String[] args) {
	final TyValue<Double> tFahrenheit = new TyValue<Double>(0.0);
	final TyValue<Double> tCelsius = new TyValue<Double>(0.0);
	new TyCalculation(tFahrenheit, tCelsius) {

	    @Override
	    public Double calculateFirst() {
		return tCelsius.get() * 9.0 / 5.0 + 32.0;
	    }

	    @Override
	    public Double calculateSecond() {
		return (tFahrenheit.get() - 32) * 5.0 / 9.0;
	    }
	};
	System.out.println("let tFahrenheit=100");
	tFahrenheit.set(100.0);
	System.out.println("now tFahrenheit: " + tFahrenheit.get() + ", tCelsius: " + tCelsius.get());
	System.out.println("let tCelsius=100");
	tCelsius.set(100.0);
	System.out.println("now tFahrenheit: " + tFahrenheit.get() + ", tCelsius: " + tCelsius.get());
    }
}
