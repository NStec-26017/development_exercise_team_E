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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.exception.BusinessException;
import com.example.fullness.stationary.form.EmployeeAccountForm;
import com.example.fullness.stationary.helper.EmployeeAccountHelper;
import com.example.fullness.stationary.service.EmployeeAccountService;

import java.util.List;

/**
 * UC009「担当者アカウント登録」を担当するController。
 * 
 */
@Controller
// このController内の全リクエストハンドラメソッドに共通するURLパスを指定する
@RequestMapping("/admin/account")
// 入力画面と確認画面をまたいでFormの入力値を保持するため、
// "form"という属性名のオブジェクトをSessionスコープで管理するよう指示する。
// 画面側が ${form.xxx} で参照しているため、属性名を "form" にしている
@SessionAttributes("form")
public class EmployeeRegisterController {

    @Autowired
    EmployeeAccountService employeeAccountService;

    /** FormからEntityへの変換を担当するHelper。 */
    @Autowired
    EmployeeAccountHelper employeeAccountHelper;

    /**
     * Formのインスタンスを生成してModelに格納する
     *
     * <p>
     * {@code @ModelAttribute}を付与したメソッドは、リクエストハンドラメソッドが
     * 呼び出される<b>前に</b>必ず実行される。ここでFormをインスタンス化しておくことで、
     * 画面のフォーム({@code th:object="${form}"})と紐付けできる状態になる。
     * </p>
     *
     * <p>
     * このメソッドがあることで、各リクエストハンドラメソッド内で
     * Formの有無を確認して生成する処理が不要になる。
     * また、{@code @SessionAttributes}によりSessionにFormが存在する場合は、
     * Spring MVCがSession上のインスタンスを優先して使用するため、
     * 「戻る」で遷移した際も入力値が保持される。
     * </p>
     *
     * @return 生成したForm
     */
    @ModelAttribute("form")
    public EmployeeAccountForm setUpForm() {
        return new EmployeeAccountForm();
    }

    /**
     * 社員名の選択肢に表示する、アカウント未作成の社員一覧をModelに格納する。
     *
     * <p>
     * このメソッドも{@code @ModelAttribute}が付与されているため、
     * リクエストハンドラメソッドの実行前に毎回呼び出される。
     * 入力画面を表示するどの経路(初回表示・入力エラー・確認画面からの戻る)でも
     * 選択肢が確実に用意される。
     * </p>
     *
     * <p>
     * 画面側が {@code ${employees}} で参照しているため、属性名を "employees" にしている。
     * </p>
     *
     * @return アカウント未作成の社員のリスト
     */
    @ModelAttribute("employees")
    public List<Employee> employees() {
        return employeeAccountService.findEmployeesWithoutAccount();
    }

    /**
     * BP003「担当者アカウント登録(入力)」画面を表示する。
     *
     * @return 入力画面のView名
     */
    @GetMapping("/form")
    public String form() {
        // "admin/account/form" は
        // resources/templates/admin/account/form.html へのフォワードを意味する
        return "admin/account/form";
    }

    /**
     * 入力内容をチェックし、BP004「担当者アカウント登録(確認)」画面を表示する。
     *
     * <p>
     * {@code @Validated}を付与することで、Formに定義したアノテーション
     * (必須・文字数・文字種)による単項目チェックが実行され、
     * 結果が直後の引数{@code bindingResult}に格納される。
     * </p>
     *
     * @param form          入力されたForm(Sessionから取得される)
     * @param bindingResult 入力チェックの結果
     * @param model         Model
     * @return 確認画面のView名。入力チェックエラー時は入力画面のView名
     */
    @PostMapping("/form")
    public String confirm(@Validated @ModelAttribute("form") EmployeeAccountForm form,
            BindingResult bindingResult,
            Model model) {

        // 入力チェックでエラーがある場合の処理（例外シナリオ）
        if (bindingResult.hasErrors()) {
            // 1. Helperを使ってエラーメッセージのリストを作成し、モデルに格納する
            model.addAttribute("errorMessages", employeeAccountHelper.toMessages(bindingResult));

            // 2. 画面のセレクトボックス表示に必要な「社員リスト」を再取得してモデルに格納する
            // AI 意味わからん
            List<Employee> employees = employeeAccountService.findEmployeesWithoutAccount();
            model.addAttribute("employees", employees);

            return "admin/account/form";
        }
        return "admin/account/confirm";
    }

    /**
     * 確認画面の「戻る」「登録」ボタン押下時の処理。
     *
     *
     * @param form               Sessionに保持されている入力内容
     * @param action             押されたボタンを表す値("back" または "register")
     * @param redirectAttributes リダイレクト先へ値を引き継ぐためのオブジェクト
     * @param sessionStatus      Sessionの状態を操作するためのオブジェクト
     * @return 「戻る」の場合は入力画面のView名、「登録」の場合は完了画面へのリダイレクト
     */
    @PostMapping("/confirm")
    public String process(@ModelAttribute("form") EmployeeAccountForm form,
            @RequestParam("action") String action,
            RedirectAttributes redirectAttributes,
            SessionStatus sessionStatus) {

        // 「戻る」の場合(代替フロー)
        // Sessionのオブジェクトを破棄せずに入力画面のView名を返すことで、
        // 入力済みの値が復元された状態で入力画面が表示される
        if ("back".equals(action)) {
            return "admin/account/form";
        }

        // 「登録」の場合
        // Formを登録用のEntityに変換してServiceに渡す。
        // 変換をHelperに任せることで、Service(ドメイン層)がFormに依存しなくなる
        EmployeeAccount employeeAccount = employeeAccountHelper.convert(form);
        employeeAccountService.create(employeeAccount);

        // 完了画面はリダイレクト後のGETで表示するため、
        // 表示に必要な値をフラッシュスコープに格納して引き継ぐ
        // (フラッシュスコープの値はリダイレクト先で1回だけ参照できる)
        redirectAttributes.addFlashAttribute("form", form);

        // 業務が完了したため、Sessionで管理していたオブジェクトの削除を要求する。
        // 削除しないと、次回の登録時に前回の入力値が残ってしまう
        sessionStatus.setComplete();

        // 登録処理はPOSTで受け、表示はGETにリダイレクトする(PRGパターン)。
        // これにより完了画面での再読み込みによる二重登録を防止する
        return "redirect:/admin/account/complete";
    }

    /**
     * BP005「担当者アカウント登録(完了)」画面を表示する。
     *
     * @return 完了画面のView名
     */
    @GetMapping("/complete")
    public String complete() {
        return "admin/account/complete";
    }

    /**
     * 業務例外(アカウント名の重複)が発生した場合の処理。
     *
     * <p>
     * このメソッドが無い場合、業務例外がそのまま画面に伝わり
     * サーバエラー(500)が表示されてしまう。
     * </p>
     *
     * @param e                  発生した業務例外
     * @param redirectAttributes リダイレクト先へ値を引き継ぐためのオブジェクト
     * @return 入力画面へのリダイレクト
     */
    @ExceptionHandler(BusinessException.class)
    public String businessExceptionHandler(BusinessException e,
            RedirectAttributes redirectAttributes) {
        // 画面側が ${errorMessages} のリストで表示する作りのため、リストに変換して渡す
        redirectAttributes.addFlashAttribute("errorMessages", List.of(e.getMessage()));
        return "redirect:/admin/account/form";
    }

}
