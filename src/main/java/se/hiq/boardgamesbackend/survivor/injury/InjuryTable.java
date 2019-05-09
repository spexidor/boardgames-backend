package se.hiq.boardgamesbackend.survivor.injury;

import se.hiq.boardgamesbackend.dice.DiceResult;
import se.hiq.boardgamesbackend.survivor.gear.HitlocationType;

public class InjuryTable {

    public static Injury randomHeadResult(){
        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2: return new Injury("Head Explosion!", HitlocationType.HEAD, true, 0);
            case 3:
            case 4: return new Injury("Decapitation", HitlocationType.HEAD, true, 0);
            case 5: return new Injury("Intracranial hemorrhage", HitlocationType.HEAD, false, 1);
            case 6: return new Injury("Deaf", HitlocationType.HEAD, false, 1);
            case 7: return new Injury("Blind", HitlocationType.HEAD, false, 1);
            case 8: return new Injury("Concussion", HitlocationType.HEAD, false, 1);
            case 9: return new Injury("Shattered jaw", HitlocationType.HEAD, false, 1);
            case 10: return new Injury("Destroyed tooth", HitlocationType.HEAD, false, 1, true);
            default: throw new RuntimeException("Unexpected dice result: " +diceResult.getResult() +"(expected 1-10)");
        }
    }

    public static Injury randomBodyResult(){
        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2: return new Injury("Instant Death", HitlocationType.BODY, true, 0);
            case 3: return new Injury("Bleeding", HitlocationType.BODY, false, 2);
            case 4: return new Injury("Gaping chest wound", HitlocationType.BODY, false, 1);
            case 5: return new Injury("Destroyed back", HitlocationType.BODY, false, 1);
            case 6: return new Injury("Disemboweled", HitlocationType.BODY, false, 1);
            case 7: return new Injury("Raptured spleen", HitlocationType.BODY, false, 2);
            case 8: return new Injury("Broken rib", HitlocationType.BODY, false, 1);
            case 9: return new Injury("Collapsed lung", HitlocationType.BODY, false, 1);
            case 10: return new Injury("Bowled over", HitlocationType.BODY, false, 0, true);
            default: throw new RuntimeException("Unexpected dice result: " + diceResult.getResult() + "(expected 1-10)");
        }
    }

    public static Injury randomArmsResult() {
        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2: return new Injury("Die of shock", HitlocationType.ARMS, true, 0);
            case 3: return new Injury("Bleeding", HitlocationType.ARMS, false, 2);
            case 4: return new Injury("Dismemered arm", HitlocationType.ARMS, false, 1);
            case 5: return new Injury("Raptured muscle", HitlocationType.ARMS, false, 1);
            case 6: return new Injury("Contracture", HitlocationType.ARMS, false, 1);
            case 7: return new Injury("Broken Arms", HitlocationType.ARMS, false, 1);
            case 8: return new Injury("Spiral fracture", HitlocationType.ARMS, false, 1);
            case 9: return new Injury("Dislocated shoulder", HitlocationType.ARMS, false, 1);
            case 10: return new Injury("Hit the dirt", HitlocationType.ARMS, false, 0, true);
            default: throw new RuntimeException("Unexpected dice result: " + diceResult.getResult() + "(expected 1-10)");
        }
    }

    public static Injury randomLegsResult(){
        return randomArmsResult();
    }

    public static Injury randomWaistResult(){
        return randomArmsResult();
    }

    public static Injury randomBrainResult(){
        DiceResult diceResult = new DiceResult(10);

        switch (diceResult.getResult()) {
            case 1:
            case 2: return new Injury("Mortal Terror", HitlocationType.BRAIN, true, 0);
            case 3: return new Injury("Memory Loss", HitlocationType.BRAIN, false, 0); //Lose 2 lv wpn prof
            case 4: return new Injury("Flee", HitlocationType.BRAIN, false, 0);
            case 5:
            case 6: return new Injury("Danger Seizure", HitlocationType.BRAIN, false, 0);
            case 7:
            case 8: return new Injury("Lunacy", HitlocationType.BRAIN, false, 0);
            case 9: return new Injury("New perspective", HitlocationType.BRAIN, false, 0);
            case 10: return new Injury("Frenzy", HitlocationType.BRAIN, false, 0);
            case 11: return new Injury("Maniacal Laughter", HitlocationType.BRAIN, false, 0);
            case 12: return new Injury("Clarity", HitlocationType.BRAIN, false, 0);
            case 13: return new Injury("Impossible!", HitlocationType.BRAIN, false, 0);
            default: throw new RuntimeException("Unexpected dice result: " + diceResult.getResult() + "(expected 1-13)");
        }
    }
}
