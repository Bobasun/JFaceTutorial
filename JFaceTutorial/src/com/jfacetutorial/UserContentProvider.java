package com.jfacetutorial;

import java.util.Map;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jfacetutorial.modellayer.UserData;

public class UserContentProvider implements IStructuredContentProvider{

	@Override
	public void dispose() {
		IStructuredContentProvider.super.dispose();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		IStructuredContentProvider.super.inputChanged(viewer, oldInput, newInput);
	}

	public UserContentProvider() {
	}

	@Override
	public Object[] getElements(Object arg0) {
		Map<Long,UserData> ss = (Map<Long,UserData>) arg0;
				return ss.values().toArray();
		
	}

	
}
