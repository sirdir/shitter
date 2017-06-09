package ui

import geb.Browser
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import pages.LoginPage
import pages.MainPage
import pages.UserFeed
import spock.lang.*



//class TwitterTest extends Specification{
//
//    @Shared WebDriver driver
//
//    def setup() {
//        driver = new ChromeDriver() //todo fuckit
//    }
//
//    def cleanup() {
//        driver.quit()
//    }
//
//    def "Time Line parse"() {
//        setup:
//        def expEpoch = ['1496857727000', '1496857682000']
//        def expRetweetCount = [0, 0]
//        def expText = ['E = mc^2', 'Привет, Твиттер! #мойпервыйТвит']
//
//        expect:
//        browser.allEpoch == expEpoch
//        browser.allRetweetCount == expRetweetCount
//        browser.allText == expText
//    }
//
//
//}
class TwitterTest extends GebSpec {

    Browser browser

    def setup() {
        browser = new Browser(driver: new ChromeDriver())
        browser.setBaseUrl('https://twitter.com/')
        to(LoginPage)
        at(LoginPage)
        loginAs('DronTestius', 'b55rkrgn')
        assert browser.getTitle() == 'Твиттер'
    }

    def cleanup() {
        CachingDriverFactory.clearCache()
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

    def "twit something"(){
        setup:
        def textOfTwit = 'something'
        def errMsg = 'You have already sent this Tweet.'
        browser.to(MainPage)
        browser.newTweet(textOfTwit)
//        browser.

        expect:
        browser.allEpoch == ['1496857727000', '1496857682000']

        cleanup:
        browser.$('delete twit')




    }

    def "delete twit"() {
        when:
        browser.to(UserFeed)

        then:
        browser.allEpoch == ['1496857727000', '1496857682000']
    }
}