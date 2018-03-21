package neurons;

public class ConstantNeuron extends CreatureNeuron {
	public ConstantNeuron() {
		super.mods = new double[1];
		super.mods[0] = Math.random()-0.5;
		super.maxInputs = 0;
	}
	
	public void eval(int tick) {
		super.output = (mods[0]-0.5)*20;
	}
}
