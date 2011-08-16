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
    /*public static Calculation<Double> expression(BiValue<Double> fValue) {
    BiValue<Double> sValue = new BiValue<Double>(0.0);
    Calculation<Double> xx = new Calculation<Double>(fValue, sValue);
    return xx;
    }
    public static Calculation<Double> plus(double pValue) {
    BiValue<Double> sValue = new BiValue<Double>(0.0);
    Calculation<Double> xx = new Calculation<Double>(fValue, sValue);
    return xx;
    }*/
    /*public static BiValue<Integer> toInteger(BiValue<Double> doubleValue) {
    BiValue<Integer> integerValue = new BiValue<Integer>(0);
    return integerValue;
    }
    public static Calculation<Double> plus(BiValue<Double> doubleValue) {
    BiValue<Integer> integerValue = new BiValue<Integer>(0);
    return integerValue;
    }*/
    /*public static void main(String[] args) {
    BiValue<Double> ff = new BiValue<Double>();
    BiValue<Double> cc = new BiValue<Double>(Calculation.expression(ff));
    //ff=new BiValue<Double>(Calculation.expression(cc).minus(8));
    
    System.out.println(ff.get() + " / " + cc.get());
    ff.set(1.0);
    System.out.println(ff.get() + " / " + cc.get());
    cc.set(2.0);
    System.out.println(ff.get() + " / " + cc.get());
    }*/
    /*
    public static void main(String[] args) {
    final BiValue<Double> tFahrenheit = new BiValue<Double>(0.0);
    final BiValue<Double> tCelsius = new BiValue<Double>(0.0);
    new Calculation(tFahrenheit, tCelsius) {
    
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
    }*/
}
