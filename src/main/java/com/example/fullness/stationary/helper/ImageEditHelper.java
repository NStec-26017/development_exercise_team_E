package com.example.fullness.stationary.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/*
 * UC012 「商品修正」Helperクラス
 */

// 注意！！動く確認するためにAIからコピペしただけ！

@Component
public class ImageEditHelper {

    // 💡画像を実際に保存するパソコン（サーバー）上のフォルダパス
    // プロジェクト内の「src/main/resources/static/images/」フォルダを指します
    private final String EDIT_DIR = "src/main/resources/static/images/";

    /**
     * 画面から届いた画像ファイルをフォルダに保存し、DBに保存するための「画像URL（文字列）」を返します。
     */
    public String saveImage(MultipartFile file) throws IOException {

        // 安全対策：ファイルが空っぽ（選択されていない）なら、何もせず終了
        if (file == null || file.isEmpty()) {
            return null;
        }

        // 1. もし保存先のフォルダ（static/images/）がまだパソコン内に無ければ、自動で作る
        Path editPath = Paths.get(EDIT_DIR);
        if (!Files.exists(editPath)) {
            Files.createDirectories(editPath);
        }

        // 2. ユーザーがアップロードした元のファイル名（例: "pencil.jpg"）から、拡張子（".jpg"）を抜き出す
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 3. 【超重要】ファイル名が他のユーザーと被らないように、ランダムな名前（UUID）に変える
        // 例: "a1b2c3d4-e5f6...jpg" という、世界に1つだけの特殊なファイル名に自動変換します
        String newFilename = UUID.randomUUID().toString() + extension;

        // 4. 指定したフォルダの中に、新しいファイル名で画像の中身を丸ごとコピー（保存）する
        Path filePath = editPath.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 5. データベースの `image_url` に保存するための、画面表示用のパス（文字列）を返す
        // 例: "/images/a1b2c3d4-e5f6...jpg"
        return "/images/" + newFilename;
    }
}
