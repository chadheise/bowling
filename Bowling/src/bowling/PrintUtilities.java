package bowling;

public final class PrintUtilities {

	public static String bowlToString(final Bowl bowl) {
		if (bowl == null) {
			return " ";
		} else if (bowl.isStrike()) {
			return "X";
		} else if (bowl.isSpare()) {
			return "/";
		} else if (bowl.getScore() == null) {
			return " ";
		}
		return bowl.getScore().toString();
	}

	public static String frameToString(final Frame frame) {
		StringBuilder b = new StringBuilder();
		// Top line
		b.append("+-+-+");
		b.append("\n");

		// First row
		b.append("|");
		if (frame.getFirstBowl().isStrike()) {
			b.append(" |" + PrintUtilities.bowlToString(frame.getFirstBowl()) + "|");
		} else {
			b.append(PrintUtilities.bowlToString(frame.getFirstBowl()));
			b.append("|");
			if (frame.getFirstBowl().isStrike()) {
				b.append(" ");
			} else {
				b.append(PrintUtilities.bowlToString(frame.getSecondBowl()));
			}
			b.append("|");
		}
		b.append("\n");

		// Middle line
		b.append("| +-+");
		b.append("\n");

		// Bottom row
		b.append("|");
		b.append(totalToString(frame.getRunningTotal()));
		b.append("|");
		b.append("\n");

		// Bottom line
		b.append("+-+-+");
		b.append("\n");

		return b.toString();
	}

	/**
	 * Always returns a string of length 3
	 * 
	 * @param total
	 */
	private static String totalToString(final Integer total) {
		if (total == null) {
			return "   ";
		}
		String totalAsString = total.toString();
		String returnString = totalAsString;
		if (totalAsString.length() == 2) {
			returnString = " " + totalAsString;
		} else if (totalAsString.length() == 1) {
			returnString = "  " + totalAsString;
		}
		return returnString;
	}

}
