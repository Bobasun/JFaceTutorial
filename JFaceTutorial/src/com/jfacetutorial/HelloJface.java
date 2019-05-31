package com.jfacetutorial;


import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import swing2swt.layout.BoxLayout;


public class HelloJface extends ApplicationWindow {
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private Table table;
	private Text txtDfdf;
	private Text text_1;

	/**
	 * Create the application window.
	 */
	public HelloJface() {
		super(null);
	}


	@Override
	protected Control createContents(Composite parent) {
		
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new FillLayout());
		
		Composite composite = new Composite(c,SWT.NONE);
		composite.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		Menu menuBar = new Menu(c.getShell(),SWT.BAR);
//		composite.setMenu(menu);
//		menu.setLocation(new Point(22, 220));
//		composite.setMenu(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menuBar, SWT.CASCADE);
		mntmNewSubmenu.setText("New SubMenu");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmNewItem = new MenuItem(menuBar, SWT.PUSH);
		
		MenuItem mntmNewItem_1 = new MenuItem(menuBar, SWT.PUSH);
		mntmNewItem_1.setText("Exit");
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Name");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Group");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("SWT done");
		tableViewer.setContentProvider(new ContentProvider());
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		
		text_1 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(100, -33);
		fd_text_1.top = new FormAttachment(0, 34);
		fd_text_1.bottom = new FormAttachment(0, 55);
		text_1.setLayoutData(fd_text_1);
		
		txtDfdf = new Text(composite_1, SWT.BORDER);
		FormData fd_txtDfdf = new FormData();
		fd_txtDfdf.top = new FormAttachment(text_1, 18);
		fd_txtDfdf.right = new FormAttachment(text_1, 0, SWT.RIGHT);
		fd_txtDfdf.left = new FormAttachment(text_1, 0, SWT.LEFT);
		txtDfdf.setLayoutData(fd_txtDfdf);
		txtDfdf.setText("dfdf");
		
		Button btnSwtTaskDone = new Button(composite_1, SWT.CHECK | SWT.CENTER);
		fd_txtDfdf.bottom = new FormAttachment(btnSwtTaskDone, -58);
		btnSwtTaskDone.setAlignment(SWT.LEFT);
		FormData fd_btnSwtTaskDone = new FormData();
		fd_btnSwtTaskDone.top = new FormAttachment(0, 152);
		fd_btnSwtTaskDone.right = new FormAttachment(100, -52);
		fd_btnSwtTaskDone.left = new FormAttachment(0, 91);
		btnSwtTaskDone.setLayoutData(fd_btnSwtTaskDone);
		btnSwtTaskDone.setText("SWT task done");
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		fd_btnSwtTaskDone.bottom = new FormAttachment(btnNewButton, -39);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100, -24);
		fd_btnNewButton.right = new FormAttachment(100, -253);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("New Button");
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.bottom = new FormAttachment(100, -24);
		fd_btnNewButton_1.right = new FormAttachment(100, -172);
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(btnNewButton, 6);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("New Button");
		
		Button btnNewButton_2 = new Button(composite_1, SWT.NONE);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.bottom = new FormAttachment(100, -24);
		fd_btnNewButton_2.right = new FormAttachment(100, -10);
		fd_btnNewButton_2.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		btnNewButton_2.setText("New Button");
		
		Button btnNewButton_3 = new Button(composite_1, SWT.NONE);
		fd_btnNewButton_2.left = new FormAttachment(btnNewButton_3, 6);
		FormData fd_btnNewButton_3 = new FormData();
		fd_btnNewButton_3.bottom = new FormAttachment(100, -24);
		fd_btnNewButton_3.right = new FormAttachment(100, -91);
		fd_btnNewButton_3.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_3.left = new FormAttachment(btnNewButton_1, 6);
		btnNewButton_3.setLayoutData(fd_btnNewButton_3);
		btnNewButton_3.setText("New Button");
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		fd_text_1.left = new FormAttachment(lblNewLabel, 37);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -276);
		fd_lblNewLabel.top = new FormAttachment(text_1, -2, SWT.TOP);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Name");
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.bottom = new FormAttachment(txtDfdf, 0, SWT.BOTTOM);
		fd_lblNewLabel_1.top = new FormAttachment(lblNewLabel, 20);
		fd_lblNewLabel_1.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_lblNewLabel_1.right = new FormAttachment(txtDfdf, -2);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Group");
		sashForm.setWeights(new int[] {420, 340});
	    return c;
	}

	public static void main(String args[]) {
			new HelloJface().run();
	}


	private void run() {
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}
}
