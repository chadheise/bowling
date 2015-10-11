package bowling;

public class Frame {

	private final int maxScore;
	private final boolean isLastFrame;

	private Bowl firstBowl = null;
	private Bowl secondBowl = null;
	private Bowl thirdBowl = null; // Needed for last frame
	private Frame previousFrame = null;
	private Frame nextFrame = null;

	public Frame(final int maxScore) {
		this(maxScore, false);
	}
	
	public Frame(final int maxScore, final boolean isLastFrame) {
		// TODO: Validate > 0, etc
		this.maxScore = maxScore;
		this.isLastFrame = isLastFrame;
	}

	public int getMaxScore() {
		return maxScore;
	}
	
	public Bowl getFirstBowl() {
		return this.firstBowl;
	}

	public Bowl getSecondBowl() {
		return this.secondBowl;
	}
	
	public Bowl getThirdBowl() {
		return this.thirdBowl;
	}

	public Frame getPreviousFrame() {
		return previousFrame;
	}

	public Frame getNextFrame() {
		return nextFrame;
	}

	public void setFirstBowl(final Bowl bowl) {
		firstBowl = bowl;
	}

	public void setSecondBowl(final Bowl bowl) {
		this.secondBowl = bowl;
	}
	
	public void setThirdBowl(final Bowl bowl) {
		if (!isLastFrame) {
			throw new RuntimeException("Cannot set third bowl on a frame that is not the last");
		}
		this.thirdBowl = bowl;
	}
	
	public void setPreviousFrame(final Frame frame) {
		this.previousFrame = frame;
	}

	public void setNextFrame(final Frame frame) {
		this.nextFrame = frame;
	}

	public Integer getScore() {
		Integer score = null;
		if (firstBowl != null && firstBowl.isStrike() && firstBowl.getNextBowl() != null && firstBowl.getNextBowl().getNextBowl() != null) {
			score = firstBowl.getScore() + firstBowl.getNextBowl().getScore()
					+ firstBowl.getNextBowl().getNextBowl().getScore();
		} else if (secondBowl != null && secondBowl.isSpare() && secondBowl.getNextBowl() != null) {
			score = firstBowl.getScore() + secondBowl.getScore() + secondBowl.getNextBowl().getScore();
		} else if (firstBowl != null && !firstBowl.isStrike() && secondBowl != null && !secondBowl.isSpare()) {
			// TODO: Account for third bowl
			score = firstBowl.getScore() + secondBowl.getScore();
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
