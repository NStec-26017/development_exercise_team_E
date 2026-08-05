package com.example.fullness.stationary.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@Service
public class EmployeeLoginService implements UserDetailsService {

    // MyBatisのRepository（Mapper）をインジェクション
    private final EmployeeAccountRepository repository;

    // コンストラクタ注入
    public EmployeeLoginService(EmployeeAccountRepository repository) {
        this.repository = repository;
    }

    /**
     * ログイン画面で「アカウント名」が入力され、ボタンが押されると
     * Spring Securityによってこのメソッドが自動的に呼び出されます。
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        // 画面から送られてきたアカウント名（name）でDBを検索
        EmployeeAccount account = repository.selectByName(name);

        // アカウントが存在しない場合は、例外をスローして認証を失敗させる
        if (account == null) {
            throw new UsernameNotFoundException("アカウントが見つかりません: " + name);
        }

        // 取得したデータを、Spring Securityが理解できる「UserDetails」型に変換して返却
        return User.withUsername(account.getName()) // ログイン状態として保持する名前
                .password(account.getPassword()) // DBに保存されているハッシュ化済みのパスワード
                .authorities("ROLE_USER") // ユーザー権限（一律でUSER権限を付与）
                .build();
    }
}
