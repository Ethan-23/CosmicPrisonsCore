package org.evasive.me.cosmicPrisonsCore.mining.data;

public class MiningBlockData {

    private long timeStamp;
    private float progress;
    private int animationId;

    public MiningBlockData (long timeStamp, float progress, int animationId){
        this.progress = progress;
        this.timeStamp = timeStamp;
        this.animationId = animationId;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getAnimationId() {
        return animationId;
    }

    public void setAnimationId(int animationId) {
        this.animationId = animationId;
    }
}
