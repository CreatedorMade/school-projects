package neurons;

public class LegNeuron extends CreatureNeuron {
	public LegNeuron() {
		super.maxInputs = 1;
		super.mods = new double[1];
		super.mods[0] = Math.random();
	}
	
	double data = 0;
	
	public double getSensoryOutput() {
		return data;
	}
	
	public void eval(int tick) {
		if(super.inputs.length != 0) {
			data = super.values[0];
		}
	}

}
