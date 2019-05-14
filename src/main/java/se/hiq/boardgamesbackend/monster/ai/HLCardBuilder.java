package se.hiq.boardgamesbackend.monster.ai;

class HLCardBuilder {

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
                return beatsPaw(title);
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
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBrainDamage(monsterLevel);
        Condition condition = new Condition(cardEffect);
        condition.setDiceRolld10(4);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard strangeHand(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBasicAttack(true);
        cardEffect.setKnockDown(true);
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

    private static HLCard beatsPaw(String title) {
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
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsScapularDeltoid(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsChest(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsElbow(String title) {
        HLCard hlCard = new HLCard(title);
        hlCard.setFailureEffect(true);
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }


    private static HLCard beastsFlank(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setWoundEffect();
        CardEffect cardEffect = new CardEffect();
        cardEffect.setPriorityToken(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }
    private static HLCard gloriousMane(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setImpervious();
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
        hlCard.setTrap();
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
        hlCard.setReflexEffect();
        CardEffect cardEffect = new CardEffect();
        Move move = new Move(Direction.FORWARD, true, cardEffect);
        cardEffect.setMove(move);
        cardEffect.setGrab(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsRib(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setWoundEffect();
        CardEffect cardEffect = new CardEffect();
        cardEffect.setGainSurvival(1);
        Condition condition = new Condition(cardEffect, true, false, 0, 3);
        cardEffect.setCondition(condition);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }

    private static HLCard beastsBrow(String title){
        HLCard hlCard = new HLCard(title);
        hlCard.setWoundEffect();
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBrainDamage(1);
        cardEffect.setBasicAttack(true);
        hlCard.setEffect(cardEffect);

        return hlCard;
    }
}