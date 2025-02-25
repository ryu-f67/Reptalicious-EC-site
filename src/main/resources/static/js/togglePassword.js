document.addEventListener('DOMContentLoaded', function () {
  const passwordField = document.getElementById('password');
  const confirmPasswordField = document.getElementById('confirmPassword');
  const togglePassword = document.getElementById('togglePassword');
  const toggleConfirmPassword = document.getElementById('toggleConfirmPassword');

  if (passwordField && togglePassword) {
    togglePassword.addEventListener('change', () => {
      passwordField.type = togglePassword.checked ? 'text' : 'password';
    });
  }

  if (confirmPasswordField && toggleConfirmPassword) {
    toggleConfirmPassword.addEventListener('change', () => {
      confirmPasswordField.type = toggleConfirmPassword.checked ? 'text' : 'password';
    });
  }
});
