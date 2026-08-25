package com.example.fullness.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.service.ProductEditService;
import com.example.fullness.stationary.service.ProductSearchService;

/*
 * UC012「商品修正」Controller
 */

@Controller
@RequestMapping("/admin/product/edit")
@SessionAttributes("form")
public class ProductEditController {

    @Autowired
    private ProductEditService productEditService;

    @Autowired
    private ProductSearchService productSearchService;

    /* 1．BP009 商品修正（入力）画面の表示 */

    @GetMapping("/{productId}")
    public String editForm(@PathVariable("productId") Integer productId, Model model) {
        // Service から Form をもらう
        ProductEditForm form = productEditService.getEditForm(productId);

        List<ProductCategory> categories = productSearchService.getProductCategories();

        model.addAttribute("form", form);
        model.addAttribute("categories", categories);

        return "admin/product/edit_form";
    }

    /* 2．画面から修正された情報を受け取って、BP010 修正（確認）画面へリダイレクト */
    @PostMapping("/{productId}")
    public String postConfirmForm(@PathVariable("productId") Integer productId,
            @Validated @ModelAttribute("form") ProductEditForm form, BindingResult bindingResult, Model model) {

        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            // Formに書いたメッセージをリストにする
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> {
                        if ("typeMismatch".equals(error.getCode())) {
                            if (error.toString().contains("price"))
                                return "正しい価格形式で入力してください";
                            if (error.toString().contains("stock"))
                                return "正しい数量形式で入力してください";
                        }
                        // Formに書いたメッセージを返す
                        return error.getDefaultMessage();
                    })
                    .toList();

            // 集めたエラーメッセージのリストを渡す
            model.addAttribute("errorMessages", errorMessages);

            // ユーザーが入力中だったデータをそのまま画面に戻す
            model.addAttribute("form", form);

            // カテゴリ一覧を渡す
            List<ProductCategory> categories = productSearchService.getProductCategories();
            model.addAttribute("categories", categories);
            // 入力画面に戻す
            return "admin/product/edit_form";
        }
        return "redirect:/admin/product/edit/confirm";
    }

    /* 3．BP010 商品修正（確認）画面の表示 */

    @GetMapping("/confirm")
    public String confirmForm(
            @ModelAttribute("form") ProductEditForm form,
            Model model) {

        // カテゴリ情報を取得（確認画面でカテゴリ名を表示するため）
        ProductCategory category = productEditService.findById(form.getCategoryId());

        // カテゴリ一覧を取得
        List<ProductCategory> categories = productSearchService.getProductCategories();

        // Form内にカテゴリ名を設定
        form.setCategoryName(category.getName());

        // Model に追加
        model.addAttribute("form", form);
        model.addAttribute("categories", categories);

        // 確認画面を表示
        return "admin/product/edit_confirm";
    }

    /* 4．画面から修正された情報を受け取って、BP011 修正（完了）画面へリダイレクト */
    @PostMapping(value = "/confirm", params = "action")
    public String postCompleteForm(
            @ModelAttribute("form") ProductEditForm form,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes redirectAttributes,
            SessionStatus sessionStatus) {

        // Formから商品IDを取り出す
        Integer productId = form.getId();

        // 「戻る」ボタンが押されたときの処理
        if ("back".equals(action)) {
            return "redirect:/admin/product/edit/" + productId;
        }

        // 完了画面で表示する商品名をRedirectAttributesに格納
        redirectAttributes.addFlashAttribute("productName", form.getName());

        // Service で DB 更新処理を実行
        productEditService.updateProduct(productId, form);

        // Session を破棄
        sessionStatus.setComplete();

        // 完了画面へリダイレクト
        return "redirect:/admin/product/edit/complete";
    }

    /* 5．BP011 修正（完了）画面の表示 */
    @GetMapping("/complete")
    public String completeForm() {

        return "admin/product/edit_complete";
    }

    // メソッド5つ用意する

}
