package org.evasive.me.cosmicPrisonsCore.mining.process;

public class MiningBlockData {

    private long timeStamp;
    private float progress;
    private float fractureProgress;
    private int animationId;

    public MiningBlockData (long timeStamp, float progress, float fractureProgress, int animationId){
        this.progress = progress;
        this.fractureProgress = fractureProgress;
        this.timeStamp = timeStamp;
        this.animationId = animationId;
    }

    public float getFractureProgress() {
        return fractureProgress;
    }

    public void setFractureProgress(float fractureProgress) {
        this.fractureProgress = fractureProgress;
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
