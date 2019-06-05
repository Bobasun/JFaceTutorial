package com.jfacetutorial;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.jfacetutorial.modellayer.UserData;

public class ResourceUsingLabelProvider extends ColumnLabelProvider{
	
	private ResourceManager resourceManager = 
			new LocalResourceManager(JFaceResources.getResources());
	

//	@Override
//	public Image getImage(Object element) {
		
//		if (((UserData) element).isTaskDone()) {
//		ImageDescriptor imageDescriptor = 
//				ImageDescriptor.createFromFile(View.class, "/icons/CHECKED.png");
//		return resourceManager.createImage(imageDescriptor);
//		}
//		return null;
//	}
	
	@Override
	public String getText(Object element) {
		UserData user = (UserData) element;
		return "" + user.isTaskDone();
	}

	@Override
    public void dispose() {
        super.dispose();
        resourceManager.dispose();
    }



	public ResourceUsingLabelProvider() {
	}

}
