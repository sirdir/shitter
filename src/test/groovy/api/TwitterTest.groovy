package api

import com.sun.xml.internal.bind.v2.TODO
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import twitter4j.Status
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TwitterTest extends Specification {

    @Shared twitter

    def setupSpec() {
        def cb = new ConfigurationBuilder()
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("gzY5BWKxHN5SfBhDgZ5Q28Aqm")
                .setOAuthConsumerSecret("BBEg3mDMqueq5Ao27NswXTHV37gzWhzimbpJCSGXn3GhkMp6kK")
                .setOAuthAccessToken("872191270000750592-sWRY3JaZ6mlnR2Ryc9TCEMsEwhB5qpH")
                .setOAuthAccessTokenSecret("WLjXUrWTXlpJx5f1R6wzZnSFMZWRJEGlqWOXXjW9B2tv7")
        TwitterFactory tf = new TwitterFactory(cb.build())
        twitter = tf.getInstance()
        assert twitter
    }

    @Unroll
    def "Time Line parse"() {
        expect:
        def date = new Date().parse('EEE MMM dd HH:mm:ss zzzz yyyy', dateStr)
        st.getCreatedAt() == date
        st.getRetweetCount() == retwit
        st.getText() == text

        where:
        st << twitter.getHomeTimeline()
        dateStr << ['Wed Jun 07 20:48:47 EEST 2017', 'Wed Jun 07 20:48:02 EEST 2017']
        retwit << [0, 0]
        text << ['E = mc^2', 'Привет, Твиттер! #мойпервыйТвит']
    }

    def "Update status"(){
        setup:
        def textOfTwit = 'something'
        def errCode = 403
        def errMsg = 'Status is a duplicate.'

        when:
        Status status = twitter.updateStatus(textOfTwit)

        then:
        status

        when:
        twitter.updateStatus(textOfTwit)

        then:
        def e = thrown(TwitterException)
        e.getStatusCode() == errCode
        e.getErrorMessage() == errMsg

//        cleanup: TODO
    }

    def "Destroy twit"() {
        setup:
        def textOfTwit = 'to delete'

        when:
        def status = twitter.updateStatus(textOfTwit)
        status = twitter.destroyStatus(status.getId())

        then:
        status.getText() == textOfTwit

//        cleanup: TODO
    }
}