async function checkLoginStatus() {
    const loginMessageElement = document.getElementById("login-container");
    const response = await fetch('/login');
    const text = await response.text();
    loginMessageElement.innerHTML = text;
}