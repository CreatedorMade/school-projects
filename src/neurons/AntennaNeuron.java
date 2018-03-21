package neurons;

public class AntennaNeuron extends CreatureNeuron {
	public AntennaNeuron() {
		super.mods = new double[1];
		mods[0] = Math.random();
		super.maxInputs = 0;
	}
	
	double data = 0;
	
	public void setSensoryInput(double d) {
		data = d;
	}
	
	public void eval(int tick) {
		super.output = data;
	}

}
