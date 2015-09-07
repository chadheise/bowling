package bowling;

public class Bowl {

	private static final int MIN_SCORE = 0;
	
	private Frame frame;
	private Integer score = null;
	private int maxScore;
	private Bowl previousBowl = null;
	private Bowl nextBowl = null;
	
	public Bowl(final Frame frame, final int i) {
		this.frame = frame;
		this.maxScore = i;
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(final Integer value) throws InvalidScoreException {
		validateScore(value);
		score = value;
	}
	
	public Bowl getPreviousBowl() {
		return previousBowl;
	}
	
	public Bowl getNextBowl() {
		return nextBowl;
	}
	
	public void setPreviousBowl(final Bowl bowl) {
		previousBowl = bowl;
	}
	
	public void setNextBowl(final Bowl bowl) {
		nextBowl = bowl;
	}
	
	public boolean isStrike() {
		boolean isStrike = false;
		if ((previousBowl == null) || previousBowl.getFrame() != frame) {
			if (score == maxScore) {
				isStrike = true;
			}
		}
		return isStrike;
	}
	
	public boolean isSpare() {
		boolean isSpare = false;
		if ((previousBowl != null) && (previousBowl.getFrame() == frame) && (!previousBowl.isStrike())) {
			if (score == maxScore) {
				isSpare = true;
			}
		}
		return isSpare;
	}
	
	private void validateScore(final Integer score) throws InvalidScoreException {
		if (score < MIN_SCORE) {
			String errorMessage = "Invalid score of " + score + ". Score cannot be less than " + MIN_SCORE + ".";
			throw new InvalidScoreException(errorMessage);
		}
		if (score > maxScore) {
			String errorMessage = "Invalid score of " + score + ". Score cannot be more than " + maxScore + ".";
			throw new InvalidScoreException(errorMessage);
		}
	}
	
}
