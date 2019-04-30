package se.hiq.boardgamesbackend.survivor.injury;

import se.hiq.boardgamesbackend.dice.DiceResult;

public class InjuryTable {

    public static Injury randomHeadResult(){
        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2: return new Injury("Head Explosion!", "Head", true, 0);
            case 3:
            case 4: return new Injury("Decapitation", "Head", true, 0);
            case 5: return new Injury("Intracranial hemorrhage", "Head", false, 1);
            case 6: return new Injury("Deaf", "Head", false, 1);
            case 7: return new Injury("Blind", "Head", false, 1);
            case 8: return new Injury("Concussion", "Head", false, 1);
            case 9: return new Injury("Shattered jaw", "Head", false, 1);
            case 10: return new Injury("Destroyed tooth", "Head", false, 1, true);
            default: throw new RuntimeException("Unexpected dice result: " +diceResult.getResult() +"(expected 1-10)");
        }
    }

    public static Injury randomBodyResult(){
        return randomHeadResult();
    }

    public static Injury randomArmsResult() {

        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2:
                return new Injury("Die of shock", "Arms", true, 0);
            case 3:
                return new Injury("Bleeding", "Arms", false, 2);
            case 4:
                return new Injury("Dismemered arm", "Arms", false, 1);
            case 5:
                return new Injury("Raptured muscle", "Arms", false, 1);
            case 6:
                return new Injury("Contracture", "Arms", false, 1);
            case 7:
                return new Injury("Broken Arms", "Arms", false, 1);
            case 8:
                return new Injury("Spiral fracture", "Arms", false, 1);
            case 9:
                return new Injury("Dislocated shoulder", "Arms", false, 1);
            case 10:
                return new Injury("Hit the dirt", "Arms", false, 0, true);
            default:
                throw new RuntimeException("Unexpected dice result: " + diceResult.getResult() + "(expected 1-10)");
        }
    }

    public static Injury randomLegsResult(){
        return randomArmsResult();
    }

    public static Injury randomWaistResult(){
        return randomArmsResult();
    }

    public static Injury randomBrainResult(){
        return randomArmsResult();
    }
}
