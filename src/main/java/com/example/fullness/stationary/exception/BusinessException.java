package com.example.fullness.stationary.exception;

/**
 * 例外処理
 *
 * <p>
 * アカウント名の重複など、業務ルールに違反した場合にServiceからスローする
 * </p>
 */
public class BusinessException extends RuntimeException {

    /**
     * メッセージを指定して業務例外を生成する。
     *
     * @param msg エラーメッセージ
     */
    public BusinessException(String msg) {
        super(msg);
    }
}
