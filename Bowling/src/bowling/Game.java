package bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

	private static final int DEFAULT_NUM_FRAMES = 10;
	private static final int DEFAULT_NUM_PINS = 10;

	private final int numFrames;
	private final int numPins;
	private final List<String> players = new ArrayList<String>();
	private final Map<String, Frame[]> games = new HashMap<String, Frame[]>();

	private int currentBowl = 0; // The bowl number within the frame (0 indexed)
	private int currentPlayer = 0; // Index of the current player
	private int currentFrame = 0; // The current frame number (0 indexed)

	public Game() {
		this(DEFAULT_NUM_FRAMES, DEFAULT_NUM_PINS);
	}

	public Game(final int numFrames, final int numPins) {
		this.numFrames = numFrames;
		this.numPins = numPins;
	}

	public void addPlayer(String playerName) {
		if (players.contains(playerName)) {
			// TODO: Do something since player already exists
		}

		players.add(playerName);
		Frame[] frames = new Frame[numFrames];
		games.put(playerName, frames);
		frames[0] = new Frame(numPins);
		for (int i = 1; i < numFrames; i++) {
			frames[i] = new Frame(numPins);
			frames[i - 1].setNextFrame(frames[i]);
			frames[i].setPreviousFrame(frames[i - 1]);
		}
	}

	/**
	 * Knock down the provided number of pins
	 * 
	 * @param pins
	 *            the number of pins knocked down
	 * @throws InvalidScoreException
	 */
	public void bowl(final int pins) throws InvalidScoreException {
		Frame frame = games.get(currentPlayer)[currentFrame];

		Bowl bowl = frame.getFirstBowl();
		if (currentBowl == 1) {
			bowl = bowl.getNextBowl();
		} else if (currentBowl == 2) {
			bowl = bowl.getNextBowl().getNextBowl();
		}

		bowl.setScore(pins);

		if (currentFrame != numFrames) { // If it is not the last frame
			// If it was a strike or the second throw of the frame
			if (bowl.isStrike() || currentBowl >= 1) {
				currentBowl = 0;
				updateFrame();
				updatePlayer();
			} else {
				currentBowl++;
				// Player and frame remain the same
			}

		} else { // If it is the last frame
			// If it was the third throw of the frame
			if (currentBowl >= 2) {
				currentBowl = 0;
				updateFrame();
				updatePlayer();
			} else if (bowl.isStrike()
					|| bowl.isSpare()
					|| (bowl.getPreviousBowl() != null && bowl
							.getPreviousBowl().isStrike())) {
				currentBowl++;
			}

		}

	}

	private void updateFrame() {
		// If the player is the last player
		if (currentPlayer + 1 >= players.size()) {
			currentFrame++; // Move to the next frame
		}
		// Otherwise, the frame stays the same
	}

	private void updatePlayer() {
		currentPlayer++;
		if (currentPlayer >= players.size()) {
			currentPlayer = 0;
		}
	}

}
