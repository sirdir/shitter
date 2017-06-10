import org.openqa.selenium.chrome.ChromeDriver

baseUrl = 'https://twitter.com/'
autoClearCookies = false
driver = {
    newDriver = new ChromeDriver()
    newDriver.manage().window().maximize()
    return newDriver
}