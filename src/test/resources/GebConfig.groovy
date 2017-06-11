import org.openqa.selenium.chrome.ChromeDriver

baseUrl = 'https://twitter.com/'
autoClearCookies = false
includeCauseInMessage = true
driver = {
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