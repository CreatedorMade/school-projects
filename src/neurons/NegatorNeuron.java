package neurons;

public class NegatorNeuron extends CreatureNeuron {
	public NegatorNeuron() {
		super.maxInputs = 1;
	}
	
	public void eval(int tick) {
		if(super.inputs.length != 1) {
			super.output = -super.values[0];
		}
	}

}
