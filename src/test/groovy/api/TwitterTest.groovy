package api

import spock.lang.Shared
import spock.lang.Specification
import twitter4j.Status
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

import java.time.LocalDateTime
import java.time.ZoneId

class TwitterTest extends Specification {

    @Shared twitter

    def setup() {
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

    def "Time Line parse"() {
        List<Status> statuses = twitter.getHomeTimeline()
        for (Status st : statuses) {
            LocalDateTime lcdt = LocalDateTime.ofInstant(st.getCreatedAt().toInstant(), ZoneId.systemDefault())
            System.out.println("----------")
            System.out.println(lcdt)
            System.out.println(st.getRetweetCount())
            System.out.println(st.getText())
            System.out.println("----------")
        }
    }

    def "Update status"(){
        def textOfTwit = 'something'

        Status status = twitter.updateStatus(textOfTwit)
        System.out.println("Successfully updated the status to [" + status.getText() + "].")

        try {
            twitter.updateStatus(textOfTwit)
            assert false
        }
        catch (TwitterException e){
            assert e.getStatusCode() == 403
            assert e.getErrorMessage() == 'Status is a duplicate.'
        }
    }

    def "Destroy twit"() {
        def textOfTwit = 'to delete'

        def status = twitter.updateStatus(textOfTwit)

        status = twitter.destroyStatus(status.getId())

        assert status.getText() == textOfTwit
    }
}