package com.acooly.module.coder.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acooly.module.coder.generate.impl.DefaultCodeGeneratorFactory;

public class AcoolyCodeGeneratorGui {

	protected Shell shell;

	private Label statusbarLabel;
	private Composite statusbar;

	private Text txtTables;
	private Text txtTableToEntityIgnorPrefix;
	private Text txtRootPackage;
	private Text txtWorkspace;
	private Text txtJdbcDriver;
	private Text txtJdbcUrl;
	private Text txtJdbcUsername;
	private Text txtJdbcPassword;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AcoolyCodeGeneratorGui window = new AcoolyCodeGeneratorGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Shell getShell() {
		return shell;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN);
		shell.setSize(567, 442);
		shell.setText("Acooly Code Generator");
		shell.setLayout(new GridLayout());
		createMenu(shell);
		createMainView(shell);
		createStatusbar(shell);
		initDefaultSetting();

	}

	private void createMainView(Shell shell) {
		Composite comp = new Composite(shell, SWT.BORDER);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new FormLayout());

		Label lblNewLabel = new Label(comp, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("选择数据表：");

		txtTables = new Text(comp, SWT.BORDER);
		fd_lblNewLabel.top = new FormAttachment(txtTables, 15, SWT.TOP);
		fd_lblNewLabel.right = new FormAttachment(txtTables, -1);
		FormData fd_txt_tables = new FormData();
		fd_txt_tables.height = 40;
		fd_txt_tables.left = new FormAttachment(0, 112);
		fd_txt_tables.right = new FormAttachment(100, -100);
		fd_txt_tables.top = new FormAttachment(0);
		txtTables.setLayoutData(fd_txt_tables);

		Button btnSelect = new Button(comp, SWT.NONE);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		FormData fd_btnSelect = new FormData();
		fd_btnSelect.left = new FormAttachment(txtTables, 10);
		fd_btnSelect.right = new FormAttachment(100, -10);
		btnSelect.setLayoutData(fd_btnSelect);
		btnSelect.setText("选择");

		Label label = new Label(comp, SWT.NONE);
		FormData fd_label = new FormData();
		label.setLayoutData(fd_label);
		label.setText("表名前缀：");

		txtTableToEntityIgnorPrefix = new Text(comp, SWT.BORDER);
		fd_btnSelect.bottom = new FormAttachment(txtTableToEntityIgnorPrefix, -6);
		fd_label.top = new FormAttachment(txtTableToEntityIgnorPrefix, 7, SWT.TOP);
		fd_label.right = new FormAttachment(txtTableToEntityIgnorPrefix, -1);
		FormData fd_text_2 = new FormData();
		fd_text_2.top = new FormAttachment(txtTables, 6);
		fd_text_2.height = 20;
		fd_text_2.right = new FormAttachment(100, -10);
		fd_text_2.left = new FormAttachment(0, 112);
		txtTableToEntityIgnorPrefix.setLayoutData(fd_text_2);

		Label lblRootPackage = new Label(comp, SWT.NONE);
		FormData fd_lblRootPackage = new FormData();
		fd_lblRootPackage.right = new FormAttachment(txtTableToEntityIgnorPrefix, -1);
		lblRootPackage.setLayoutData(fd_lblRootPackage);
		lblRootPackage.setText("Root Package：");

		txtRootPackage = new Text(comp, SWT.BORDER);
		fd_lblRootPackage.top = new FormAttachment(txtRootPackage, 5, SWT.TOP);
		FormData fd_text_3 = new FormData();
		fd_text_3.top = new FormAttachment(txtTableToEntityIgnorPrefix, 6);
		fd_text_3.height = 20;
		fd_text_3.right = new FormAttachment(100, -10);
		fd_text_3.left = new FormAttachment(0, 112);
		txtRootPackage.setLayoutData(fd_text_3);

		Label lblNewLabel_1 = new Label(comp, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("工作主目录：");

		txtWorkspace = new Text(comp, SWT.BORDER);
		fd_lblNewLabel_1.right = new FormAttachment(txtWorkspace, -1);
		fd_lblNewLabel_1.top = new FormAttachment(txtWorkspace, 5, SWT.TOP);
		FormData fd_txtWorkspace = new FormData();
		fd_txtWorkspace.top = new FormAttachment(txtRootPackage, 6);
		fd_txtWorkspace.height = 20;
		fd_txtWorkspace.right = new FormAttachment(100, -10);
		fd_txtWorkspace.left = new FormAttachment(0, 112);
		txtWorkspace.setLayoutData(fd_txtWorkspace);

		Group group = new Group(comp, SWT.NONE);
		group.setText("数据库配置");
		FormData fd_group = new FormData();
		fd_group.top = new FormAttachment(txtWorkspace, 13);
		fd_group.left = new FormAttachment(0, 10);
		fd_group.right = new FormAttachment(100, -10);
		group.setLayoutData(fd_group);

		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setBounds(10, 30, 100, 20);
		lblNewLabel_2.setText("数据库驱动：");

		txtJdbcDriver = new Text(group, SWT.BORDER);
		txtJdbcDriver.setBounds(116, 28, 401, 23);

		Label lblNewLabel_3 = new Label(group, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.RIGHT);
		lblNewLabel_3.setBounds(10, 60, 100, 20);
		lblNewLabel_3.setText("连接字符串：");

		txtJdbcUrl = new Text(group, SWT.BORDER);
		txtJdbcUrl.setBounds(116, 57, 401, 23);

		Label lblNewLabel_4 = new Label(group, SWT.NONE);
		lblNewLabel_4.setAlignment(SWT.RIGHT);
		lblNewLabel_4.setBounds(10, 90, 100, 20);
		lblNewLabel_4.setText("数据库用户名：");

		txtJdbcUsername = new Text(group, SWT.BORDER);
		txtJdbcUsername.setBounds(116, 87, 401, 23);

		Label lblNewLabel_5 = new Label(group, SWT.NONE);
		lblNewLabel_5.setAlignment(SWT.RIGHT);
		lblNewLabel_5.setBounds(10, 120, 100, 20);
		lblNewLabel_5.setText("数据库密码：");

		txtJdbcPassword = new Text(group, SWT.BORDER);
		txtJdbcPassword.setBounds(116, 116, 401, 23);

		Button btnGenerate = new Button(comp, SWT.NONE);
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callGenerate();
			}
		});

		FormData fd_btnGenerate = new FormData();
		fd_btnGenerate.top = new FormAttachment(group, 10);
		fd_btnGenerate.right = new FormAttachment(100, -10);
		fd_btnGenerate.width = 100;
		btnGenerate.setLayoutData(fd_btnGenerate);
		btnGenerate.setText("生成代码");
	}

	private void createStatusbar(Composite parent) {
		statusbar = new Composite(parent, SWT.BORDER);
		// 设置工具栏在Shell中的形状为水平抢占充满，并高19像素
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 20;
		statusbar.setLayoutData(gridData);
		// 设置为用行列式布局管理状态栏里的组件
		RowLayout layout = new RowLayout();
		layout.marginLeft = layout.marginTop = 0; // 无边距
		statusbar.setLayout(layout);
		// 创建一个用于显示文字的标签
		statusbarLabel = new Label(statusbar, SWT.BORDER);
		statusbarLabel.setLayoutData(new RowData(70, -1));
	}

	private void createMenu(Shell shell) {
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		// 文件主菜单
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("文件");
		Menu filemenu = new Menu(shell, SWT.DROP_DOWN);
		mntmFile.setMenu(filemenu);

		// 設置菜单
		MenuItem settingItem = new MenuItem(filemenu, SWT.PUSH);
		settingItem.setText("设置");
		settingItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
			}
		});

		// 退出菜单
		MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("退出");
		exitItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				getShell().close();
			}
		});

		// 帮助主菜单
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("帮助");
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		mntmHelp.setMenu(helpMenu);

		// 帮助菜单
		MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
		helpItem.setText("使用指导");
		helpItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
			}
		});

		// 关于菜单
		MenuItem aboutItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutItem.setText("关于");
		aboutItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
			}
		});
	}

	/**
	 * 加载默认配置 从 classpath:application.properties
	 */
	private void initDefaultSetting() {
		Properties p = loadDefaultSetting();
		txtWorkspace.setText(p.getProperty("generator.workspace", ""));
		txtTableToEntityIgnorPrefix.setText(p.getProperty("generator.tableToEntityIgnorPrefix", ""));
		txtRootPackage.setText(p.getProperty("generator.rootPackage", ""));

		txtJdbcDriver.setText(p.getProperty("jdbc.driver", ""));
		txtJdbcUrl.setText(p.getProperty("jdbc.url", ""));
		txtJdbcUsername.setText(p.getProperty("jdbc.username", ""));
		txtJdbcPassword.setText(p.getProperty("jdbc.password", ""));
	}

	private Properties loadDefaultSetting() {
		String propertyFileName = "application.properties";
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (in != null)
				p.load(in);
		} catch (IOException e) {
			System.out.println("load " + propertyFileName + " int Properties error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("close " + propertyFileName + " error!");
				}
			}
		}
		return p;
	}

	private void showStatusMessage(String message) {
		statusbarLabel.setText(message);
	}

	/**
	 * 调用代码生成
	 */
	private void callGenerate() {
		showStatusMessage("开始生成...");
		String tables = txtTables.getText();

		if (StringUtils.isBlank(tables)) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
			messageBox.setMessage("请先选择被生成的表，多个表使用空格分隔");
			messageBox.open();
			return;
		}

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-main.xml");
		DefaultCodeGeneratorFactory codeGeneratorFactory = (DefaultCodeGeneratorFactory) context
				.getBean("codeGeneratorFactory");
		codeGeneratorFactory.generateTables(tables);
	}
}
