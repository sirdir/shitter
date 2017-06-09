package ui

import geb.Browser
import geb.driver.CachingDriverFactory
import geb.navigator.Navigator
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.*

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


class TwitterTest extends Specification{

    Browser browser

    def setup() {
        browser = new Browser(driver: new ChromeDriver())
        browser.setBaseUrl('https://twitter.com')
        browser.go()
        browser.$('#signin-email').value('DronTestius')
        browser.$('#signin-password').value('b55rkrgn')
        assert browser.getTitle() == 'Twitter. It\'s what\'s happening.'
//        browser.drive {
//            go('https://twitter.com/')
//            $('#signin-email').value('DronTestius')
//            $('#signin-password').value('b55rkrgn')
//            assert title == 'Twitter. It\'s what\'s happening.'
//        }
    }

    def cleanup() {
        CachingDriverFactory.clearCache()
        browser.quit()
    }

    def "Time Line parse"() {
        when:
        browser.go('/DronTestius')
        Navigator all = browser.$('#stream-items-id>li')
        Navigator times = all.$('span._timestamp')
        println 'xuy'
        println times.size()
        println 'pizda'
//        for (Navigator el : times){
//            println '-----------'
//            println el.attr('data-time-ms')
//            println el.attr('data-time-ms') == LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)*1000
//            println '-----------'
//        }

        then:
        times[0].attr('data-time-ms') == '1496857727000'
        times[1].attr('data-time-ms') == '1496857682000'
        browser.getTitle() == "Geb - Very Groovy Browser Automation" //fixme
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