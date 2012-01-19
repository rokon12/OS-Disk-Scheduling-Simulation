package com.diskscheduling;

import java.util.Arrays;

public class ClookDisk {
	int[] x;
	int start;

	public ClookDisk(int[] clook, int start) {
		this.x = clook;
		this.start = start;
	}

	public int[] calculate() {
		int[] temp = new int[x.length];
		int[] temp1 = new int[x.length];
		int a = 0, b = 0;
		for (int i = 0; i < temp.length; i++) {
			if (x[i] > start) {
				temp[a] = x[i];
				a++;
			} else {
				temp1[b] = x[i];
				b++;
			}
		}
		int aa[] = new int[a];
		int[] bb = new int[b];

		aa[0] = start;
		for (int i = 1; i < aa.length; i++) {
			aa[i] = temp[i];
		}
		for (int i = 0; i < bb.length; i++) {
			bb[i] = temp1[i];
		}

		Arrays.sort(aa);
		Arrays.sort(bb);

		int c = 0;
		int[] finalX = new int[x.length];
		for (int i = 0; i < aa.length; i++) {
			finalX[c] = aa[i];
			c++;
		}

		for (int i = 0; i < bb.length; i++) {
			finalX[c] = bb[i];
			c++;
		}
		return finalX;
	}
}
