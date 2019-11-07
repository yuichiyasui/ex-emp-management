package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラ
 * @author yuichi
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 扶養人数を更新する際のリクエストパラメータを.
	 * formからmodelに自動的に格納するメソッド.
	 * @return 空のupdateEmployeeFormオブジェクト.
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員一覧を表示するページにフォワードするメソッド.
	 * @param model リクエストパラメータ.
	 * @return 従業員一覧画面.
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		model.addAttribute("employeeList", employeeService.showList());
		return "employee/list.html";
	}
	
	/**
	 * 従業員の詳細ページにフォワードするメソッド.
	 * @param id 詳細を表示したい従業員のID.
	 * @param model リクエストスコープ.
	 * @return 従業員の詳細画面.
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		model.addAttribute("employee", employeeService.showDetail(Integer.parseInt(id)));
		return "employee/detail.html";
	}
	
	/**
	 * 従業員詳細(扶養人数のみ)を更新する.
	 * @param form IDと扶養人数情報が入ったドメイン.
	 * @return 従業員一覧画面にリダイレクト.
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
}
