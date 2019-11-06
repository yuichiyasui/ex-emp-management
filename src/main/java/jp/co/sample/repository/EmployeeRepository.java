package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i)->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * 従業員一覧情報を入社日順で取得する
	 * (従業員が存在しない場合はサイズ0件の従業員一覧を返す)
	 * @return なし
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,"
				+ "zip_code,address,telephone,salary,characteristics,dependents_count"
				+" FROM employees ORDER BY hire_date ASC";
		List<Employee> employeeList = new ArrayList<>();
		employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	/**
	 * 主キーから従業員情報を取得する
	 * (従業員が存在しない場合はSpringが自動的に例外を発生する)
	 * @param id
	 * @return 検索された従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,"
				+ "zip_code,address,telephone,salary,characteristics,dependents_count"
				+" FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		Employee employee = new Employee();
		employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		try {
			return employee;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 従業員情報を変更する
	 * (今回は従業員情報の扶養人数だけ変更できるようなSQLを発行する)
	 * @param employee
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET dependents_count=:dependents_count";
		SqlParameterSource param = new MapSqlParameterSource().addValue("dependents_count",employee.getDependentsCount());
		template.update(sql, param);
	}
	
}
