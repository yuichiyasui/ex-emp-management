package jp.co.sample.form;

/**
 * ログイン情報のリクエストパラメータを受けるフォーム
 * @author yuichi
 *
 */
public class LoginForm {

	/**	ログイン時のメールアドレス */
	private String mailAddress;
	/**	ログイン時のパスワード */
	private String password;
	
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	
	
}
