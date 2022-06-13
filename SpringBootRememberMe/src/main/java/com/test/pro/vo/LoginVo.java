package com.test.pro.vo;

/**
 * 用户VO
 * 
 * @author hai
 *
 */
public class LoginVo {
	private String accountId;
	private String passWord;
	private Boolean rememberMe;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
