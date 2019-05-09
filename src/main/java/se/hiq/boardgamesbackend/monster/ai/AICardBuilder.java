package se.hiq.boardgamesbackend.monster.ai;

import se.hiq.boardgamesbackend.survivor.gear.HitlocationType;

import java.util.Objects;

class AICardBuilder {

    public static AICard getCardByName(String title, int monsterLevel, AIDeck deck) {
        AICard card = getCardByName(title, monsterLevel);
        Objects.requireNonNull(card).setDeck(deck);
        return card;
    }

    private static AICard getCardByName(String title, int monsterLevel){

        switch(title) {

            case "Basic Action":
                return basicAction(title);

            case "Claw":
                return claw(title);

            case "Size Up":
                return sizeUp(title, monsterLevel);

            case "Grasp":
                return grasp(title);

            case "Revenge":
               return revenge(title);

            case "Bat Around":
                return batAround(title, monsterLevel);

            case "Vicious Claw":
                return viciousClaw(title);

            case "Power Swat":
                return powerSwat(title);

            case "Combo Claw":
                return comboClaw(title);

            case "Chomp":
                return chomp(title);

            default: return null;
        }
    }

    private static AICard chomp(String title) {
        Target target1 = new Target(true, true, true, true);
        Target target2 = new Target(true, true);
        target2.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target1, target2);

        Attack attack = new Attack(1, 2, 1);
        attack.setTargetLocation(HitlocationType.HEAD);

        return new AICard(title, targetRule, attack);
    }

    private static AICard comboClaw(String title) {
        Target target1 = new Target(true, true, true, true);
        Target target2 = new Target(true, true);
        target2.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target1, target2);

        Attack attack = new Attack(2, 4, 1);
        CardEffect cardEffect = new CardEffect();
        cardEffect.setDrawAI(1);
        attack.setCardEffect(cardEffect);

        return  new AICard(title, targetRule, attack);
    }

    private static AICard basicAction(String title){
        Target target = new Target(true);
        target.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target);
        Attack attack = new Attack(2, 2, 1);
        return new AICard(title, targetRule, attack);
    }

    private static AICard claw(String title){
        Target target1 = new Target(true, true, true, true);
        Target target2 = new Target(true, true);
        target2.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target1, target2);
        Attack attack = new Attack(2, 2, 1);
        return  new AICard(title, targetRule, attack);
    }

    private static AICard sizeUp(String title, int monsterLevel){
        Target target = new Target();
        target.setRandom(true);
        target.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target);
        Attack attack = new Attack(1, 4, monsterLevel);
        attack.setIgnoreEvasion(true);
        attack.setTargetLocation(HitlocationType.BRAIN);
        attack.setReach(-1);
        attack.setTrigger(new Trigger(false, true));
        CardEffect cardEffect = new CardEffect();
        cardEffect.setKnockDown(true);
        attack.setCardEffect(cardEffect);
        AICard aiCard = new AICard(title, targetRule, attack);
        aiCard.setNoMove(true);
        return aiCard;
    }

    private static AICard grasp(String title){
        Target target1 = new Target(true);
        target1.setInRange(true);
        target1.setKnockedDown(true);
        Target target2 = new Target(true);
        target2.setInRange(true);
        TargetRule targetRule = new TargetRule(target1, target2);

        Attack attack = new Attack(1, 2, 1);
        attack.setTrigger(new Trigger(true, false));
        CardEffect cardEffect = new CardEffect();
        cardEffect.setGrab(true);
        Move tmpMove = new Move(Direction.AWAY_FROM_THREATS,true, cardEffect);
        cardEffect.setMove(tmpMove);
        attack.setCardEffect(cardEffect);

        return new AICard(title, targetRule, attack);
    }

    private static AICard revenge(String title){
        Target target1 = new Target();
        target1.setLastToWound(true);
        target1.setInRange(true);
        Target target2 = new Target(true, true);
        target2.setInFieldOfView(true);
        TargetRule a5t = new TargetRule(target1, target2);

        Attack attack = new Attack(2, 2, 2);
        attack.setTrigger(new Trigger(true, false));
        CardEffect cardEffect = new CardEffect();
        cardEffect.setGrab(true);
        Move tmpMove5 = new Move(Direction.AWAY_FROM_THREATS,true, cardEffect);
        cardEffect.setMove(tmpMove5);
        attack.setCardEffect(cardEffect);

        return new AICard(title, a5t, attack);
    }

    private static AICard batAround(String title, int monsterLevel){
        Target target1 = new Target(true, true, true, true);
        Target target2 = new Target(true, true);
        target2.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target1, target2);

        Attack attack = new Attack(2, 5, 1);
        attack.setTrigger(new Trigger(true, false));
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBrainDamage(monsterLevel);
        attack.setCardEffect(cardEffect);

        return new AICard(title, targetRule, attack);
    }

    private static AICard viciousClaw(String title){

        Target target = new Target();
        target.setRandom(true);
        target.setInRange(true);
        TargetRule targetRule = new TargetRule(target);

        Attack attack = new Attack(2, 2, 1);
        attack.setTrigger(new Trigger(true, false));
        CardEffect cardEffect = new CardEffect();
        cardEffect.setBleed(1);
        attack.setCardEffect(cardEffect);

        return new AICard(title, targetRule, attack);
    }

    private static AICard powerSwat(String title){

        Target target1 = new Target(true, true, true, true);
        Target target2 = new Target(true, true);
        target2.setInFieldOfView(true);
        TargetRule targetRule = new TargetRule(target1, target2);

        Attack attack = new Attack(1,2,2);
        attack.setTrigger(new Trigger(true, false));
        CardEffect cardEffect = new CardEffect();
        cardEffect.setKnockBack(6);

        attack.setCardEffect(cardEffect);

        return new AICard(title, targetRule, attack);
    }
}
