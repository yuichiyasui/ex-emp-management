package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * EmployeeRepositoryを操作するサービスクラス
 * @author yuichi
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員一覧を取得するメソッド.
	 * @return 従業員一覧のリスト
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 主キーから従業員情報を取得する.
	 * 従業員が存在しない場合はSpringが自動的に例外を発生する。
	 * 
	 * @param id ID
	 * @return 検索された従業員情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報を更新する.
	 * 
	 * @param employee 更新する従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
}
