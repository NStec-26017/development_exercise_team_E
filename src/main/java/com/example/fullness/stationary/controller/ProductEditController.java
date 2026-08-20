package com.example.fullness.stationary.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.service.ProductEditService;

/*
 * UC012「商品修正」Controller
 */

@Controller
@RequestMapping("/admin/product")
@SessionAttributes("ProductEditForm")
public class ProductEditController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEditService productEditService;

    // 商品修正（入力）画面
    // 検索画面で「修正」ボタンを変更したとき

    @GetMapping("/edit/{productId}")
    public String showInputPage(@PathVariable("productId") Integer productId, Model model) {

        // ① 検索画面から届いた商品ID(PK)を使って、DBから現在の情報を1件だけ特定して持ってくる！
        Product product = productRepository.selectById(productId);
        if (product == null) {
            // 対象の商品がなければ、商品検索画面へリダイレクトして返す
            return "redirect:/admin/product";
        }

        // ② 【プルダウン用】画面のセレクトボックスに表示するための、全カテゴリマスタデータを取得！
        List<ProductCategory> categoryList = productRepository.selectAllCategories();

        // ③ 取ってきたデータを、画面表示用の Form（大きな伝票）に詰め替えて初期値をセット
        ProductEditForm form = new ProductEditForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        form.setProductCategoryId(product.getProductCategoryId());
        form.setImageUrl(product.getImageUrl()); // 現在登録されている画像のパスを記憶

        // ④ 画面（HTML）へ「商品データ」と「カテゴリ選択肢」を両方手渡す
        model.addAttribute("ProductEditForm", form);
        model.addAttribute("categoryList", categoryList);

        return "admin/product/edit_form"; // ➔ 「商品修正(入力)」画面のHTMLを表示
    }

    /**
     * 【2. 商品修正(確認)画面への遷移】
     * 仕様書イベントフロー3〜4：入力画面で項目を入力して「完了」ボタンを押下したとき
     */
    @PostMapping("/confirm")
    public String goToConfirm(
            @Validated @ModelAttribute("ProductEditForm") ProductEditForm form,
            BindingResult result,
            Model model) {

        // プルダウンの選択肢は、エラーで入力画面に戻る場合でも毎回必ず画面に渡す必要があります
        List<ProductCategory> categoryList = productRepository.selectAllCategories();
        model.addAttribute("categoryList", categoryList);

        // ① 単体バリデーションチェック（必須入力、型、文字数チェックなど）
        if (result.hasErrors()) {
            return "admin/product/edit_form"; // エラーがあれば入力画面に戻す
        }

        // ② カスタムバリデーションチェック（ハッカー対策：選択されたカテゴリIDがマスタに実在するか）
        // Java側で @Param("categoryId") をつけた、小文字の boolean のexistsCategoryがここで大活躍します！
        if (form.getProductCategoryId() != null && !productRepository.existsCategory(form.getProductCategoryId())) {
            result.rejectValue("productCategoryId", "error.invalidCategory", "カテゴリを選択してください");
            return "admin/product/edit_input"; // 存在しないカテゴリなら入力画面に戻す
        }

        return "admin/product/edit_confirm"; // ➔ 「商品修正(確認)」画面のHTMLを表示
    }

    /**
     * 【3. 商品修正(完了)への登録実行処理】
     * 仕様書イベントフロー5〜6：確認画面で「登録」ボタンを押下したとき
     */
    @PostMapping("/complete")
    public String doComplete(
            @ModelAttribute("ProductEditForm") ProductEditForm form,
            SessionStatus sessionStatus) {

        try {
            // ★完成したService（店長）を呼び出して、「画像保存」「商品更新」「在庫更新」を一括実行！
            productEditService.editProductAndStock(form);

        } catch (IOException e) {
            // 万が一画像保存などでエラーが起きたらシステムエラー画面へ
            return "admin/error";
        }

        // ユースケース終了に合わせ、セッションに一時キープしていたFormデータを綺麗にお片付け（破棄）
        sessionStatus.setComplete();

        return "admin/product/edit_complete"; // ➔ 「商品修正(完了)」画面のHTMLを表示
    }

    /**
     * 【代替フロー. 確認画面で「戻る」ボタンを押下したとき】
     * 仕様書代替フロー：戻った後の入力画面は入力した内容が残っている
     */
    @PostMapping(value = "/confirm", params = "back") // th:name="back" のボタンが押されたときに起動
    public String backToInput(@ModelAttribute("ProductEditForm") ProductEditForm form, Model model) {

        // 入力画面に戻る際、プルダウンの選択肢だけはもう一度DBから取ってきて画面に渡してあげる必要があります
        List<ProductCategory> categoryList = productRepository.selectAllCategories();
        model.addAttribute("categoryList", categoryList);

        // @SessionAttributes のおかげで、これだけでユーザーがさっき書いた文字が全部残った状態で画面が開きます！
        return "admin/product/edit_form";
    }

}
