package com.example.homework.day1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SongCacheImpl implements SongCache{
    static class Song {
        String id;
        AtomicInteger num = new AtomicInteger();

        public Song(String id, int num) {
            this.id = id;
            this.num.set(num);
        }

        public String getId() {
            return id;
        }

        public int getNum() {
            return num.get();
        }
    }

    private final ConcurrentHashMap<String, Integer> songMap = new ConcurrentHashMap<>();
    private final PriorityBlockingQueue<Song> songPQ = new PriorityBlockingQueue<>(10, (s1, s2) -> s2.getNum() - s1.getNum());

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        songMap.put(songId, songMap.getOrDefault(songId, 0) + numPlays);
        Song song = new Song(songId, numPlays);
        songPQ.removeIf(s -> s.getId().equals(songId));
        songPQ.add(song);
    }

    @Override
    public int getPlaysForSong(String songId) {
        return songMap.getOrDefault(songId, -1);
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        LinkedList<String> res = new LinkedList<>();
        PriorityBlockingQueue<Song> copySongPQ = new PriorityBlockingQueue<>(songPQ);
        for (int i = 0; i < n; i++) {
            try {
                res.add(copySongPQ.take().getId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);

        System.out.println(cache.getPlaysForSong("ID-1")); // 4
        System.out.println(cache.getPlaysForSong("ID-9")); // -1
        System.out.println(cache.getTopNSongsPlayed(2)); // ID-3, ID-1
        System.out.println(cache.getTopNSongsPlayed(0)); // null
    }
}



