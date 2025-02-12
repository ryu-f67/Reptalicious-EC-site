document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('passForm');
  if (form) {
    form.addEventListener('submit', function (event) {
      const password = document.getElementById('password').value;
      const confirmPassword = document.getElementById('confirmPassword').value;
      const messageElement = document.getElementById('password_error');

      messageElement.textContent = ''; // メッセージをリセット

      if (password.length < 8 || password.length > 32) {
        messageElement.textContent = 'パスワードは8～32文字で入力してください';
        event.preventDefault();
        return;
      }

      const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]*$/;
      if (!passwordRegex.test(password)) {
        messageElement.textContent = 'パスワードには、大文字・小文字・数字をそれぞれ1つ以上含めてください';
        event.preventDefault();
        return;
      }

      if (password !== confirmPassword) {
        messageElement.textContent = 'パスワードが一致しません';
        event.preventDefault();
      }
    });
  }
});
