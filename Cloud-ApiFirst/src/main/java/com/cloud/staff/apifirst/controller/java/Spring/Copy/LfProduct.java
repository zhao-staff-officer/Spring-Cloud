package com.cloud.staff.apifirst.controller.java.Spring.Copy;

import java.util.Date;

import com.cloud.staff.apifirst.controller.java.Basic.copy.Student;

public class LfProduct {
	/* 产品id */
    private String lfProductId;
    /* 物流金融账号 */
    private String lfAccountId;
    /* 产品名称 */
    private String productName;
    /* 产品类型(BAOFU:保付; MIAOFU:秒付; JISHIFU:即时付;) */
    private String productType;
    /* 适用场景列表（逗号分隔）(COD:代收货款; FREIGHT:运费;) */
    private String sceneTypes;
    /* 产品描述 */
    private String productDescription;
    /* 产品规则描述 */
    private String productRoleDescription;
    /* 放款触发行为 */
    private String loanAction;
    /* 收款触发行为 */
    private String repayAction;
    /* 产品状态(ENABLE:启用; DISABLE:停用;) */
    private String productStatus;
    /* 预警阀值 */
    private Long warnBalance;
    /* 发货人是否控制额度(1:是; 0:否;) */
    private Integer isConsignerCreditLimit;
    /* 承运商是否控制额度(1:是; 0:否;) */
    private Integer isExpressCreditLimit;
    
    private String    productAgreementUrl;
    
    private String    poundageCalcType;
    
    private String    poundageRate;
    
    /* 备注1 */
    private String remark1;
    /* 操作员编号 */
    private String operatorId;
    /* 输入日期 */
    private Date inputDate;
    /* 记录更新时间 */
    private Date stampDate;
    
    private Student student;
    
	public String getLfProductId() {
		return lfProductId;
	}
	public void setLfProductId(String lfProductId) {
		this.lfProductId = lfProductId;
	}
	public String getLfAccountId() {
		return lfAccountId;
	}
	public void setLfAccountId(String lfAccountId) {
		this.lfAccountId = lfAccountId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSceneTypes() {
		return sceneTypes;
	}
	public void setSceneTypes(String sceneTypes) {
		this.sceneTypes = sceneTypes;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductRoleDescription() {
		return productRoleDescription;
	}
	public void setProductRoleDescription(String productRoleDescription) {
		this.productRoleDescription = productRoleDescription;
	}
	public String getLoanAction() {
		return loanAction;
	}
	public void setLoanAction(String loanAction) {
		this.loanAction = loanAction;
	}
	public String getRepayAction() {
		return repayAction;
	}
	public void setRepayAction(String repayAction) {
		this.repayAction = repayAction;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public Long getWarnBalance() {
		return warnBalance;
	}
	public void setWarnBalance(Long warnBalance) {
		this.warnBalance = warnBalance;
	}
	public Integer getIsConsignerCreditLimit() {
		return isConsignerCreditLimit;
	}
	public void setIsConsignerCreditLimit(Integer isConsignerCreditLimit) {
		this.isConsignerCreditLimit = isConsignerCreditLimit;
	}
	public Integer getIsExpressCreditLimit() {
		return isExpressCreditLimit;
	}
	public void setIsExpressCreditLimit(Integer isExpressCreditLimit) {
		this.isExpressCreditLimit = isExpressCreditLimit;
	}
	public String getProductAgreementUrl() {
		return productAgreementUrl;
	}
	public void setProductAgreementUrl(String productAgreementUrl) {
		this.productAgreementUrl = productAgreementUrl;
	}
	public String getPoundageCalcType() {
		return poundageCalcType;
	}
	public void setPoundageCalcType(String poundageCalcType) {
		this.poundageCalcType = poundageCalcType;
	}
	public String getPoundageRate() {
		return poundageRate;
	}
	public void setPoundageRate(String poundageRate) {
		this.poundageRate = poundageRate;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public Date getStampDate() {
		return stampDate;
	}
	public void setStampDate(Date stampDate) {
		this.stampDate = stampDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
    
    

}
