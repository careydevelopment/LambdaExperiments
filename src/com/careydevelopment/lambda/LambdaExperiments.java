package com.careydevelopment.lambda;

public class LambdaExperiments {

	public static void main(String... args) {
		LambdaExperiments le = new LambdaExperiments();
		le.go();
	}
	
	private void go() {
		playWithStateChange();
		playWithParameters();
	}
	
	private void playWithStateChange() {
		System.err.println("\n\n\n--- STARTING STATE CHANGE ---");

		StateOwner so = new StateOwner();
		so.addStateListener(() -> {
			System.err.println("I JUST CHANGED THE STATE!");
		});

		so.fireChangeState();
		
		System.err.println("--- ENDING STATE CHANGE ---");
	}
	
	private void playWithParameters() {
		System.err.println("\n\n\n--- STARTING PARAMETERS ---");

		MathHandler mh = (i1, i2) -> i1 * i2;
		MathOwner mo = new MathOwner(mh);
		mo.calculate(3, 4);
		
		MathOwner mo2 = new MathOwner((i1, i2) -> {
			return i1 * i2 * 3;
		});
		mo2.calculate(4, 5);
		
		System.err.println("--- ENDING PARAMETERS ---");
	}	
	
	private static class StateOwner {
		
		private StateChangeListener scl;
		
		void addStateListener(StateChangeListener scl) {
			this.scl = scl;
		}
		
		void fireChangeState() {
			scl.onStateChange();
		}
	}
	
	private static class MathOwner {
		private MathHandler mh;
		
		MathOwner(MathHandler mh) {
			this.mh = mh;
		}
		
		void calculate(int i1, int i2) {
			int result = mh.doMath(i1, i2);
			System.err.println("Result is " + result);
		}
	}
}
