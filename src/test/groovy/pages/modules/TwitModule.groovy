package pages.modules

import geb.Module

class TwitModule extends Module{

    static content = {
        allTweets { $('#stream-items-id>li') }
        retweetBtn { allTweets.$('button.ProfileTweet-actionButton[data-modal="ProfileTweet-retweet"]') }
        allTexts { allTweets.$('p.tweet-text') }
    }
    def getAllEpoch() {
        def epoch = []
        allTweets.$('span._timestamp').forEach({
            nav -> epoch.add(nav.attr('data-time-ms'))
        })
        epoch
    }

    def getAllRetweetCount() {
        def retweetCount = []
        retweetBtn.$('span.ProfileTweet-actionCount').forEach({
            nav ->
                def attr = nav.getAttribute('data-tweet-stat-count')
                attr ? retweetCount.add(attr) : retweetCount.add(0)
        })
        retweetCount
    }

    def getAllRetweetCountRounded() {
        def retweetCountRounded = []
        retweetBtn.$('span.ProfileTweet-actionCountForPresentation').forEach({
            nav -> retweetCountRounded.add(nav.value())
        })
        retweetCountRounded
    }

    def getAllText() {
        def text = []
        allTexts.forEach({
            nav -> text.add(nav.text())
        })
        return text
    }
}