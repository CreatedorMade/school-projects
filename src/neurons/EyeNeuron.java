package neurons;

public class EyeNeuron extends CreatureNeuron {
	double data = 0;
	public EyeNeuron() {
		super.mods = new double[2];
		super.mods[0] = Math.random();
		super.mods[1] = Math.random();
		super.maxInputs = 0;
	}
	
	public void eval(int tick) {
		super.output = data;
	}
	
	public void setSensoryInput(double d) {
		data = d;
	}
}
