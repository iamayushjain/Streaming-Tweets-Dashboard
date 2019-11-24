# Requirements
- Java - 1.8.x
- Gradle - 3.5+

# Rest APIS
##Search for a keyword
Aim to search tweet from a given keyword such as hashtag or plain text
```http
GET  /tweets/?source=%23IndVsBan
GET  /tweets/?source=elections
```
Request params:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `source` | `string` | **Required**. Your search keyword |

##Search for a specific twitter account
Aim to get tweets for a specific twitter account
```http
GET  /tweets/?source=@ayushjain2911
```
Request params:

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `source` | `string` | **Required**. An specific twitter account |
## Response
API endpoints return the JSON response
```javascript
{
  "statusCode" : int,
  "data" : [{
        "userName" : string,
        "userDisplayName" : string,
        "text" : string
  }]
}
```

Sample Output:

```javascript
{"statusCode":200,"data":[{"userName":"rameo532","userDisplayName":"Ram","text":"RT @mohanstatsman: If India don't bat again in this Test match, they will become the first side in Test history to register four consecutiv…"},{"userName":"SwapnilJMundhe","userDisplayName":"Swapnil J Mundhe","text":"RT @Tanmay6291: Dear @StarSportsIndia team, how much money will it cost the cricket fans to get @sanjaymanjrekar out of commentary box? Ple…"},{"userName":"pawanpandey1996","userDisplayName":"Pawan Kumar pandey","text":"India becomes the FIRST team to win four consecutive Tests by an innings margin.\n\nIndia's last four Tests:\nBeat SA… https://t.co/NNX7k7FRN1"},{"userName":"pawanpandey1996","userDisplayName":"Pawan Kumar pandey","text":"968 balled bowled in the Kolkata #PinkBallTest makes it the shortest Test match hosted by India which had an outrig… https://t.co/sBzylDI0vE"},{"userName":"MirzaranjaRaman","userDisplayName":"Raman Sandhu","text":"RT @RaviShastriOfc: Great pink show in Kolkata. President @SGanguly99 ensured no stone was left unturned. Well done ! #PinkBallTest #India…"},{"userName":"rfDhanvanth","userDisplayName":"Dhanvanth\uD83E\uDD81","text":"RT @RaviShastriOfc: Great pink show in Kolkata. President @SGanguly99 ensured no stone was left unturned. Well done ! #PinkBallTest #India…"},{"userName":"DeviniYashodara","userDisplayName":"Virushka Forever ❣❣","text":"RT @vk_fangirl: Happy Captain @imVkohli\n\uD83D\uDE1A❤️\nSwagstar of the match \uD83D\uDD25❤️\n#INDvsBAN #PinkBallTest https://t.co/HlyNzVmdz2"},{"userName":"vicky_twitz","userDisplayName":"VK","text":"RT @Wriddhipops: Congratulations Team! It was an amazing experience to play this special Test match & to see the number of ppl who turned u…"},{"userName":"dutt_sankar","userDisplayName":"Siddhartha Sankar Dutta","text":"RT @Wriddhipops: Congratulations Team! It was an amazing experience to play this special Test match & to see the number of ppl who turned u…"},{"userName":"vicky_twitz","userDisplayName":"VK","text":"RT @RaviShastriOfc: Great pink show in Kolkata. President @SGanguly99 ensured no stone was left unturned. Well done ! #PinkBallTest #India…"},{"userName":"vicky_twitz","userDisplayName":"VK","text":"RT @Wriddhipops: End of Day 2 #PinkTestBall #IndVsBan https://t.co/LeFqocMGlR"},{"userName":"RNAkkian","userDisplayName":"R.N.Akkian \uD83D\uDE4F\uD83D\uDE4F ( निजामुद्दीन ) \uD83D\uDE4F\uD83D\uDE4F","text":"RT @IrfanPathan: Doesn’t happen often in India when the spinners hardly roll their arm over in the test match. Well done #fastbowlers on wi…"},{"userName":"TweetsMohit","userDisplayName":"Mohit Srivastava","text":"@chintskap Don't know why when I read your tweet I recalled  #SunilGavaskar 's comment yesterday about fast bowling… https://t.co/AR9TF8ubEp"},{"userName":"CSridhar10","userDisplayName":"Sridhar","text":"RT @CricketNDTV: #SanjayManjrekar's comments on #HarshaBhogle's knowledge of cricket angers fans on Twitter\n\n#INDvBAN #INDvsBAN #DayNightTe…"},{"userName":"DrBhushanPatil6","userDisplayName":"Dr.Bhushan Patil","text":"RT @RaviShastriOfc: Great pink show in Kolkata. President @SGanguly99 ensured no stone was left unturned. Well done ! #PinkBallTest #India…"}]}
```

## Status Codes

Response returns the following status codes in its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `SUCCESS` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

# Live Stream Dashboard
## Dashboard
```http
  URL/?source=%23IndVsBan
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `source` | `string` | **Required**. Your search keyword |

## WebSockets in a Spring Boot Application

- `/app/live` - Message Mapping URL
- `/topic/source.{query}` - Message Broker topic for pushing messages to the UI back.

## UI
![App Screenshot](screenshot.png)

## WebSockets Payload
WebSockets pushes the JSON response contains new tweets
```javascript
[
    {
        "userName" : string,
        "userDisplayName" : string,
        "text" : string
    }
]
```
## WebSockets Events
  - annotate `@EventListener` to determine 'onSessionConnected' and 'onDisconnectEvent' and track sessions.
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig {

    @EventListener
    public void onSessionConnected(SessionConnectEvent event) {
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
    }
}
```

### @EventListener Function
###### onSessionConnected
> Track sessionId from `StompHeaderAccessor` class.
> Store sessionId in the `SessionManager`.
###### onDisconnectEvent
> Remove sessionId in the `SessionManager`.
> Terminate thread for the sessionId.


## Unit Testing
- `ApplicationTest` @SpringBootTest annotation tells Spring Boot to go and look for a main configuration class (one with @SpringBootApplication for instance), and use that to start a Spring application context.
- `TweetsServiceImplTest`
###### checkIfTweetsAreOfSameUser
> Validate each tweet by a particular user.
###### checkIfTweetsAreFromDifferentUsers
> Validate non null response for data payload in `BaseResponse`

## SessionManager
HashMap where `SessionId` is key `MaxSinceId` as value. Ideally this service works as standalone entity in distributed system.
Each user query will have different `SessionId`.

### Todo
- Integrate Sliding window to make sure the `getRateLimitStatus()` doesn't overflow.
- Enhance thread logic in web socket live feed controller.
- Add controller test cases.

## Twitter Java Api Examples
- (http://twitter4j.org/en/code-examples.html "Twitter Java Api Examples")
