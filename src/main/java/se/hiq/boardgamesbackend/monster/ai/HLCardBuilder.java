package se.hiq.boardgamesbackend.monster.ai;

import java.util.ArrayList;
import java.util.List;

public class HLCardBuilder {

    static HLCard getCardByName(String title, int monsterLevel, HLDeck deck) {

        HLCard hlCard = getCardByName(title, monsterLevel);
        hlCard.setDeck(deck);
        return hlCard;
    }

    private static HLCard getCardByName(String title, int monsterLevel) {

        switch (title) {

            case "Beast's Tail":
                return beastsTail(title);
            case "Straining Neck":
                return strainingNeck(title);
            case "Beast's Rib":
                return beastsRib(title);
            case "Beast's Brow":
                return beastsBrow(title);
            case "Beast's Flank":
                return beastsFlank(title);
            case "Glorious Mane":
                return gloriousMane(title);
            case "Soft Belly":
                return softBelly(title);
            case "Beast's Heel":
                return beastsHeel(title);
            case "Clever Ploy":
                return cleverPloy(title);
            case "Fuzzy Groin":
                return fuzzyGroin(title);

            case "Beast's Knee":
                return beastsKnee(title);
            case "Beast's Femur":
                return beastsFemur(title);
            case "Beast's Elbow":
                return beastsElbow(title);
            case "Beast's Chest":
                return beastsChest(title);
            case "Beast's Scapular Deltoid":
                return beastsScapularDeltoid(title);
            case "Beast's Back":
                return beastsBack(title);
            case "Fleshy Gut":
                return fleshyGut(title);
            case "Beast's Ear":
                return beastsEar(title);
            case "Beast's Tricep":
                return beastsTricep(title);
            case "Beast's Paw":
                return beastsPaw(title);
            case "Beast's Temple":
                return beastsTemple(title);
            case "Strange Hand":
                return strangeHand(title);
            case "Beast's Maw":
                return beastsMaw(title, monsterLevel);
            default: throw new RuntimeException("Card with title " +title +" not found");
        }
    }

    private static HLCard beastsMaw(String title, int monsterLevel) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        hlCard.setDescription("The Beast roars triumphantly. On 4+, the attacker suffers 1 brain damage per monster level and is knocked down.");
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBrainDamage(monsterLevel);
        Condition condition = new Condition(cardEffect);
        condition.setDiceRolld10(4);
        cardEffect.setCondition(condition);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard strangeHand(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsTemple(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsPaw(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        cardEffect.setAttackExtraDamage(2);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsTricep(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsEar(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.BACKWARDS, false, cardEffect);
        move.setLength(1);
        cardEffect.setMove(move);
        hlCard.setEffect(cardEffect);
        CriticalWound criticalWound = new CriticalWound("The blow damages the White Lion's ear. It is now partially deaf. The White Lions gains -1 Accuracy accuracy token.");
        CardEffect cardEffectCrit =  new CardEffect();
        cardEffectCrit.setMonsterNegativeToken(Token.ACCURACY);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }

    private static HLCard fleshyGut(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsBack(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        hlCard.setDescription("Full move monster forward in a straight line. Cancel all hits now out of range. Any survivor passed over suffers grab.");
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        Condition condition = new Condition(cardEffect);
        condition.setSurvivorPassedOver(true);
        cardEffect.setAffectsAllSurvivors(true);
        cardEffect.setCondition(condition);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("White Lion gains -1 Accuracy token.");
        CardEffect cardEffectCrit = new CardEffect();
        cardEffectCrit.setMonsterNegativeToken(Token.ACCURACY);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }

    private static HLCard beastsScapularDeltoid(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        hlCard.setDescription("Full move monster forward in a straight line. Cancel all hits now out of range. Any survivor passed over suffers grab.");
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        Condition condition = new Condition(cardEffect);
        condition.setSurvivorPassedOver(true);
        cardEffect.setAffectsAllSurvivors(true);
        cardEffect.setCondition(condition);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsChest(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        hlCard.setDescription("Full move monster forward in a straight line. Cancel all hits now out of range. Any survivor passed over suffers grab.");
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        Condition condition = new Condition(cardEffect);
        condition.setSurvivorPassedOver(true);
        cardEffect.setAffectsAllSurvivors(true);
        cardEffect.setCondition(condition);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsElbow(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        hlCard.setDescription("Full move monster forward in a straight line. Cancel all hits now out of range. Any survivor passed over suffers grab.");
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        Condition condition = new Condition(cardEffect);
        condition.setSurvivorPassedOver(true);
        cardEffect.setAffectsAllSurvivors(true);
        cardEffect.setCondition(condition);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        CriticalWound criticalWound = new CriticalWound("The monster howls in pain as the blow breaks the elbow with a sickening crunch. Non deaf survivors gain +3 insanity and may stand if they are knocked down.");
        CardEffect cardEffectCrit = new CardEffect();
        Condition conditionCrit = new Condition(cardEffectCrit);
        conditionCrit.setNonDeaf(true);
        cardEffectCrit.setCondition(conditionCrit);
        cardEffectCrit.setAffectsAllSurvivors(true);
        cardEffectCrit.setSurvivorStand(true);
        cardEffectCrit.setSurvivorGainInsanity(3);

        return hlCard;
    }


    private static HLCard beastsFlank(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("Cats hates this! Attacker gains the priority target token.");
        hlCard.setWoundEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setPriorityToken(true);
        hlCard.setEffect(cardEffect);

        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("White Lion is knocked down.");
        CardEffect cardEffectCrit = new CardEffect();
        cardEffectCrit.setMonsterKnockDown(true);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }
    private static HLCard gloriousMane(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("Impervious hit locations cannot be wounded. A wound or critical wound will not remove an AI card or defeat the monster.");
        hlCard.setImpervious(true);
        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("If the attacker is insane, the sheer frustration grants them power. Gain +1 Strength token.");
        CardEffect cardEffectCrit = new CardEffect();
        Condition condition = new Condition(cardEffectCrit);
        condition.setMinInsanity(3);
        cardEffectCrit.setCondition(condition);
        cardEffectCrit.setSurvivorPositiveToken(Token.STRENGTH);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }
    private static HLCard beastsHeel(String title){

        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("You clip the tense muscle of the heel");
        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("Your precice attack ruins the monster's straining tendon");
        criticalWound.setPersistantInjury(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setName("Ruptured Tendon");
        cardEffect.setDescription("When the White Lion starts it's movement, roll a D10. On a roll of 1, the beast is knocked down");
        cardEffect.setMonsterKnockDown(true);
        cardEffect.setRepeatEveryTurn(true);
        cardEffect.setRepeatOnRoll(1);
        criticalWound.setCardEffect(cardEffect);
        hlCard.setCriticalWound(criticalWound);
        return hlCard;
    }
    private static HLCard softBelly(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("A debilitating blow to the soft belly.");

        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("The White Lion’s intestines hang from the wound on its gut, dragging on the floor. At the start of every turn, before it draws and AI card, it suffers a wound on a roll of 1.");
        criticalWound.setPersistantInjury(true);
        CardEffect cardEffectCrit = new CardEffect();
        cardEffectCrit.setName("Organ Trail");
        cardEffectCrit.setRepeatEveryTurn(true);
        Condition condition = new Condition(cardEffectCrit);
        condition.setDiceRolld10(10);
        cardEffectCrit.setCondition(condition);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }
    private static HLCard cleverPloy(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("The attacker is caught in the White Lion's ruse and is savagely ravaged. Perform Basic Action, targeting the attacker.");
        hlCard.setTrap(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);
        hlCard.setCritable(false); //critable is true by default

        return hlCard;
    }
    private static HLCard fuzzyGroin(String title){

        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("You hit the monster right in the ding dong.");
        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("Your attack destroys the White Lions genitals. The monster is livid. The White Lion gains +1 damage token. The attacker permanently gains the priority target token.");
        CardEffect cardEffect = new CardEffect();
        cardEffect.setMonsterPositiveToken(Token.DAMAGE);
        cardEffect.setPermanentPriorityToken(true);
        cardEffect.setName("Lost Ding Dong");
        criticalWound.setPersistantInjury(true);
        criticalWound.setCardEffect(cardEffect);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }
    private static HLCard beastsKnee(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("You hit the White Lion's sturdy knee cap.");
        CriticalWound criticalWound = new CriticalWound("The White Lions twists unnaturally and shatters. -1 Movement Token added.");
        CardEffect cardEffect = new CardEffect();
        cardEffect.setMonsterNegativeToken(Token.MOVEMENT);
        criticalWound.setCardEffect(cardEffect);
        hlCard.setCriticalWound(criticalWound);
        return hlCard;
    }
    private static HLCard beastsFemur(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("The blow lands on the beast's leg");
        CriticalWound criticalWound = new CriticalWound("You bruise the White Lions femur, crippling its graceful movements. -1 Movement Token added.");
        CardEffect cardEffect = new CardEffect();
        cardEffect.setMonsterNegativeToken(Token.MOVEMENT);
        criticalWound.setCardEffect(cardEffect);
        hlCard.setCriticalWound(criticalWound);
        return hlCard;

    }
    private static HLCard strainingNeck(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("You strike at the monsters throat.");
        CriticalWound criticalWound = new CriticalWound("Random result: 1-9: Paralyzed - The monster is knocked down. 10: The monster cannot breathe. It dies at the start of next turn.");

        HLCardTableResult hlCardTableResult = new HLCardTableResult();
        List<CardEffect> cardEffects = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        CardEffect cardEffect1 = new CardEffect();
        CardEffect cardEffect2 = new CardEffect();
        cardEffect1.setMonsterKnockDown(true);
        cardEffect1.setDescription("The monster is knocked down.");
        cardEffect2.setMonsterDiesNextTurn(true);
        cardEffect2.setDescription("The monster cannot breathe! It dies at the end of the next turn.");

        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(0);
        indexList.add(1);

        cardEffects.add(cardEffect1);
        cardEffects.add(cardEffect2);
        hlCardTableResult.setCardEffects(cardEffects);
        hlCardTableResult.setTableIndexes(indexList);
        criticalWound.setHlCardTableResult(hlCardTableResult);

        hlCard.setCriticalWound(criticalWound);
        return hlCard;
    }

    private static HLCard beastsTail(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setReflexEffect(true);
        hlCard.setDescription("Full move monster forward in a straight line. Cancel all hits now out of range. Any survivor passed over suffers grab.");
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        Condition condition = new Condition(cardEffect);
        condition.setSurvivorPassedOver(true);
        cardEffect.setCondition(condition);
        cardEffect.setAffectsAllSurvivors(true);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("The tails is ruined. White Lion gains -1 Accuracy token.");
        CardEffect cardEffectCrit = new CardEffect();
        cardEffectCrit.setMonsterNegativeToken(Token.ACCURACY);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }

    private static HLCard beastsRib(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("If the attacker has 3+ understanding, the sound of cracking ribs is encouraging and the attacker gains +1 survival.");
        hlCard.setWoundEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setSurvivorGainSurvival(1);
        Condition condition = new Condition(cardEffect, true, false, 0, 3);
        cardEffect.setCondition(condition);
        hlCard.setEffect(cardEffect);

        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("The force of the blow breaks the Whit Lion’s ribs, weakening it. White Lion gains -1 Toughness token.");

        CardEffect cardEffectCrit = new CardEffect();
        cardEffectCrit.setMonsterNegativeToken(Token.TOUGHNESS);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }

    private static HLCard beastsBrow(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setDescription("Snarling, the monster swats at its attacker. Attackers suffers 1 brain damage. Perform Basic Action, target the attacker.");
        hlCard.setWoundEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBrainDamage(1);
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);
        CriticalWound criticalWound = new CriticalWound();
        criticalWound.setDescription("The White Lion’s vision is impaired. The White Lion gains -1 Accuracy token.");
        CardEffect cardEffectCrit = new CardEffect();
        cardEffectCrit.setMonsterNegativeToken(Token.ACCURACY);
        criticalWound.setCardEffect(cardEffectCrit);
        hlCard.setCriticalWound(criticalWound);

        return hlCard;
    }
}