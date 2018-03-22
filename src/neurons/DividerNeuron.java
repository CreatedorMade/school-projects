package neurons;

public class DividerNeuron extends CreatureNeuron {
	public DividerNeuron() {
		super.maxInputs = 2;
	}
	
	public void eval(int tick) {
		if(super.inputs.length == 2) {
			super.output = super.values[0] / super.values[1];
		}
	}
	
}
