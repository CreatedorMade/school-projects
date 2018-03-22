package neurons;

public class AdderNeuron extends CreatureNeuron {
	public AdderNeuron() {
		super.maxInputs = -1;
	}
	
	public void eval(int tick) {
		double sum = 0;
		for(double d : super.values) sum += d;
		super.output = sum;
	}
}
