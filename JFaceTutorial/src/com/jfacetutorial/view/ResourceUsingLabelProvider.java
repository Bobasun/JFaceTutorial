package com.jfacetutorial.view;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.jfacetutorial.App;
import com.jfacetutorial.modellayer.UserData;

public class ResourceUsingLabelProvider extends ColumnLabelProvider{
	
	private ResourceManager resourceManager = 
			new LocalResourceManager(JFaceResources.getResources());
	
	private Image image;
	
	public ResourceUsingLabelProvider() {
		ImageDescriptor imageDescriptor = 
				ImageDescriptor.createFromFile(App.class, "/icons/CHECKED.png");
		Image imageLocal = resourceManager.createImage(imageDescriptor);
		this.image = resize(imageLocal, 20, 20);
	}

	private Image resize (Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		  GC gc = new GC(scaled);
		  gc.setAntialias(SWT.ON);
		  gc.setInterpolation(SWT.HIGH);
		  gc.drawImage(image, 0, 0,image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		  gc.dispose();
		  image.dispose(); 
		  return scaled;
	}
	
	@Override
	public Image getImage(Object element) {
		
		if (((UserData) element).isTaskDone()) {
			return image;
		} 
		return null;
	}
	
	@Override
	public String getText(Object element) {
		return "";
	}

	@Override
    public void dispose() {
        super.dispose();
        resourceManager.dispose();
    }



	
}
