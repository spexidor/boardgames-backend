package se.hiq.boardgamesbackend.survivor.injury;

import se.hiq.boardgamesbackend.dice.DiceResult;

public class InjuryTable {

    public static Injury randomHeadResult(){
        Injury injury;
        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2: injury = new Injury("Head Explosion!", "Head", true, 0);
                    break;
            case 3:
            case 4: injury = new Injury("Decapitation", "Head", true, 0);
                    break;
            case 5: injury = new Injury("Intracranial hemorrhage", "Head", false, 1);
                    break;
            case 6: injury = new Injury("Deaf", "Head", false, 1);
                    break;
            case 7: injury = new Injury("Blind", "Head", false, 1);
                    break;
            case 8: injury = new Injury("Concussion", "Head", false, 1);
                    break;
            case 9: injury = new Injury("Shattered jaw", "Head", false, 1);
                    break;
            case 10: injury = new Injury("Destroyed tooth", "Head", false, 1, true);
                    break;
            default: throw new RuntimeException("Unexpected dice result: " +diceResult.getResult() +"(expected 1-10)");
        }

        return injury;
    }
}
