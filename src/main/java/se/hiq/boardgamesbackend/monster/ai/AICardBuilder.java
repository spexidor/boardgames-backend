package se.hiq.boardgamesbackend.monster.ai;

public class AICardBuilder {

    public static AICard getCardByName(String title, int monsterLevel){

        switch(title) {

            case "Basic Action":
                Target target = new Target(true);
                target.setInFieldOfView(true);
                TargetRule targetRule = new TargetRule(target);
                Attack attack = new Attack(2, 2, 1);
                AICard aiCard = new AICard(title, targetRule, attack);
            return aiCard;

            case "Claw":
                Target a2t1 = new Target(true, true, true, true);
                Target a2t2 = new Target(true, true);
                a2t2.setInFieldOfView(true);
                TargetRule a2t = new TargetRule(a2t1, a2t2);
                Attack a2a = new Attack(2, 2, 1);
                AICard a2 = new AICard(title, a2t, a2a);
            return a2;

            case "Size Up":
                Target a3t1 = new Target();
                a3t1.setRandom(true);
                a3t1.setInFieldOfView(true);
                TargetRule a3t = new TargetRule(a3t1);
                Attack a3a = new Attack(1, 4, monsterLevel);
                a3a.setIgnoreEvasion(true);
                a3a.setBrainDamage(true);
                a3a.setTrigger(new Trigger(false, true));
                TriggerEffect a3te = new TriggerEffect();
                a3te.setKnockDown(true);
                AICard a3 = new AICard(title, a3t, a3a);
            return a3;

            case "Grasp":
                Target a4t1 = new Target(true);
                a4t1.setInRange(true);
                a4t1.setKnockedDown(true);
                Target a4t2 = new Target(true);
                a4t2.setInRange(true);
                TargetRule a4t = new TargetRule(a4t1, a4t2);
                Attack a4a = new Attack(1, 2, 1);
                a4a.setTrigger(new Trigger(true, false));
                TriggerEffect a4te = new TriggerEffect();
                a4te.setDamage(monsterLevel);
                a4te.setGrab(true);
                a4a.setTriggerEffect(a4te);
                AICard a4 = new AICard(title, a4t, a4a);
            return a4;
            default: return null;
        }
    }
}
