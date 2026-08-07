package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.exception.BackEndException;
import com.example.fullness.stationary.form.EmployeeAccountForm;
import com.example.fullness.stationary.helper.EmployeeAccountHelper;
import com.example.fullness.stationary.service.EmployeeAccountService;

import java.util.List;

/**
 * UC009「担当者アカウント登録」の画面遷移を担当するController。
 * 
 */
@Controller
@RequestMapping("/admin/account")
@SessionAttributes("employeeAccountForm")
public class EmployeeAccountController {

    @Autowired
    EmployeeAccountService employeeAccountService;

    @Autowired
    EmployeeAccountHelper employeeAccountHelper;

    /**
     * 社員名の選択肢に表示する、アカウント未作成の社員一覧をModelに格納する。
     *
     * 
     */
    @ModelAttribute("employeeList")
    public List<Employee> employeeList() {
        return employeeAccountService.findEmployeesWithoutAccount();
    }

    /**
     * /admin/account でアクセスされた場合に入力画面へ遷移する。
     *
     * @return 入力画面へのリダイレクト
     */
    @GetMapping
    public String index() {
        return "redirect:/admin/account/form";
    }

    /**
     * BP003「担当者アカウント登録(入力)」画面を表示する。
     *
     * <p>
     * 確認画面から「戻る」で遷移してきた場合は、セッションに保持されている
     * 入力値をそのまま表示する。
     * </p>
     *
     * @param model Model
     * @return 入力画面
     */
    @GetMapping("/form")
    public String form(Model model) {
        if (!model.containsAttribute("employeeAccountForm")) {
            model.addAttribute("employeeAccountForm", new EmployeeAccountForm());
        }
        return "admin/account/form";
    }

    /**
     * 入力内容をチェックし、BP004「担当者アカウント登録(確認)」画面を表示する。
     *
     * <p>
     * 入力チェックでエラーがあった場合は、エラーメッセージとともに
     * 入力画面に戻す(例外シナリオ)。
     * </p>
     *
     * @param employeeAccountForm 入力されたForm
     * @param bindingResult       入力チェック結果
     * @return 確認画面。入力チェックエラー時は入力画面
     */
    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute EmployeeAccountForm employeeAccountForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/account/form";
        }
        Employee employee = employeeAccountService.findEmployeeById(employeeAccountForm.getEmployeeId());
        if (employee == null) {
            bindingResult.rejectValue("employeeId", "employeeId.notFound", "社員名を選択してください");
            return "admin/account/form";
        }
        // 確認画面に表示する社員名をFormに設定する
        employeeAccountForm.setEmployeeName(employee.getName());
        return "admin/account/confirm";
    }

    /**
     * 確認画面の「戻る」ボタン押下時の処理。入力値を保持したまま入力画面に戻す。
     *
     * @return 入力画面
     */
    @PostMapping("/back")
    public String back() {
        return "admin/account/form";
    }

    /**
     * 確認画面の「登録」ボタン押下時の処理。社員アカウントを登録する。
     *
     * <p>
     * 登録後は完了画面へリダイレクトする(PRGパターン)。
     * </p>
     *
     * @param employeeAccountForm セッションに保持されている入力内容
     * @param sessionStatus       セッションの状態
     * @return 完了画面へのリダイレクト
     */
    @PostMapping("/register")
    public String register(@ModelAttribute EmployeeAccountForm employeeAccountForm,
            SessionStatus sessionStatus) {
        // EmployeeAccount employeeAccount =
        // employeeAccountHelper.convert(employeeAccountForm);
        // employeeAccountService.create(employeeAccount);
        sessionStatus.setComplete();
        return "redirect:/admin/account/complete";
    }

    /**
     * BP005「担当者アカウント登録(完了)」画面を表示する。
     *
     * @return 完了画面
     */
    @GetMapping("/complete")
    public String complete() {
        return "admin/account/complete";
    }

    /**
     * 確認画面・入力画面の「キャンセル」リンク押下時の処理。
     * 入力値を破棄してメニュー画面へ戻す。
     *
     * @param sessionStatus セッションの状態
     * @return メニュー画面へのリダイレクト
     */
    @GetMapping("/cancel")
    public String cancel(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/admin";
    }

    /**
     * アカウント名の重複が発生した場合の処理。
     *
     * <p>
     * 仕様書の例外処理に従い、エラーメッセージとともに入力画面へリダイレクトする。
     * </p>
     *
     * @param e                  発生した業務例外
     * @param redirectAttributes リダイレクト先へ値を引き継ぐためのオブジェクト
     * @return 入力画面へのリダイレクト
     */
    @ExceptionHandler(BackEndException.class)
    public String businessExceptionHandler(BackEndException e,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/admin/account/form";
    }
}
