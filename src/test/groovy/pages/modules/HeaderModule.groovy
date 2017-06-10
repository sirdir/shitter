package pages.modules

import geb.Module

class HeaderModule extends Module{

    static content = {
        popup { $('#global-tweet-dialog') }
        tweetBtn { $('#global-new-tweet-button') }
        twitTextArea { $('#tweet-box-global') }
    }

    void newTweet(text) {
        tweetBtn.click()
        waitFor(5, 0.5) { popup.isDisplayed() }
        twitTextArea.singleElement().sendKeys(text)
        popup.$('.js-tweet-btn').click()
    }
}