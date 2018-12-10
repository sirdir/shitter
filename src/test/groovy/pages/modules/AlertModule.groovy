package pages.modules

import geb.Module

class AlertModule extends Module {

    static content = {
        popup { $('#message-drawer') }
        message { $('.message-text') }
    }

    def getText() {
        waitFor() { ['46px', '0px'].contains(popup.css('top')) }
        message.text()
    }
}