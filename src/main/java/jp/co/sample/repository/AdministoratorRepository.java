package jp.co.sample.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者のRepositoryクラス
 * @author yuichi
 */
@Repository
public class AdministoratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public final static RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i)->{
		Administrator admin = new Administrator();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setMailAddress(rs.getString("mail_address"));
		admin.setPassword(rs.getString("password"));
		return admin;
	};
	
	/**
	 * 新規に管理者情報をデータベースに登録
	 * @param administrator 挿入する管理者情報
	 */
	public void insert(Administrator administrator) {
		String insertSql =
				"INSERT INTO administrators(id, name, mail_address, password)"
				+ "VALUES(:id, :name, :mail_address, :password)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(insertSql, param);
		System.out.println("新しく管理者情報を登録しました。");
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得する(存在しない場合はnullを返す)
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 取得された管理者情報
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		String sql = "SELECT id, name, mail_address, password FROM administrators "
				+ "WHERE mail_address=:mailAddress AND password=:password";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("mailAddress",mailAddress).addValue("password",password);
		Administrator admin = new Administrator();
		admin = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(admin == null) {
			return null;
		}
		return admin;
	}
	
}
