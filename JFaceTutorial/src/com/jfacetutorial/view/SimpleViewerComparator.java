package com.jfacetutorial.view;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import com.jfacetutorial.modellayer.UserData;

public class SimpleViewerComparator extends ViewerComparator {
	private int direction = SWT.UP;

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		UserData user1 = (UserData) e1;
		UserData user2 = (UserData) e2;
		int result = user1.getName().compareToIgnoreCase(user2.getName());
		if (direction == SWT.UP) {
			result = -result;
		}
		return result;
	}

	public int change() {
		if (direction == SWT.UP) {
			direction = SWT.DOWN;
		} else {
			direction = SWT.UP;
		}
		return direction;
	}

}
