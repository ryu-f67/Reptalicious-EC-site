document.getElementById('userForm').addEventListener('submit', function(event) {
  // 各フォーム入力値を取得
  const email = document.querySelector('[name="email"]').value;
  const name = document.querySelector('[name="name"]').value;
  const nickname = document.querySelector('[name="nickname"]').value;
  const phoneNumber = document.querySelector('[name="phoneNumber"]').value;
  const address = document.querySelector('[name="address"]').value;

  // すべてのエラーメッセージをクリア
  const errorElements = document.querySelectorAll('.error-message');
  errorElements.forEach(el => el.textContent = '');
  
  // 入力に不備があるメッセージをクリア
    const registerWorning = document.getElementById('register-worning');
    registerWorning.textContent = '';

  let isValid = true; // フォームの有効性フラグ

  // メールアドレスの条件を確認
  if (email.length > 255) {
    document.getElementById('email_error').textContent = 'メールアドレスは255文字以内で入力してください。';
    isValid = false;
  }

  // 氏名の長さを確認
  if (name.length > 100) {
    document.getElementById('name_error').textContent = '氏名は100文字以内で入力してください。';
    isValid = false;
  }

  // ニックネームの長さを確認
  if (nickname && nickname.length > 10) {
    document.getElementById('nickname_error').textContent = 'ニックネームは10文字以内で入力してください。';
    isValid = false;
  }

  // 電話番号の形式を確認
  if (!/^[0-9]{10,11}$/.test(phoneNumber)) {
    document.getElementById('phoneNumber_error').textContent = '電話番号はハイフンなしで10桁または11桁で入力してください。';
    isValid = false;
  }

  // 住所の長さを確認
  if (address.length > 255) {
    document.getElementById('address_error').textContent = '住所は255文字以内で入力してください。';
    isValid = false;
  }

  // 無効な場合はフォーム送信をキャンセル
  if (!isValid) {
    event.preventDefault();
    window.scrollTo({ top: 0, behavior: 'smooth' });
    registerWorning.textContent = '入力に不備があります';
  }
});

