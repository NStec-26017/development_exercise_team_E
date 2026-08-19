package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

/*
* DBから送られてきた情報をもとにアカウント照会するクラス。
*/
@Service
public class EmployeeLoginService implements UserDetailsService {
    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 画面から送られてきたアカウント名（name）でDBを検索
        EmployeeAccount account = employeeAccountRepository.selectByName(name);
        // アカウントが存在しない場合は、例外をスローして認証を失敗させる
        if (account == null) {
            throw new UsernameNotFoundException("アカウントが見つかりません");
        }
        // 取得したデータを、Spring Securityが理解できる「UserDetails」型に変換して返却
        // ログイン状態として保持する名前
        return User.withUsername(account.getName())
                // DBに保存されているハッシュ化済みのパスワード
                .password(account.getPassword())
                // ユーザー権限（一律でUSER権限を付与）
                .authorities("ROLE_USER")
                .build();
    }
}