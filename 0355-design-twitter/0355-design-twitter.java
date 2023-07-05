import java.util.*;

class Twitter {

    private final HashMap<Integer, User> userMap = new HashMap<>();
    private static int time = 0;

    public void postTweet(int userId, int tweetId) {
        User user = getUser(userId);
        Tweet tweet = new Tweet(tweetId, ++time, user);
        user.postTweet(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)) return Collections.emptyList();
        User user = getUser(userId);
        user.follow(user);

        PriorityQueue<TweetInfo> pq = new PriorityQueue<>((tweetInfo1, tweetInfo2) -> tweetInfo2.getTweet().getTime() - tweetInfo1.getTweet().getTime());
        for (User followeeUser : user.getFollowingUsers()) {
            Optional<Tweet> tweet = followeeUser.getTweet(0);
            tweet.ifPresent(value -> pq.offer(new TweetInfo(tweet.get(), 0)));
        }

        List<Integer> newsFeed = new ArrayList<>();
        while (!pq.isEmpty()) {
            TweetInfo tweetInfo = pq.poll();
            Tweet tweet = tweetInfo.getTweet();
            newsFeed.add(tweet.getTweetId());
            if (newsFeed.size() == 10) return newsFeed;

            int idx = tweetInfo.getIdx();
            Optional<Tweet> nextTweet = tweet.getUser().getTweet(++idx);
            if (nextTweet.isPresent()) pq.offer(new TweetInfo(nextTweet.get(), idx));
        }

        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {
        User followerUser = getUser(followerId);
        User followeeUser = getUser(followeeId);
        followerUser.follow(followeeUser);
    }

    public void unfollow(int followerId, int followeeId) {
        User followerUser = getUser(followerId);
        User followeeUser = getUser(followeeId);
        followerUser.unfollow(followeeUser);
    }

    private User getUser(int userId) {
        return userMap.computeIfAbsent(userId, User::new);
    }
}

class User {
    private final int userId;
    private final List<Tweet> tweets = new LinkedList<>();
    private final Set<User> followingUsers;

    public User(int userId) {
        this.userId = userId;
        this.followingUsers = new HashSet<>();
    }

    public void postTweet(Tweet tweet) {
        tweets.add(0, tweet);
    }

    public Optional<Tweet> getTweet(int idx) {
        if (idx >= tweets.size()) return Optional.empty();
        return Optional.of(tweets.get(idx));
    }

    public void follow(User user) {
        followingUsers.add(user);
    }

    public void unfollow(User user) {
        followingUsers.remove(user);
    }

    public Set<User> getFollowingUsers() {
        return followingUsers;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof User user)) return false;
        return this.userId == user.userId;
    }

    public int hashCode() {
        return Objects.hash(userId);
    }
}

class Tweet {
    private final int tweetId;
    private final int time;
    private final User user;

    public Tweet(int tweetId, int time, User user) {
        this.tweetId = tweetId;
        this.time = time;
        this.user = user;
    }

    public int getTweetId() {
        return tweetId;
    }

    public int getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }
}

class TweetInfo {
    private final Tweet tweet;
    private final int idx;

    public TweetInfo(Tweet tweet, int idx) {
        this.tweet = tweet;
        this.idx = idx;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public int getIdx() {
        return idx;
    }
}