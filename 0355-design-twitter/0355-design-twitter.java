import java.util.*;

class Twitter {

    private final HashMap<Integer, User> userMap = new HashMap<>();
    private static int time = 0;

    public void postTweet(int userId, int tweetId) {
        User user = getUser(userId);
        Tweet tweet = new Tweet(tweetId, ++time, user);
        user.addTweet(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)) return Collections.emptyList();
        User user = getUser(userId);
        user.follow(user);

        PriorityQueue<Object[]> pq = new PriorityQueue<>((o1, o2) -> ((Tweet) o2[0]).getTime() - ((Tweet) o1[0]).getTime());
        for (User followeeUser : user.getFollowingUsers()) {
            Optional<Tweet> tweet = followeeUser.getTweet(0);
            tweet.ifPresent(value -> pq.offer(new Object[]{value, 0}));
        }

        List<Integer> newsFeed = new ArrayList<>();
        while (!pq.isEmpty()) {
            Object[] entry = pq.poll();
            Tweet tweet = (Tweet) entry[0];
            int idx = (int) entry[1];
            newsFeed.add(tweet.getTweetId());
            if (newsFeed.size() == 10) return newsFeed;

            Optional<Tweet> nextTweet = tweet.getUser().getTweet(++idx);
            if (nextTweet.isPresent()) pq.offer(new Object[]{nextTweet.get(), idx});
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

    public void addTweet(Tweet tweet) {
        tweets.add(0, tweet);
    }

    public Optional<Tweet> getTweet(int i) {
        if (i >= tweets.size()) return Optional.empty();
        return Optional.of(tweets.get(i));
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