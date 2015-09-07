package bowling;

public class Frame {

	private final int maxScore;
	
	private Bowl firstBowl = null;
	private Bowl secondBowl = null;
	private Bowl thirdBowl = null; // Needed for last frame
	private Frame previousFrame = null;
	private Frame nextFrame = null;
	
	public Frame(final int maxScore) {
		// TODO: Validate > 0, etc
		this.maxScore = maxScore;
		firstBowl = new Bowl(this, maxScore);
	}

	public Bowl getFirstBowl() {
		return this.firstBowl;
	}
	
	public Frame getPreviousFrame() {
		return previousFrame;
	}

	public Frame getNextFrame() {
		return nextFrame;
	}
	
	public void setPreviousFrame(final Frame frame) {
		this.previousFrame = frame;
	}
	
	public void setNextFrame(final Frame frame) {
		this.nextFrame = frame;
	}
	
	public Integer getScore() {
		Integer score = null;
		if (firstBowl.isStrike() && firstBowl.getNextBowl() != null && firstBowl.getNextBowl().getNextBowl() != null) {
			score = firstBowl.getScore() + firstBowl.getNextBowl().getScore() + firstBowl.getNextBowl().getNextBowl().getScore();
		}
		else if (secondBowl.isSpare() && secondBowl.getNextBowl() != null) {
			score = firstBowl.getScore() + secondBowl.getScore() + secondBowl.getNextBowl().getScore();
		}
		return score;
	}
	
	// TODO: Calculating this is slow but probably fine for 10 frames
	public Integer getRunningTotal() {
		Integer total = null;
		Integer score = getScore();
		if (score != null) {
			total = score;
			if (previousFrame != null && previousFrame.getScore() != null) {
				total += previousFrame.getScore();
			}
		}
		return total;
	}

}
