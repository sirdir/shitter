import org.testng.annotations.Test;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class BaseTest {

    @Test
    public void test1() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("gzY5BWKxHN5SfBhDgZ5Q28Aqm")
                .setOAuthConsumerSecret("BBEg3mDMqueq5Ao27NswXTHV37gzWhzimbpJCSGXn3GhkMp6kK")
                .setOAuthAccessToken("872191270000750592-sWRY3JaZ6mlnR2Ryc9TCEMsEwhB5qpH")
                .setOAuthAccessTokenSecret("WLjXUrWTXlpJx5f1R6wzZnSFMZWRJEGlqWOXXjW9B2tv7");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
    }
}