# Reptalicious EC Site

このWebアプリは、SpringBootで開発したECサイトです。  
local環境で実装を行っています。

---
## 環境変数の設定（GitHub Repository Secrets）
---
> [!WARNING]  
> アプリの動作を確認後、`application.properties` の   
> `spring.jpa.hibernate.ddl-auto=create` を `spring.jpa.hibernate.ddl-auto=update` に変更してください。
> 
---

## 初期ログイン情報

デフォルトのログイン情報は以下の通りです。

| 権限  | メールアドレス                       | パスワード  |
|------|-------------------------------|-----------|
| 管理者 | <foo>admin01</foo>@example.jp | password  |
| 一般   | <foo>user**</foo>@example.jp  | password  |

※ `**` には `01~09` のいずれかの数字が入ります。

---

以上でセットアップは完了です。

