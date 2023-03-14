Day 1 solution documentation
@Jiawei Lu
-------------------------------------------------------------------------------
SongCacheImpl.java
Implementation of the SongCache Interface.

Content:
1. Inner class Song:
    Store songId and songNum in Song object.
    songNum is defined as AtomicInteger for thread-safe purpose.

2. ConcurrentHashMap<String, Integer> songMap:
    A thread-safe map to store key-value pair of <songId, songNum>
    Update the songMap when called recordSongPlays() method.

3. PriorityBlockingQueue<Song> songPQ:
    A thread-safe PriorityQueue to maintain the most popular song.
    When recording a new Song, we remove the old one in the PriorityQueue
    (if any), and insert the new object.
    Call take() to get the most popular song in the queue.



