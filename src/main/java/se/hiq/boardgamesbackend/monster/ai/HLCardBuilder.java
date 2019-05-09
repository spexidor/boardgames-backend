package se.hiq.boardgamesbackend.monster.ai;

public class HLCardBuilder {

    public static HLCard getCardByName(String title, int monsterLevel, HLDeck deck) {

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
            default: throw new RuntimeException("Card with title " +title +" not found");
        }
    }


    private static HLCard beastsFlank(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setWoundEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setPriorityToken(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }
    private static HLCard gloriousMane(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setImpervious(true);
        return hlCard;
    }
    private static HLCard softBelly(String title){
        return new HLCard(title);
    }
    private static HLCard beastsHeel(String title){
        return new HLCard(title);
    }
    private static HLCard cleverPloy(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setTrap(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }
    private static HLCard fuzzyGroin(String title){
        return new HLCard(title);
    }
    private static HLCard beastsKnee(String title){
        return new HLCard(title);
    }
    private static HLCard beastsFemur(String title){
        return new HLCard(title);
    }
    private static HLCard strainingNeck(String title){
        return new HLCard(title);
    }

    private static HLCard beastsTail(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setReflexEffect(true);
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        cardEffect.setMove(move);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsRib(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setWoundEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setGainSurvival(1);
        Condition condition = new Condition(cardEffect, true, false, 0, 3);
        cardEffect.setCondition(condition);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsBrow(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setWoundEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBrainDamage(1);
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }
}