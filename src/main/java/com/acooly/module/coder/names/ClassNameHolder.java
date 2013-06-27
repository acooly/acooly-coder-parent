package com.acooly.module.coder.names;

public class ClassNameHolder implements NameHolder {

	private String classPath;
	private String packageName;
	private String className;
	private String variableName;

	@Override
	public String getFullPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

}
