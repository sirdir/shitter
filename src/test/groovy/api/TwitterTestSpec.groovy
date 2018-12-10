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

    @Shared String textOfNewTweet
    @Shared epoch
    @Shared retweetCount
    @Shared textOfTweets

    @Shared long id

    def setupSpec() {
        def config = new ConfigSlurper()
                .parse(new File('build/resources/test/GebConfig.groovy').toURI().toURL())
        textOfNewTweet = config.textOfNewTweet
        epoch = config.epoch
        retweetCount = config.retweetCount
        textOfTweets = config.textOfTweets

        def cb = new ConfigurationBuilder()
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(config.cKey)
                .setOAuthConsumerSecret(config.cSecret)
                .setOAuthAccessToken(config.aToken)
                .setOAuthAccessTokenSecret(config.aTokenSecret)
        TwitterFactory tf = new TwitterFactory(cb.build())
        twitter = tf.getInstance()
        assert twitter
    }

    @Unroll
    def "Time Line"() {
        expect:
        def date = new Date(epochExp)
        st.getCreatedAt() == date
        st.getRetweetCount() == retweetExp
        st.getText() == textExp

        where:
        st << twitter.getHomeTimeline()
        epochExp << epoch
        retweetExp << retweetCount
        textExp << textOfTweets
    }

    def "tweet something"() {
        when:
        Status status = twitter.updateStatus(textOfNewTweet)
        id = status.getId()

        then:
        status
        status.getText() == textOfNewTweet
    }

    def "duplicate tweet"() {
        setup:
        def errCode = 403
        def errMsg = 'Status is a duplicate.'

        when:
        twitter.updateStatus(textOfNewTweet)

        then:
        def e = thrown(TwitterException)
        e.getStatusCode() == errCode
        e.getErrorMessage() == errMsg
    }

    def "delete tweet"() {
        when:
        Status status = twitter.destroyStatus(id)

        then:
        status.getText() == textOfNewTweet
    }
}