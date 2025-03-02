# Reptalicious EC Site

このWebアプリは、SpringBootで開発したECサイトです。  
local環境で実装を行っています。

---

## 前提条件
MySQLでデータベースを作成してください。
作成したデータベースの情報を`application.properties`に入力してください。

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[DatabaseName]?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=[UserName]
spring.datasource.password=[Password]
```
[DatabaseName] : 作成したデーターベースの名前  
[UserName] : MySQLのユーザー名  
[Password] : MySQLのパスワード  

---

## 起動
SpringBootを起動し、アプリが動作することを確認してください。

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

## 商品画像の登録
商品に画像を登録する場合は、画像のURLを指定してください。

---

以上でセットアップは完了です。

