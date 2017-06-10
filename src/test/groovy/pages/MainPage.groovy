package pages

import geb.Page
import pages.modules.AlertModule
import pages.modules.HeaderModule
import pages.modules.TwitModule

class MainPage extends Page{

    static url = '/'

    static at = { title == 'Twitter' }

    static content = {
        tweets { module(TwitModule) }
        header { module(HeaderModule)}
        alert { module(AlertModule)}
        twitTextArea { $('#tweet-box-home-timeline') }
        btnTweet { $('#page-container .js-tweet-btn') }
    }

    def newTweet(text) {
        twitTextArea.click()
        twitTextArea.singleElement().sendKeys(text)
        btnTweet.click()
        waitFor(5, 0.5) { browser.driver.executeScript('return $.active') == 0}
    }
}