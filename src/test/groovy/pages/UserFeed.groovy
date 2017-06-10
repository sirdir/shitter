package pages

import geb.Page
import pages.modules.AlertModule
import pages.modules.HeaderModule
import pages.modules.TwitModule

class UserFeed extends Page{
    static url = 'DronTestius'

    static at = { title == 'Dron Testius (@DronTestius) | Twitter'}

    static content = {
        tweets { module(TwitModule) }
        header { module(HeaderModule)}
        alert { module(AlertModule)}
    }

}