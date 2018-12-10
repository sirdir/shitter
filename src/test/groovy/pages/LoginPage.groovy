package pages

import geb.Page

class LoginPage extends Page {

    static at = {title == "Twitter. It's what's happening."}

    static content = {
        loginInput { $('#signin-email') }
        pwdInput { $('#signin-password') }
        submitBtn { $('button.submit') }
    }
    void loginAs(login, pwd) {
        loginInput.value(login)
        pwdInput.value(pwd)
        submitBtn.click()
    }
}