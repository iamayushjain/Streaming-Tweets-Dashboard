package tweets.service;

import com.stream.tweets.dao.TwitterStatusDao;
import com.stream.tweets.model.Tweets;
import com.stream.tweets.model.TweetsListPayload;
import com.stream.tweets.service.TweetsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import twitter4j.TwitterException;

import static org.mockito.Mockito.when;

public class TweetsServiceImplTest {

    @Mock
    TwitterStatusDao twitterStatusDao;

    @Mock
    TweetsServiceImpl tweetsService;


    @Test
    public void checkIfTweetsAreOfSameUser() throws TwitterException {
        TweetsListPayload tweetsListPayload = null;//get tweetlist object containing real data.

        when(tweetsService.getDataFromTags("@subham2401", 0)).thenReturn(tweetsListPayload);
        int count = tweetsListPayload.getTweetList().size();
        int sameUserCount = 0;
        for (Tweets tweet : tweetsListPayload.getTweetList()) {
            if (tweet.getUserName().equals("subham2401"))
                sameUserCount++;
        }
        Assert.assertEquals(count, sameUserCount);
    }

    @Test
    public void checkIfTweetsAreFromDifferentUSers() throws TwitterException {
        TweetsListPayload tweetsListPayload = null;//get tweetlist object containing real data.
        when(tweetsService.getDataFromTags("india", 0)).thenReturn(tweetsListPayload);
        Assert.assertNotNull(tweetsListPayload.getTweetList());
    }
}
