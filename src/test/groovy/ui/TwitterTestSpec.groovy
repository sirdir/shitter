package ui

import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import pages.LoginPage
import pages.MainPage

import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class TwitterTestSpec extends GebSpec {

    @Shared login = browser.config.rawConfig.login
    @Shared password = browser.config.rawConfig.password

    @Shared textOfNewTweet = browser.config.rawConfig.textOfNewTweet
    @Shared epoch = browser.config.rawConfig.epoch
    @Shared retweetCount = browser.config.rawConfig.retweetCount
    @Shared textOfTweets = browser.config.rawConfig.textOfTweets

    def setupSpec() {
        to(LoginPage)
        at(LoginPage)
        loginAs(login, password)
        via(MainPage)
        at(MainPage)
    }
    def cleanupSpec() {
        via(MainPage)
        tweets.deleteByText(textOfNewTweet)
        CachingDriverFactory.clearCacheAndQuitDriver()
    }

    def "Time Line"() {
        setup:
        epoch.eachWithIndex({ it, index -> epoch[index] = it.toString()})
        via(MainPage)

        expect:
        tweets.getAllEpoch() == epoch
        tweets.getAllRetweetCount() == retweetCount
        tweets.getAllText() == textOfTweets
    }

    def "tweet something"(){
        when:
        via(MainPage)
        newTweet(textOfNewTweet)

        then:
        tweets.getAllText().get(0) == textOfNewTweet
    }

    def "tweet duplication"() {
        setup:
        via(MainPage)
        def duplicateMsg = 'You have already sent this Tweet.'

        when:
        newTweet(textOfNewTweet)

        then:
        alert.getText() == duplicateMsg
    }

    def "delete tweet"() {
        setup:
        via(MainPage)
        def deleteMsg = 'Your Tweet has been deleted.'

        when:
        tweets.deleteByText(textOfNewTweet)

        then:
        alert.getText() == deleteMsg
    }
}