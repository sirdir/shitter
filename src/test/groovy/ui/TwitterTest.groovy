package ui

import geb.Browser
import geb.driver.CachingDriverFactory
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.*


class TwitterTest extends Specification{

    Browser browser

    def setup() {
        browser = new Browser(driver: new ChromeDriver())
        browser.setBaseUrl('http://www.gebish.org/')
    }

    def cleanup() {
        CachingDriverFactory.clearCache()
        browser.quit()
    }

    def "Time Line parse"() {
        when:
        browser.go()

        then:
        browser.getTitle() == "Geb - Very Groovy Browser Automation"
    }

    def "twit something"(){
        when:
        browser.go('/manual/current/')

        then:
        browser.$('#driver-quirks').text() == '3.3. Driver quirks'
   }

    def "delete twit"() {

    }
}