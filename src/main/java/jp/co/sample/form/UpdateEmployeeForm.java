package jp.co.sample.form;

/**
 * 更新する従業員情報のリクエストパラメータを受け取るフォーム
 * @author yuichi
 *
 */
public class UpdateEmployeeForm {
	
	/**	扶養人数を変更する従業員のID */
	private String id;
	/**	扶養人数 */
	private String depenentsCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepenentsCount() {
		return depenentsCount;
	}
	public void setDepenentsCount(String depenentsCount) {
		this.depenentsCount = depenentsCount;
	}
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", depenentsCount=" + depenentsCount + "]";
	}
	

	
	
}
