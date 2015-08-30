package com.acooly.module.coder.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acooly.module.coder.generate.impl.DefaultCodeGeneratorFactory;
import com.google.common.collect.Lists;

public class Generator {

	protected Shell shell;

	ApplicationContext context;

	private Label statusbarLabel;
	private Composite statusbar;

	Composite compositeMain;
	Composite compositeDatabase;
	Composite compositeSetting;
	Composite compositeGenerate;

	private Text txtJdbcDriver;
	private Text txtJdbcUrl;
	private Text txtUsername;
	private Text txtPassword;
	private Label label;
	private Button btnNewButton;
	private Text txtTables;
	private Text txtTemplatePath;
	private Text txtCodePath;
	private Text txtResourcePath;
	private Text txtTestPath;
	private Text txtWebappPath;
	private Text txtPagePath;
	private Text txtWorkspace;
	private Text txtTableToEntityIgnorPrefix;
	private Text txtRootPackage;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Generator window = new Generator();
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

		shell.setLocation(display.getClientArea().width / 2 - shell.getSize().x / 2, display.getClientArea().height / 2
				- shell.getSize().y / 2);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN);
		shell.setSize(587, 453);
		shell.setText("Acooly代码生成器2.0.0");
		shell.setLayout(new GridLayout());

		compositeMain = new Composite(shell, SWT.BORDER);
		compositeMain.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout gl_compMain = new GridLayout(2, false);
		compositeMain.setLayout(gl_compMain);

		Tree tree = new Tree(compositeMain, SWT.BORDER);
		tree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TreeItem item = (TreeItem) event.item;
				if (item.getText().equals("数据库配置")) {
					hideSettingView();
					hideGenerateView();
					showDatabaseView(compositeMain);
				} else if (item.getText().equals("生成规则配置")) {
					hideDatabaseView();
					hideGenerateView();
					showSettingView(compositeMain);
				} else if (item.getText().equals("生成代码")) {
					hideDatabaseView();
					hideSettingView();
					showGenerateView(compositeMain);
				}
			}
		});

		tree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		TreeItem treeItemDatabase = new TreeItem(tree, SWT.NONE);
		treeItemDatabase.setText("数据库配置");

		TreeItem treeItemSetting = new TreeItem(tree, SWT.NONE);
		treeItemSetting.setText("生成规则配置");

		TreeItem treeItemGenerate = new TreeItem(tree, SWT.NONE);
		treeItemGenerate.setText("生成代码");
		treeItemGenerate.setExpanded(true);

		// showDatabaseView(compositeMain);
		// showSettingView(compositeMain);
		showGenerateView(compositeMain);
		createStatusbar(shell);
	}

	private void showGenerateView(Composite parent) {

		// if (compositeGenerate == null) {
		compositeGenerate = new Composite(parent, SWT.NONE);
		compositeGenerate.setLayoutData(new GridData(GridData.FILL_BOTH));
		compositeGenerate.setLayout(new FormLayout());
		Group groupGenerate = new Group(compositeGenerate, SWT.NONE);
		groupGenerate.setText("生成代码");
		FormData fd_groupDatabase = new FormData();
		fd_groupDatabase.top = new FormAttachment(0);
		fd_groupDatabase.left = new FormAttachment(0, 10);
		fd_groupDatabase.bottom = new FormAttachment(0, 362);
		fd_groupDatabase.right = new FormAttachment(100, -10);
		groupGenerate.setLayoutData(fd_groupDatabase);
		groupGenerate.setLayout(new FormLayout());

		Label label_1 = new Label(groupGenerate, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		FormData fd_label_1 = new FormData();
		fd_label_1.width = 80;
		fd_label_1.top = new FormAttachment(0, 10);
		fd_label_1.left = new FormAttachment(0, 10);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("工作主目录：");

		txtWorkspace = new Text(groupGenerate, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(label_1, -3, SWT.TOP);
		fd_text.left = new FormAttachment(label_1, 5);
		txtWorkspace.setLayoutData(fd_text);

		Button button = new Button(groupGenerate, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DirectoryDialog dlg = new DirectoryDialog(shell);
				dlg.setFilterPath(txtWorkspace.getText());
				dlg.setText("选择");
				dlg.setMessage("选择工作主目录");
				String selectedDirectory = dlg.open();
				if (selectedDirectory != null) {
					txtWorkspace.setText(selectedDirectory);
				}
			}
		});
		fd_text.right = new FormAttachment(button, -9);
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0, 5);
		fd_button.width = 50;
		fd_button.right = new FormAttachment(100, -10);
		button.setLayoutData(fd_button);
		button.setText("选择");

		Label label_2 = new Label(groupGenerate, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		FormData fd_label_2 = new FormData();
		fd_label_2.width = 80;
		fd_label_2.top = new FormAttachment(label_1, 15);
		fd_label_2.left = new FormAttachment(0, 10);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("表忽略前缀：");

		txtTableToEntityIgnorPrefix = new Text(groupGenerate, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(100, -10);
		fd_text_1.left = new FormAttachment(label_2, 6);
		fd_text_1.top = new FormAttachment(button, 6);
		txtTableToEntityIgnorPrefix.setLayoutData(fd_text_1);

		Label label_3 = new Label(groupGenerate, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(label_1, 47);
		fd_label_3.width = 80;
		fd_label_3.left = new FormAttachment(0, 10);
		label_3.setLayoutData(fd_label_3);
		label_3.setText("根包路径：");

		txtRootPackage = new Text(groupGenerate, SWT.BORDER);
		FormData fd_text_3 = new FormData();
		fd_text_3.top = new FormAttachment(button, 38);
		fd_text_3.right = new FormAttachment(100, -10);
		fd_text_3.left = new FormAttachment(label_3, 6);
		txtRootPackage.setLayoutData(fd_text_3);

		Label label_4 = new Label(groupGenerate, SWT.NONE);
		label_4.setAlignment(SWT.RIGHT);
		FormData fd_label_4 = new FormData();
		fd_label_4.width = 80;
		fd_label_4.top = new FormAttachment(label_3, 14);
		fd_label_4.left = new FormAttachment(0, 10);
		label_4.setLayoutData(fd_label_4);
		label_4.setText("表名：");

		txtTables = new Text(groupGenerate, SWT.BORDER);
		FormData fd_treeTables = new FormData();
		fd_treeTables.right = new FormAttachment(100, -10);
		fd_treeTables.top = new FormAttachment(txtRootPackage, 8);
		fd_treeTables.left = new FormAttachment(txtWorkspace, 0, SWT.LEFT);
		txtTables.setLayoutData(fd_treeTables);

		Button button_1 = new Button(groupGenerate, SWT.NONE);
		fd_treeTables.bottom = new FormAttachment(button_1, -6);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				saveParameters();
				callGenerate();
			}
		});

		FormData fd_button_1 = new FormData();
		fd_button_1.right = new FormAttachment(100, -10);
		fd_button_1.bottom = new FormAttachment(100, -5);
		fd_button_1.width = 80;
		button_1.setLayoutData(fd_button_1);
		button_1.setText("生成代码");
		// }

		Properties p = loadSetting();
		txtWorkspace.setText(p.getProperty("generator.workspace"));
		txtRootPackage.setText(p.getProperty("generator.rootPackage"));
		txtTableToEntityIgnorPrefix.setText(p.getProperty("generator.tableToEntityIgnorPrefix"));

		// loadAllTables();

		((GridData) compositeGenerate.getLayoutData()).exclude = false;
		compositeGenerate.setVisible(true);
		compositeGenerate.getParent().layout();

	}

	private void hideGenerateView() {
		if (compositeGenerate != null) {
			((GridData) compositeGenerate.getLayoutData()).exclude = true;
			compositeGenerate.setVisible(false);
			compositeGenerate.getParent().layout();
		}
	}

	/**
	 * 数据库设置界面
	 * 
	 * @param parent
	 */
	private void showDatabaseView(Composite parent) {
		if (compositeDatabase == null) {
			compositeDatabase = new Composite(parent, SWT.NONE);
			compositeDatabase.setLayoutData(new GridData(GridData.FILL_BOTH));
			compositeDatabase.setLayout(new FormLayout());
			Group groupDatabase = new Group(compositeDatabase, SWT.NONE);
			groupDatabase.setText("数据库连接设置");
			FormData fd_groupDatabase = new FormData();
			fd_groupDatabase.top = new FormAttachment(0);
			fd_groupDatabase.left = new FormAttachment(0, 10);
			fd_groupDatabase.bottom = new FormAttachment(0, 203);
			fd_groupDatabase.right = new FormAttachment(100, -10);
			groupDatabase.setLayoutData(fd_groupDatabase);
			groupDatabase.setLayout(new FormLayout());

			Label lblNewLabel = new Label(groupDatabase, SWT.NONE);
			lblNewLabel.setAlignment(SWT.RIGHT);
			FormData fd_lblNewLabel = new FormData();
			fd_lblNewLabel.width = 80;
			fd_lblNewLabel.top = new FormAttachment(0, 10);
			fd_lblNewLabel.left = new FormAttachment(0, 10);
			lblNewLabel.setLayoutData(fd_lblNewLabel);
			lblNewLabel.setText("数据库驱动：");

			txtJdbcDriver = new Text(groupDatabase, SWT.BORDER);
			FormData fd_text_jdbcDriver = new FormData();
			fd_text_jdbcDriver.top = new FormAttachment(lblNewLabel, -3, SWT.TOP);
			fd_text_jdbcDriver.right = new FormAttachment(100, -10);
			fd_text_jdbcDriver.left = new FormAttachment(lblNewLabel, 5);
			txtJdbcDriver.setLayoutData(fd_text_jdbcDriver);

			Label lblNewLabel_1 = new Label(groupDatabase, SWT.NONE);
			lblNewLabel_1.setAlignment(SWT.RIGHT);
			FormData fd_lblNewLabel_1 = new FormData();
			fd_lblNewLabel_1.width = 80;
			fd_lblNewLabel_1.top = new FormAttachment(lblNewLabel, 10);
			fd_lblNewLabel_1.left = new FormAttachment(0, 10);
			lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
			lblNewLabel_1.setText("连接URL：");

			txtJdbcUrl = new Text(groupDatabase, SWT.BORDER);
			fd_lblNewLabel_1.top = new FormAttachment(txtJdbcUrl, 3, SWT.TOP);
			FormData fd_text_jdbcUrl = new FormData();
			fd_text_jdbcUrl.top = new FormAttachment(txtJdbcDriver, 10);
			fd_text_jdbcUrl.left = new FormAttachment(lblNewLabel_1, 5);
			fd_text_jdbcUrl.right = new FormAttachment(100, -10);
			txtJdbcUrl.setLayoutData(fd_text_jdbcUrl);

			Label lblNewLabel_2 = new Label(groupDatabase, SWT.NONE);
			lblNewLabel_2.setAlignment(SWT.RIGHT);
			FormData fd_lblNewLabel_2 = new FormData();
			fd_lblNewLabel_2.left = new FormAttachment(0, 10);
			fd_lblNewLabel_2.width = 80;
			lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
			lblNewLabel_2.setText("用户名：");

			txtUsername = new Text(groupDatabase, SWT.BORDER);
			fd_lblNewLabel_2.top = new FormAttachment(txtUsername, 3, SWT.TOP);
			FormData fd_text_1 = new FormData();
			fd_text_1.top = new FormAttachment(txtJdbcUrl, 10);
			fd_text_1.left = new FormAttachment(lblNewLabel_2, 5);
			fd_text_1.right = new FormAttachment(100, -10);
			txtUsername.setLayoutData(fd_text_1);

			label = new Label(groupDatabase, SWT.NONE);
			label.setAlignment(SWT.RIGHT);
			FormData fd_label = new FormData();
			fd_label.left = new FormAttachment(0, 10);
			fd_label.width = 80;
			label.setLayoutData(fd_label);
			label.setText("密码：");

			txtPassword = new Text(groupDatabase, SWT.BORDER);
			fd_label.top = new FormAttachment(txtPassword, 3, SWT.TOP);
			FormData fd_text_password = new FormData();
			fd_text_password.top = new FormAttachment(txtUsername, 10);
			fd_text_password.left = new FormAttachment(label, 5);
			fd_text_password.right = new FormAttachment(100, -10);
			txtPassword.setLayoutData(fd_text_password);

			btnNewButton = new Button(groupDatabase, SWT.NONE);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					saveDatabaseSetting();
				}
			});
			FormData fd_btnNewButton = new FormData();
			fd_btnNewButton.width = 80;
			fd_btnNewButton.top = new FormAttachment(txtPassword, 15);
			fd_btnNewButton.right = new FormAttachment(txtJdbcDriver, 0, SWT.RIGHT);
			btnNewButton.setLayoutData(fd_btnNewButton);
			btnNewButton.setText("设置");
		}
		Properties p = loadSetting();
		txtJdbcDriver.setText(p.getProperty("jdbc.driver"));
		txtJdbcUrl.setText(p.getProperty("jdbc.url"));
		txtUsername.setText(p.getProperty("jdbc.username"));
		txtPassword.setText(p.getProperty("jdbc.password"));

		((GridData) compositeDatabase.getLayoutData()).exclude = false;
		compositeDatabase.setVisible(true);
		compositeDatabase.getParent().layout();
	}

	private void hideDatabaseView() {
		if (compositeDatabase != null) {
			((GridData) compositeDatabase.getLayoutData()).exclude = true;
			compositeDatabase.setVisible(false);
			compositeDatabase.getParent().layout();
		}
	}

	private void showStatusMessage(String message) {
		statusbarLabel.setText(message);
	}

	/**
	 * 参数设置界面
	 */
	private void showSettingView(Composite parent) {
		if (compositeSetting == null) {
			compositeSetting = new Composite(parent, SWT.NONE);
			compositeSetting.setLayoutData(new GridData(GridData.FILL_BOTH));
			compositeSetting.setLayout(new FormLayout());
			Group groupSetting = new Group(compositeSetting, SWT.NONE);
			FormData fd_groupSetting = new FormData();
			fd_groupSetting.top = new FormAttachment(0);
			fd_groupSetting.left = new FormAttachment(0, 10);
			fd_groupSetting.bottom = new FormAttachment(0, 312);
			fd_groupSetting.right = new FormAttachment(100, -10);
			groupSetting.setLayoutData(fd_groupSetting);
			groupSetting.setText("生成规则配置");

			Label label_1 = new Label(groupSetting, SWT.NONE);
			label_1.setAlignment(SWT.RIGHT);
			label_1.setBounds(10, 25, 100, 20);
			label_1.setText("模板目录：");

			txtTemplatePath = new Text(groupSetting, SWT.BORDER);
			txtTemplatePath.setBounds(116, 22, 287, 20);

			Label label_2 = new Label(groupSetting, SWT.NONE);
			label_2.setAlignment(SWT.RIGHT);
			label_2.setBounds(10, 60, 100, 20);
			label_2.setText("源代码目录：");

			txtCodePath = new Text(groupSetting, SWT.BORDER);
			txtCodePath.setBounds(116, 57, 287, 23);

			Label label_3 = new Label(groupSetting, SWT.NONE);
			label_3.setAlignment(SWT.RIGHT);
			label_3.setBounds(10, 95, 100, 20);
			label_3.setText("资源文件目录：");

			txtResourcePath = new Text(groupSetting, SWT.BORDER);
			txtResourcePath.setBounds(116, 92, 287, 23);

			Label label_4 = new Label(groupSetting, SWT.NONE);
			label_4.setAlignment(SWT.RIGHT);
			label_4.setBounds(10, 130, 100, 20);
			label_4.setText("测试代码目录：");

			txtTestPath = new Text(groupSetting, SWT.BORDER);
			txtTestPath.setBounds(116, 127, 287, 23);

			Label lblNewLabel_3 = new Label(groupSetting, SWT.NONE);
			lblNewLabel_3.setAlignment(SWT.RIGHT);
			lblNewLabel_3.setBounds(10, 165, 100, 20);
			lblNewLabel_3.setText("WEB根目录：");

			txtWebappPath = new Text(groupSetting, SWT.BORDER);
			txtWebappPath.setBounds(116, 162, 287, 23);

			Label label_5 = new Label(groupSetting, SWT.NONE);
			label_5.setAlignment(SWT.RIGHT);
			label_5.setBounds(10, 200, 100, 20);
			label_5.setText("页面程序目录：");

			txtPagePath = new Text(groupSetting, SWT.BORDER);
			txtPagePath.setBounds(115, 197, 288, 23);

			Button button = new Button(groupSetting, SWT.NONE);
			button.setBounds(323, 233, 80, 27);
			button.setText("保存設置");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					saveRuleSetting();
				}
			});

		}

		Properties p = loadSetting();
		txtTemplatePath.setText(p.getProperty("generator.templatePath"));
		txtCodePath.setText(p.getProperty("generator.codePath"));
		txtResourcePath.setText(p.getProperty("generator.resourcePath"));
		txtTestPath.setText(p.getProperty("generator.testPath"));
		txtWebappPath.setText(p.getProperty("generator.webappPath"));
		txtPagePath.setText(p.getProperty("generator.pagePath"));

		((GridData) compositeSetting.getLayoutData()).exclude = false;
		compositeSetting.setVisible(true);
		compositeSetting.getParent().layout();
	}

	private void hideSettingView() {
		if (compositeSetting != null) {
			((GridData) compositeSetting.getLayoutData()).exclude = true;
			compositeSetting.setVisible(false);
			compositeSetting.getParent().layout();
		}
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
		statusbarLabel.setLayoutData(new RowData(120, -1));
	}

	private void saveDatabaseSetting() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("jdbc.driver", txtJdbcDriver.getText());
		data.put("jdbc.url", txtJdbcUrl.getText());
		data.put("jdbc.username", txtUsername.getText());
		data.put("jdbc.password", txtPassword.getText());
		ConfigFileHelper.saveProperties(data);
		showStatusMessage("数据库设置保存成功。");
	}

	private void saveRuleSetting() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("generator.templatePath", txtTemplatePath.getText());
		data.put("generator.codePath", txtCodePath.getText());
		data.put("generator.resourcePath", txtResourcePath.getText());
		data.put("generator.testPath", txtTestPath.getText());
		data.put("generator.webappPath", txtWebappPath.getText());
		data.put("generator.pagePath", txtPagePath.getText());
		ConfigFileHelper.saveProperties(data);
		showStatusMessage("生成规则设置保存成功。");
	}

	private void saveParameters() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("generator.workspace", txtWorkspace.getText());
		data.put("generator.rootPackage", txtRootPackage.getText());
		data.put("generator.tableToEntityIgnorPrefix", txtTableToEntityIgnorPrefix.getText());
		ConfigFileHelper.saveProperties(data);
		showStatusMessage("生成参数保存成功。");
	}

	/**
	 * 调用代码生成
	 */
	private void callGenerate() {
		showStatusMessage("开始生成...");
		String tables = txtTables.getText();
		if (StringUtils.isBlank(tables)) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
			messageBox.setMessage("请先选择被生成的表，多个表使用空格或逗号分隔");
			messageBox.open();
			return;
		}
		List<String> listTbs = Lists.newArrayList();
		for (String tb : StringUtils.split(tables)) {
			if (!StringUtils.contains(tb, ",")) {
				listTbs.add(tb);
				continue;
			}
			for (String t : StringUtils.split(tb, ",")) {
				listTbs.add(t);
			}
		}
		DefaultCodeGeneratorFactory codeGeneratorFactory = (DefaultCodeGeneratorFactory) getContext().getBean(
				"codeGeneratorFactory");
		codeGeneratorFactory.generateTables(listTbs.toArray(new String[0]));
	}

	private Properties loadSetting() {
		return ConfigFileHelper.loadProperty();
	}

	private ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-main.xml");
		}
		return context;
	}
}
