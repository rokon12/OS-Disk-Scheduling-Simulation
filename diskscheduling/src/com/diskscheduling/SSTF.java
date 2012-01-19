package com.diskscheduling;

public class SSTF {
	String[] str;
	String[] cylNum;
	int x[];
	int nearest = -1;

	public int findClosest(int desiredNumber) {
		int bestDistanceFoundYet = 1000;
		int d;
		int i;
		for (int k = 0; k < x.length; k++) {
			if (nearest == x[k])
				x[k] = 5000;
		}

		for (i = 1; i < x.length; i++) {
			d = Math.abs(desiredNumber - x[i]);
			if (d < bestDistanceFoundYet) {
				bestDistanceFoundYet = d;
				nearest = x[i];
			}
		}
		return nearest;
	}

	public int[] getSftf(int[] x, int st) {
		this.x = x;
		int[] sstf = new int[x.length + 1];
		sstf[0] = st;

		int desiredNumber = st;
		
		for (int count = 0; count < x.length - 1; count++) {
			sstf[count + 1] = findClosest(desiredNumber);

			System.out.println(sstf[count + 1]);
			desiredNumber = nearest;
		}
		return sstf;
	}

	public static void main(String[] args) {
		int[] x = { 89, 183, 37, 122, 14, 124, 65, 67 };
		SSTF sstf = new SSTF();
		int[] sst = sstf.getSftf(x, 53);
		for (int i = 0; i < sst.length; i++) {
			System.out.print(sst[i] + ",");
		}
	}
}
