package com.smepro.dto;

import java.util.List;

public class GamificationWidgetDTO {
	private String playerName;
    private int totalXp;
    private int level;
    private int completedQuests;
    private int totalQuests;
    private int overallPercent;
    private List<QuestDTO> quests;
    private String levelTitle;
    // --- Getters & Setters (สร้างเผื่อไว้ให้ครบนะครับ) ---
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public int getTotalXp() { return totalXp; }
    public void setTotalXp(int totalXp) { this.totalXp = totalXp; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getCompletedQuests() { return completedQuests; }
    public void setCompletedQuests(int completedQuests) { this.completedQuests = completedQuests; }
    public int getTotalQuests() { return totalQuests; }
    public void setTotalQuests(int totalQuests) { this.totalQuests = totalQuests; }
    public int getOverallPercent() { return overallPercent; }
    public void setOverallPercent(int overallPercent) { this.overallPercent = overallPercent; }
    public List<QuestDTO> getQuests() { return quests; }
    public void setQuests(List<QuestDTO> quests) { this.quests = quests; }
    public String getLevelTitle() {
		return levelTitle;
	}
	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}


	public static class QuestDTO {
        private String goalName;
        private double currentResult;
        private double targetValue;
        private int percentComplete;
        private int rewardXp;
        private String goalType;
        private String frequency; // เพิ่มบรรทัดนี้
        
        
        // --- Getters & Setters ---
        public String getGoalName() { return goalName; }
        public void setGoalName(String goalName) { this.goalName = goalName; }
        public double getCurrentResult() { return currentResult; }
        public void setCurrentResult(double currentResult) { this.currentResult = currentResult; }
        public double getTargetValue() { return targetValue; }
        public void setTargetValue(double targetValue) { this.targetValue = targetValue; }
        public int getPercentComplete() { return percentComplete; }
        public void setPercentComplete(int percentComplete) { this.percentComplete = percentComplete; }
        public int getRewardXp() { return rewardXp; }
        public void setRewardXp(int rewardXp) { this.rewardXp = rewardXp; }
        public String getGoalType() { return goalType; }
        public void setGoalType(String goalType) { this.goalType = goalType; }
        public String getFrequency() { return frequency; }
        public void setFrequency(String frequency) { this.frequency = frequency; }
    }











	
}
