package com.diskscheduling;

import java.util.Arrays;

public class ScanDisk {
	int[] x;
	int[] finalX;
	int startingHead;

	public ScanDisk(int[] scan, int start) {
		x = scan;
		finalX = new int[x.length + 2];
		startingHead = start;
	}

	public int[] calculateScanDisk() {
		int[] sortedX = new int[x.length];
		int[] modified;

		int count = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i] >= 0 && x[i] < startingHead) {
				sortedX[count] = x[i];
				count++;
			}
		}
		modified = new int[count];

		for (int i = 0; i < modified.length; i++) {
			modified[i] = sortedX[i];
		}
		Arrays.sort(modified);
		int num = 0;
		finalX[num] = startingHead;
		num++;
		for (int i = modified.length - 1; i >= 0; i--) {
			finalX[num] = modified[i];
			num++;
		}

		finalX[num] = 0;
		num++;
		int[] secondPart = new int[finalX.length];

		int a = 0, b = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i] > startingHead) {
				secondPart[b] = x[i];
				b++;
			}
		}
		int abc[] = new int[b];
		for (int i = 0; i < abc.length; i++) {
			abc[i] = secondPart[i];
		}

		Arrays.sort(abc);
		for (int i = 0; i < b; i++) {
			finalX[num] = abc[i];
			num++;
		}

		int[] readyX = new int[finalX.length - 1];
		for (int i = 0; i < readyX.length; i++) {
			readyX[i] = finalX[i];
		}

		return readyX;
	}
}
