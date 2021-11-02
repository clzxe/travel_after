package com.travel.travel.entity;

public class Score implements Comparable<Score> {
    private String userId;      // 用户标识
    private String itemId;      // 商品标识
    private double score;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    @Override
    public String toString() {
        return "Score [userId=" + userId + ", itemId=" + itemId + ", score="
                + score + "]";
    }
    @Override
    public int compareTo(Score o) {
        if ((this.score - o.score) < 0) {
            return 1;
        }else if ((this.score - o.score) > 0) {
            return -1;
        }else {
            return 0;
        }
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(score);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Score other = (Score) obj;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        if (Double.doubleToLongBits(score) != Double
                .doubleToLongBits(other.score))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
