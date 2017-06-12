import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver

baseUrl = 'https://twitter.com/'
autoClearCookies = false
includeCauseInMessage = true
driver = {
    ChromeDriverManager.getInstance().setup()
    newDriver = new ChromeDriver()
    newDriver.manage().window().maximize()
    return newDriver
}
waiting {
    timeout = 5
    retryInterval = 0.5
}

login = 'DronTestius'
password = 'b55rkrgn'

textOfNewTweet = 'something'
epoch = [1496857727000, 1496857682000]
retweetCount = [0, 0]
textOfTweets = ['E = mc^2', 'Привет, Твиттер! #мойпервыйТвит']

cKey = 'gzY5BWKxHN5SfBhDgZ5Q28Aqm'
cSecret = 'BBEg3mDMqueq5Ao27NswXTHV37gzWhzimbpJCSGXn3GhkMp6kK'
aToken = '872191270000750592-sWRY3JaZ6mlnR2Ryc9TCEMsEwhB5qpH'
aTokenSecret = 'WLjXUrWTXlpJx5f1R6wzZnSFMZWRJEGlqWOXXjW9B2tv7'