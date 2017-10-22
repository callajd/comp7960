package edu.auburn.neuralnetwork;

public interface GRNNResultRecord<T extends Comparable<T>> {

	public T getInput();
	
	public double getAccuracy();
	public int getTotalCount();
	
	public int getSuccessCount();
	public int getTruePositiveCount();
	public int getTrueNegativeCount();

	public int getFailureCount();
	public int getFalsePositiveCount();
	public int getFalseNegativeCount();
	
	public default String recordString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append(String.format("input : %f\n", getInput()));
	    builder.append("---------------------------\n");
	    builder.append("TP\tTN\tFP\tFN\n");
	    builder.append(String.format("%d\t%d\t%d\t%d\n", getTruePositiveCount(), getTrueNegativeCount(), getFalsePositiveCount(), getFalseNegativeCount()));
	    builder.append("---------------------------\n");
	    builder.append(String.format("matches / total : %d / %d\n", getSuccessCount(), getTotalCount()));
	    builder.append(String.format("accuracy : %f\n", getAccuracy()));
	    return builder.toString();
	  }
}
