package api

import spock.lang.*
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

@Stepwise
class TwitterTestSpec extends Specification {

    @Shared Twitter twitter
    @Shared textOfTweet = 'something funny'
    @Shared long id

    def setupSpec() {
        def cb = new ConfigurationBuilder()
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey('gzY5BWKxHN5SfBhDgZ5Q28Aqm')
                .setOAuthConsumerSecret('BBEg3mDMqueq5Ao27NswXTHV37gzWhzimbpJCSGXn3GhkMp6kK')
                .setOAuthAccessToken('872191270000750592-sWRY3JaZ6mlnR2Ryc9TCEMsEwhB5qpH')
                .setOAuthAccessTokenSecret('WLjXUrWTXlpJx5f1R6wzZnSFMZWRJEGlqWOXXjW9B2tv7')
        TwitterFactory tf = new TwitterFactory(cb.build())
        twitter = tf.getInstance()
        assert twitter
    }

    @Unroll
    def "Time Line"() {
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

    def "tweet something"(){
        when:
        Status status = twitter.updateStatus(textOfTweet)
        id = status.getId()

        then:
        status
        status.getText() == textOfTweet
    }

    def "duplicate tweet"() {
        setup:
        def errCode = 403
        def errMsg = 'Status is a duplicate.'

        when:
        twitter.updateStatus(textOfTweet)

        then:
        def e = thrown(TwitterException)
        e.getStatusCode() == errCode
        e.getErrorMessage() == errMsg
    }

    def "delete tweet"() {
        when:
        Status status = twitter.destroyStatus(id)

        then:
        status.getText() == textOfTweet
    }
}