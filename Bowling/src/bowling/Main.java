package bowling;

public class Main {

	public static int MAX_SCORE = 10;
	
	public static final void main(final String[] args) throws InvalidScoreException {
		Frame frame1 = new Frame(MAX_SCORE);
		Bowl bowl1a = new Bowl(frame1, MAX_SCORE);
		bowl1a.setScore(MAX_SCORE);
		frame1.setFirstBowl(bowl1a);
		
		String f1 = PrintUtilities.frameToString(frame1);
		System.out.println(f1);
		
		Frame frame2 = new Frame(MAX_SCORE);
		Bowl bowl2a = new Bowl(frame2, MAX_SCORE);
		bowl2a.setScore(6);
		frame2.setFirstBowl(bowl2a);
		Bowl bowl2b = new Bowl(frame2, MAX_SCORE - 6);
		bowl2b.setScore(MAX_SCORE - 6);
		bowl2a.setNextBowl(bowl2b);
		bowl2b.setPreviousBowl(bowl2a);
		frame2.setSecondBowl(bowl2b);
		
		String f2 = PrintUtilities.frameToString(frame2);
		System.out.println(f2);
		
		Frame frame3 = new Frame(MAX_SCORE);
		Bowl bowl3a = new Bowl(frame3, MAX_SCORE);
		bowl3a.setScore(6);
		frame3.setFirstBowl(bowl3a);
		Bowl bowl3b = new Bowl(frame3, MAX_SCORE - 6);
		bowl3b.setScore(MAX_SCORE - 6 - 2);
		bowl3a.setNextBowl(bowl3b);
		bowl3b.setPreviousBowl(bowl3a);
		frame3.setSecondBowl(bowl3b);
		
		String f3 = PrintUtilities.frameToString(frame3);
		System.out.println(f3);
		
		
	}
	
}
