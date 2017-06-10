package ui

import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import pages.LoginPage
import pages.MainPage
import pages.UserFeed
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class TwitterTestSpec extends GebSpec {

//    @Shared Browser browser = new Browser(driver: new ChromeDriver())
    @Shared textOfTweet = 'something'

    def setupSpec() {
//        System.setProperty('geb.build.baseUrl', 'https://twitter.com/')
        getDriver().manage().window().maximize()
        to(LoginPage)
        at(LoginPage)
        loginAs('DronTestius', 'b55rkrgn')
        via(MainPage)
        at(MainPage)
    }
    def cleanupSpec() {
        via(MainPage)
        tweets.deleteByText(textOfTweet)
        browser.quit()
    }

    def "Time Line parse"() {
        setup:
        def expEpoch = ['1496857727000', '1496857682000']
        def expRetweetCount = [0, 0]
        def expText = ['E = mc^2', 'Привет, Твиттер! #мойпервыйТвит']
        to(UserFeed)

        expect:
        tweets.getAllEpoch() == expEpoch
        tweets.getAllRetweetCount() == expRetweetCount
        tweets.getAllText() == expText
    }

    def "tweet something"(){
        when:
        via(MainPage)
        newTweet(textOfTweet)

        then:
        tweets.getAllText().get(0) == textOfTweet
    }

    def "tweet duplication"() {
        setup:
        via(MainPage)
        def duplicateMsg = 'You have already sent this Tweet.'

        when:
        newTweet(textOfTweet)

        then:
        alert.getText() == duplicateMsg
    }

    def "delete tweet"() {
        setup:
        via(MainPage)
        def deleteMsg = 'Your Tweet has been deleted.'

        when:
        tweets.deleteByText(textOfTweet)

        then:
        alert.getText() == deleteMsg
    }
}