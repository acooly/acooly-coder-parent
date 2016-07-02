package com.acooly.module.coder.resolver;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NameScheme {

	private String domainClassName;

	private String domainPackage;

	private String enumPackage;

	private String daoClassName;

	private String daoPackage;

	private String daoImplClassName;

	private String daoImplPackage;

	private String daoTestPackage;

	private String daoTestClassName;

	private String servicePackage;

	private String serviceClassName;

	private String serviceImplPackage;
	private String serviceImplClassName;

	private String serviceTestPackage;
	private String serviceTestClassName;

	private String controllerPackage;
	private String controllerClassName;

	private String pagePath;
	private String listPageName;

	private String editPageName;
	private String ImportPageName;
	private String showPageName;

	public String getShowPageName() {
		return showPageName;
	}

	public void setShowPageName(String showPageName) {
		this.showPageName = showPageName;
	}

	public String getDomainClassName() {
		return domainClassName;
	}

	public void setDomainClassName(String domainClassName) {
		this.domainClassName = domainClassName;
	}

	public String getDaoClassName() {
		return daoClassName;
	}

	public void setDaoClassName(String daoClassName) {
		this.daoClassName = daoClassName;
	}

	public String getDaoImplClassName() {
		return daoImplClassName;
	}

	public void setDaoImplClassName(String daoImplClassName) {
		this.daoImplClassName = daoImplClassName;
	}

	public String getDaoTestClassName() {
		return daoTestClassName;
	}

	public void setDaoTestClassName(String daoTestClassName) {
		this.daoTestClassName = daoTestClassName;
	}

	public String getServiceClassName() {
		return serviceClassName;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	public String getServiceImplClassName() {
		return serviceImplClassName;
	}

	public void setServiceImplClassName(String serviceImplClassName) {
		this.serviceImplClassName = serviceImplClassName;
	}

	public String getServiceTestClassName() {
		return serviceTestClassName;
	}

	public void setServiceTestClassName(String serviceTestClassName) {
		this.serviceTestClassName = serviceTestClassName;
	}

	public String getControllerClassName() {
		return controllerClassName;
	}

	public void setControllerClassName(String controllerClassName) {
		this.controllerClassName = controllerClassName;
	}

	public String getListPageName() {
		return listPageName;
	}

	public void setListPageName(String listPageName) {
		this.listPageName = listPageName;
	}

	public String getEditPageName() {
		return editPageName;
	}

	public void setEditPageName(String editPageName) {
		this.editPageName = editPageName;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoImplPackage() {
		return daoImplPackage;
	}

	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}

	public String getDaoTestPackage() {
		return daoTestPackage;
	}

	public void setDaoTestPackage(String daoTestPackage) {
		this.daoTestPackage = daoTestPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getServiceTestPackage() {
		return serviceTestPackage;
	}

	public void setServiceTestPackage(String serviceTestPackage) {
		this.serviceTestPackage = serviceTestPackage;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getImportPageName() {
		return ImportPageName;
	}

	public void setImportPageName(String importPageName) {
		ImportPageName = importPageName;
	}

	public String getEnumPackage() {
		return enumPackage;
	}

	public void setEnumPackage(String enumPackage) {
		this.enumPackage = enumPackage;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
