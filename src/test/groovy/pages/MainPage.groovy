package pages

import geb.Page

class MainPage extends Page{

    static content = {
        newTweet { text ->
            $('#tweet-box-home-timeline').click()
            $('#tweet-box-home-timeline>div').value(text)
            $('#page-container .js-tweet-btn').click()
        }
    }
}