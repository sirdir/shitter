package pages

import geb.Page
import pages.modules.TwitModule

class UserFeed extends Page{
    static url = 'DronTestius'

    static content = {
        tweets { module(TwitModule) }
    }

}