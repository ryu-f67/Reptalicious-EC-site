INSERT INTO users (name, nickname, email, password, phone_number, address, role, created_at, updated_at) VALUES
('佐藤 太郎', 'タロー', 'admin01@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08012345678', '東京都千代田区丸の内1-1-1', 'ROLE_ADMIN', '2025-01-10 14:23:45', '2025-01-15 10:45:30'),
('鈴木 花子', 'ハナコ', 'user01@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08023456789', '東京都新宿区西新宿2-2-2', 'ROLE_USER', '2025-01-05 09:12:34', '2025-01-07 13:45:00'),
('高橋 一郎', 'イチロー', 'user02@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08034567890', '大阪府大阪市北区梅田3-3-3', 'ROLE_USER', '2025-01-08 17:30:15', '2025-01-12 08:20:45'),
('田中 直美', 'ナオミ', 'user03@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08045678901', '北海道札幌市中央区北1条西4-4-4', 'ROLE_USER', '2025-01-03 11:45:20', '2025-01-04 16:55:10'),
('伊藤 健太', 'ケンタ', 'user04@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08056789012', '福岡県福岡市博多区博多駅前5-5-5', 'ROLE_USER', '2025-01-14 09:25:10', '2025-01-18 14:30:00'),
('中村 由美', 'ユミ', 'user05@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08067890123', '愛知県名古屋市中区栄6-6-6', 'ROLE_USER', '2025-01-06 10:10:10', '2025-01-10 18:40:25'),
('小林 翔太', 'ショウタ', 'user06@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08078901234', '神奈川県横浜市中区山下町7-7-7', 'ROLE_USER', '2025-01-02 08:50:00', '2025-01-03 12:15:45'),
('山本 美咲', 'ミサキ', 'user07@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08089012345', '京都府京都市中京区烏丸通8-8-8', 'ROLE_USER', '2025-01-12 13:35:20', '2025-01-20 15:25:10'),
('加藤 大輔', 'ダイスケ', 'user08@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08090123456', '広島県広島市中区紙屋町9-9-9', 'ROLE_USER', '2025-01-07 19:20:00', '2025-01-11 09:50:30'),
('松本 彩', 'アヤ', 'user09@example.jp', '$2a$10$7NurUE4kK/xyWxlf2EA62.xu2LFYnZJDMBJR/Y.5IChJEzWrajNda', '08001234567', '沖縄県那覇市久茂地10-10-10', 'ROLE_USER', '2025-01-09 16:40:50', '2025-01-17 12:35:00');

INSERT INTO products (name, description, price, stock, image_url1, image_url2, image_url3, created_at, updated_at) VALUES
('爬虫類ケージ（小型）', '小型の爬虫類に最適な通気性の良いガラス製ケージです。', 4500, 12, 'https://i.gyazo.com/c37b3f15020606049b6ccde7a2519b6d.png', 'https://i.gyazo.com/f89fa19c58018c08ae0fd6a416fbb087.png', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(small)%0Asub2', '2025-01-01 10:00:00', '2025-01-10 10:20:00'),
('爬虫類ケージ（中型）', '中型のトカゲや蛇用の耐久性の高いケージです。', 12000, 8, 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(medium)%0Amain', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(medium)%0Asub1', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(medium)%0Asub2', '2025-01-02 11:00:00', '2025-01-02 12:45:30'),
('爬虫類ケージ（大型）', '大型の爬虫類に十分な広さを提供するケージ。', 22000, 5, 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(large)%0Amain', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(large)%0Asub1', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=Cage(large)%0Asub2', '2025-01-03 12:00:00', '2025-01-05 12:30:00'),
('爬虫類用紫外線ライト', '紫外線B波を放射し、爬虫類の健康をサポートします。', 3000, 20, 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=ultraviolet%20light', NULL, NULL, '2025-01-04 13:00:00', '2025-01-25 13:40:15'),
('保温ライト', '寒い環境でも爬虫類が快適に過ごせるようサポートするライトです。', 2800, 15, 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=thermal%20bulb', NULL, NULL, '2025-01-05 14:00:00', '2025-01-17 14:55:00'),
('エサ用コオロギ（生）', '爬虫類の主食として新鮮なコオロギを提供します。', 800, 30, 'https://i.gyazo.com/b7c7e128963e0ac69dbc257b366d5c76.png', 'https://i.gyazo.com/ba506701d0709646d631f9d2c89921b5.png', NULL, '2025-01-06 15:00:00', '2025-01-06 15:35:25'),
('エサ用ミルワーム', 'タンパク質たっぷりのミルワームで爬虫類の健康を保ちます。', 900, 25, NULL, NULL, NULL, '2025-01-07 16:00:00', '2025-01-11 16:50:10'),
('湿度調整マット', 'ケージ内の湿度を一定に保つマット。脱皮不全対策にも最適です。', 1500, 18, NULL, NULL, NULL, '2025-01-08 17:00:00', '2025-01-09 17:15:40'),
('観葉植物（人工）', 'ケージ内の装飾用に最適な人工観葉植物。', 1200, 22, 'https://i.gyazo.com/9fb0156cbec60e14364f5e1d97f97843.png', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=artificial%20plants%0Asub', NULL, '2025-01-09 18:00:00', '2025-01-21 18:25:00'),
('爬虫類用水入れ（小型）', '小型ケージにぴったりの陶器製水入れ。', 600, 28, 'https://i.gyazo.com/10578387207c61e70808b8a468b8e4c1.png', NULL, NULL, '2025-01-10 19:00:00', '2025-01-18 19:35:30'),
('爬虫類用水入れ（大型）', '大型ケージに最適な深型水入れ。', 800, 20, 'https://i.gyazo.com/79be995d87888181e3a69cef3878141c.png', NULL, NULL, '2025-01-11 20:00:00', '2025-01-18 20:45:15'),
('カルシウムパウダー', '爬虫類の骨の健康を支えるカルシウムサプリメントです。', 1200, 10, NULL, NULL, NULL, '2025-01-12 21:00:00', '2025-01-14 21:20:00'),
('ケージ用背景ボード', '自然な風景を再現する背景ボード。ケージ内装飾用。', 1800, 16, 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=background%20board%0Amain', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=background%20board%0Asub1', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=background%20board%0Asub2', '2025-01-13 22:00:00', '2025-01-13 22:30:30'),
('爬虫類用湿度計', '湿度を簡単に測定できるアナログ式湿度計。', 1000, 14, 'https://i.gyazo.com/c1e61c76f720d516288435ba133e93aa.png', NULL, NULL, '2025-01-14 09:00:00', '2025-01-22 09:50:00'),
('爬虫類用温度計', '温度を正確に測定するデジタル温度計。', 2000, 19, 'https://i.gyazo.com/c1e61c76f720d516288435ba133e93aa.png', NULL, NULL, '2025-01-15 10:00:00', '2025-01-21 10:40:30'),
('爬虫類用ヒーティングマット', '寒冷地や冬季に最適なヒーティングマット。', 3500, 11, NULL, NULL, NULL, '2025-01-16 11:00:00', '2025-01-19 11:25:10'),
('エサ用トング', '爬虫類の餌やりが安全かつ衛生的に行えるトング。', 700, 24, 'https://i.gyazo.com/5552274c9bee115f05d950049a540c29.png', 'https://i.gyazo.com/ba506701d0709646d631f9d2c89921b5.png', NULL, '2025-01-17 12:00:00', '2025-01-17 12:15:00'),
('爬虫類用ケージ掃除セット', 'ケージ掃除用のブラシとスクレーパーのセット。', 1800, 13, 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=cleaning%20set%0Amain', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=cleaning%20set%0Asub1', 'https://placehold.jp/60/c9c9c9/000000/500x500.png?text=cleaning%20set%0Asub2', '2025-01-18 13:00:00', '2025-01-20 13:45:40'),
('爬虫類用隠れ家', '自然な岩を模した隠れ家でストレス軽減に最適。', 2200, 17, 'https://i.gyazo.com/e62b72a6a97ee904339cd867050d1a5b.png', 'https://i.gyazo.com/96d9eeb5c37a343393a9579fbd98736d.png', 'https://i.gyazo.com/74ada8c78590ac4a6d3781dc215d7f4c.png', '2025-01-19 14:00:00', '2025-01-23 14:25:10'),
('爬虫類用エサ入れ（小型）', '小型爬虫類用のコンパクトなエサ入れ。', 500, 30, 'https://i.gyazo.com/bf528d465d944b8de8982d463675ca85.png', NULL, NULL, '2025-01-20 15:00:00', '2025-01-23 15:05:00'),
('爬虫類用エサ入れ（大型）', '大型ケージに最適な深型エサ入れ。', 800, 21, NULL, NULL, NULL, '2025-01-21 16:00:00', '2025-01-21 16:40:30');

 INSERT INTO threads (user_id, report, title, description, display, created_at, updated_at) VALUES
(7, 0, 'ヒョウモントカゲモドキのケージサイズについて', 'ヒョウモントカゲモドキを飼育していますが、適切なケージのサイズについて知りたいです。皆さんはどのくらいのサイズを使っていますか？', 'DISPLAY', '2025-01-05 12:15:30', '2025-01-06 08:45:10'),
(3, 0, '爬虫類用ライトの選び方', '紫外線ライトや保温ライトの選び方が分からず悩んでいます。おすすめの商品があれば教えてください！', 'DISPLAY', '2025-01-08 14:30:00', '2025-01-09 10:20:15'),
(5, 1, 'ヘビが餌を食べないときの対処法', 'コーンスネークを飼っていますが、最近餌を食べなくて心配です。同じような経験をされた方はいらっしゃいますか？', 'DISPLAY', '2025-01-10 16:45:40', '2025-01-11 12:30:25'),
(7, 2, 'ケージ内の湿度管理について', '湿度を適切に保つのが難しいです。特に冬場の湿度管理についてアドバイスをいただきたいです。', 'DISPLAY', '2025-01-12 09:25:10', '2025-01-12 17:10:00'),
(9, 5, '餌用コオロギの管理方法', '餌用コオロギの管理が上手くいかず、すぐに死んでしまいます。長く健康に保つ方法を教えてください。', 'HIDDEN', '2025-01-03 18:40:20', '2025-01-05 11:20:00'),
(2, 0, 'フトアゴヒゲトカゲの成長について', 'フトアゴヒゲトカゲの成長が遅いように感じています。栄養管理や飼育環境で気をつけることはありますか？', 'DISPLAY', '2025-01-07 15:30:45', '2025-01-08 10:15:50'),
(8, 3, '脱皮不全の対処法', 'トカゲが脱皮不全を起こしてしまいました。どのように対処すれば良いでしょうか？', 'DISPLAY', '2025-01-04 13:20:30', '2025-01-06 09:50:10'),
(3, 0, 'ヒョウモントカゲモドキの発色について', 'ヒョウモントカゲモドキの発色をよくするためにはどのようにしたら良いでしょうか？', 'DISPLAY', '2025-01-04 13:20:30', '2025-01-06 09:50:10');

INSERT INTO comments (user_id, thread_id, report, description, display, created_at, updated_at) VALUES
(1, 1, 0, '私のヒョウモントカゲモドキには45cm×30cmのケージを使っています。十分な広さでおすすめですよ。', 'DISPLAY', '2025-01-10 09:20:15', '2025-01-10 09:45:30'),
(2, 1, 1, '60cmケージも検討してみてください。大きいほうが快適です。', 'DISPLAY', '2025-01-11 10:30:20', '2025-01-11 11:00:45'),
(3, 1, 3, 'ケージに観葉植物を入れると見た目も良くなります！', 'DELETED', '2025-01-12 14:15:30', '2025-01-12 14:40:00'),
(4, 1, 0, 'エサ入れと水入れは隅に置くとトカゲが安心します。', 'DISPLAY', '2025-01-13 08:20:40', '2025-01-13 08:45:10'),
(5, 2, 0, '紫外線ライトはReptisunがおすすめです。長寿命で効果的です。', 'DISPLAY', '2025-01-14 16:50:25', '2025-01-14 17:20:50'),
(6, 2, 1, '保温ライトならCeramic Heat Emitterが便利です。', 'DISPLAY', '2025-01-15 18:10:00', '2025-01-15 18:35:30'),
(7, 2, 0, 'ライトの交換頻度は半年に一度くらいが目安です。', 'DISPLAY', '2025-01-16 09:25:10', '2025-01-16 09:50:00'),
(8, 2, 5, '紫外線ライトが切れたらすぐ交換しないと健康に悪影響です。', 'HIDDEN', '2025-01-17 10:30:00', '2025-01-17 11:00:15'),
(9, 3, 0, 'ヘビが餌を食べないときは環境を確認しましょう。ケージの温度が低すぎる場合があります。', 'DISPLAY', '2025-01-18 08:20:15', '2025-01-18 08:45:30'),
(10, 3, 0, 'ストレスが原因のことも多いので、ケージを覆って静かにすると良いですよ。', 'DISPLAY', '2025-01-19 14:10:50', '2025-01-19 14:35:00'),
(1, 3, 0, 'ケージ内の配置を変えるだけでストレス軽減につながることも。', 'DISPLAY', '2025-01-20 09:30:20', '2025-01-20 09:55:10'),
(2, 4, 0, '冬場は湿度を50%以上に保つよう、加湿器を使っています。おすすめです！', 'DISPLAY', '2025-01-21 10:15:30', '2025-01-21 10:40:00'),
(3, 4, 3, '湿度が低いと脱皮不全につながるので注意してください。', 'DISPLAY', '2025-01-22 15:40:00', '2025-01-22 16:10:15'),
(4, 4, 0, '湿度を一定に保つために自動加湿装置を使うのも手です。', 'DISPLAY', '2025-01-23 11:30:50', '2025-01-23 12:00:00'),
(5, 5, 0, 'コオロギは通気性の良いケースに入れて餌を与えると長生きします。', 'DISPLAY', '2025-01-24 09:10:25', '2025-01-24 09:30:45'),
(6, 5, 1, 'コオロギ用の餌として野菜やドッグフードを少量与えると良いですよ。', 'DISPLAY', '2025-01-24 10:20:30', '2025-01-24 10:50:40'),
(7, 5, 5, 'コオロギのケースの掃除頻度は最低週1回です。', 'HIDDEN', '2025-01-24 11:15:10', '2025-01-24 11:40:00'),
(8, 6, 0, 'フトアゴヒゲトカゲにはカルシウムの補給も大事です。餌にカルシウムパウダーを混ぜましょう。', 'DISPLAY', '2025-01-24 12:20:45', '2025-01-24 12:50:00'),
(9, 6, 1, '照明時間を長めにすると食欲が増す場合があります。', 'DISPLAY', '2025-01-24 13:30:15', '2025-01-24 14:00:30'),
(10, 6, 0, '成長期にはバランスの良い食事を与え、ライトの強さも確認しましょう。', 'DISPLAY', '2025-01-24 14:45:20', '2025-01-24 15:10:40'),
(1, 6, 4, '爬虫類用のサプリメントも定期的に与えると良いです。', 'DELETED', '2025-01-24 16:15:30', '2025-01-24 16:40:50'),
(2, 7, 0, '脱皮不全が進むと命に関わることもあるので早めに対処を！', 'DISPLAY', '2025-01-24 17:20:15', '2025-01-24 17:50:30'),
(3, 7, 2, 'トカゲをぬるま湯につけてあげると脱皮を助けることができますよ。', 'DISPLAY', '2025-01-24 18:10:45', '2025-01-24 18:40:10'),
(4, 7, 0, '湿度と温度を適切に保つと脱皮不全が減ります。', 'DISPLAY', '2025-01-24 19:25:30', '2025-01-24 19:50:00');
 
INSERT INTO categories (name, logical_delete_status, created_at) VALUES
('爬虫類用ケージ', 'ACTIVE', '2025-01-05 10:20:30'),
('ライト', 'ACTIVE', '2025-01-08 15:45:10'),
('内装', 'ACTIVE', '2025-01-12 12:30:00'),
('餌用品', 'ACTIVE', '2025-01-03 09:10:20'),
('水容器', 'ACTIVE', '2025-01-06 14:15:50'),
('爬虫類用シェルター', 'ACTIVE', '2025-01-02 18:20:40'),
('床材', 'DELETED', '2025-01-10 16:25:30'),
('温湿調整用品', 'ACTIVE', '2025-01-14 11:40:00'),
('餌', 'ACTIVE', '2025-01-04 08:55:10'),
('初心者向け', 'ACTIVE', '2025-01-07 19:30:25');

INSERT INTO product_category (product_id, category_id, created_at) VALUES
(1, 1, '2025-01-01 10:20:00'),
(1, 10, '2025-01-01 10:20:00'),
(2, 1, '2025-01-02 12:45:30'),
(2, 10, '2025-01-02 12:45:30'),
(3, 1, '2025-01-03 12:30:00'),
(4, 2, '2025-01-04 13:40:15'),
(5, 2, '2025-01-05 14:55:00'),
(6, 9, '2025-01-06 15:35:25'),
(6, 10, '2025-01-06 15:35:25'),
(6, 4, '2025-01-06 15:35:25'),
(7, 4, '2025-01-07 16:50:10'),
(7, 9, '2025-01-07 16:50:10'),
(8, 6, '2025-01-08 17:15:40'),
(8, 8, '2025-01-08 17:15:40'),
(8, 5, '2025-01-08 17:15:40'),
(9, 3, '2025-01-09 18:25:00'),
(10, 5, '2025-01-10 19:35:30'),
(11, 5, '2025-01-13 19:35:30'),
(12, 4, '2025-01-15 19:35:30'),
(12, 10, '2025-01-15 19:35:30'),
(13, 3, '2025-01-18 19:35:30'),
(14, 8, '2025-01-20 19:35:30'),
(14, 10, '2025-01-20 19:35:30'),
(15, 8, '2025-01-21 19:35:30'),
(15, 10, '2025-01-21 19:35:30'),
(16, 8, '2025-01-23 19:35:30'),
(17, 4, '2025-01-23 19:35:30'),
(18, 10, '2025-01-25 19:35:30'),
(19, 3, '2025-01-26 19:35:30'),
(19, 6, '2025-01-26 19:35:30'),
(20, 4, '2025-01-27 19:35:30'),
(21, 4, '2025-01-28 19:35:30');

INSERT INTO orders (user_id, total_price, order_number, shipping_fee, created_at) VALUES
(3, 46600, '20250126-000001', 0, '2025-01-01 11:00:00'),
(2, 11200, '20250126-000002', 0, '2025-01-02 13:00:00'),
(3, 8490, '20250126-000003', 990, '2025-01-03 13:00:00'),
(4, 6790, '20250126-000004', 990, '2025-01-04 14:00:00'),
(5, 12800, '20250126-000005', 0, '2025-01-05 15:00:00'),
(9, 9890, '20250126-000006', 990, '2025-01-06 16:00:00'),
(7, 4790, '20250126-000007', 990, '2025-01-07 17:00:00');


INSERT INTO order_details (order_id, product_id, price, quantity, created_at) VALUES
(1, 1, 4500, 2, '2025-01-01 11:00:00'),
(1, 2, 12000, 3, '2025-01-01 11:30:00'),
(1, 6, 800, 2, '2025-01-01 12:00:00'),
(2, 4, 3000, 2, '2025-01-02 13:00:00'),
(2, 5, 2800, 1, '2025-01-02 13:15:00'),
(2, 6, 800, 3, '2025-01-02 13:45:00'),
(3, 7, 900, 4, '2025-01-03 13:00:00'),
(3, 8, 1500, 1, '2025-01-03 13:10:00'),
(3, 9, 1200, 2, '2025-01-03 13:30:00'),
(4, 10, 600, 1, '2025-01-04 14:00:00'),
(4, 11, 800, 2, '2025-01-04 14:10:00'),
(4, 12, 1200, 3, '2025-01-04 14:30:00'),
(5, 13, 1800, 1, '2025-01-05 15:00:00'),
(5, 14, 1000, 2, '2025-01-05 15:10:00'),
(5, 15, 2000, 1, '2025-01-05 15:20:00'),
(5, 16, 3500, 2, '2025-01-05 15:40:00'),
(6, 17, 700, 5, '2025-01-06 16:00:00'),
(6, 18, 1800, 3, '2025-01-06 16:20:00'),
(7, 19, 2200, 1, '2025-01-07 17:00:00'),
(7, 20, 800, 2, '2025-01-07 17:30:00');

INSERT INTO reviews (user_id, product_id, order_detail_id, rating, description, created_at) VALUES
(3, 6, 3, 5, 'とても使いやすくて満足しています！', '2025-01-02 12:00:00'),
(3, 2, 2, 4, 'コストパフォーマンスが良いと思います。', '2025-01-02 12:15:00'),
(2, 6, 6, 3, NULL, '2025-01-03 14:00:00'),
(2, 5, 5, 5, 'デザインが気に入りました！', '2025-01-03 14:30:00'),
(3, 7, 7, 4, 'もう少し改良の余地がありそうです。', '2025-01-04 15:00:00'),
(3, 8, 8, 3, NULL, '2025-01-04 15:20:00'),
(5, 13, 13, 5, '素晴らしい商品で大満足です！', '2025-01-05 16:00:00'),
(5, 16, 16, 4, NULL, '2025-01-05 16:30:00'),
(9, 17, 17, 5, 'また購入したいと思います。', '2025-01-06 17:00:00'),
(7, 20, 20, 3, '思っていたよりも良かったです。', '2025-01-07 18:00:00');
