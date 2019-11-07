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
	 * 従業員一覧を取得するメソッド
	 * @return 従業員一覧のリスト
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
}
