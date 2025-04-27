package org.evasive.me.cosmicPrisonsCore.mining.data;

import java.util.LinkedList;
import java.util.Queue;

public class AnimationIds {

    private int nextId = 1;
    private final Queue<Integer> recycledIds = new LinkedList<>();

    public int getUniqueAnimationId() {
        return recycledIds.isEmpty() ? nextId++ : recycledIds.poll();
    }

    public void releaseAnimationId(int id) {
        recycledIds.offer(id);
    }

}
