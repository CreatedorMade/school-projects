package neurons;
public class MouthNeuron extends CreatureNeuron {
	public MouthNeuron() {
		super.maxInputs = 1;
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
