package se.hiq.boardgamesbackend.monster.ai;

import javax.persistence.Entity;

@Entity
public class AIDeck extends Deck {

    public AIDeck(){

        /*
        int monsterLevel = this.getMonster().getLevel();

        //Basic action
        Target a1t1 = new Target(true);
        a1t1.setInFieldOfView(true);
        TargetRule a1t = new TargetRule(a1t1);
        Attack a1a = new Attack(2,2,1);
        AICard a1 =  new AICard("Basic Action", a1t, a1a);

        //Claw
        Target a2t1 = new Target(true, true, true, true);
        Target a2t2 = new Target(true, true);
        a2t2.setInFieldOfView(true);
        TargetRule a2t = new TargetRule(a2t1, a2t2);
        Attack a2a = new Attack(2,2,1);
        AICard a2 =  new AICard("Claw", a2t, a2a);

        //Size up
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
        AICard a3 = new AICard("Size Up", a3t, a3a);

        //Grasp
        Target a4t1 = new Target(true);
        a4t1.setInRange(true);
        a4t1.setKnockedDown(true);
        Target a4t2 = new Target(true);
        a4t2.setInRange(true);
        TargetRule a4t = new TargetRule(a4t1, a4t2);
        Attack a4a = new Attack(1,2,1);
        a4a.setTrigger(new Trigger(true, false));
        TriggerEffect a4te = new TriggerEffect();
        a4te.setDamage(monsterLevel);
        a4te.setGrab(true);
        a4a.setTriggerEffect(a4te);
        AICard a4 = new AICard("Grasp", a4t, a4a);

        this.cardsInDeck.add(a1);
        this.cardsInDeck.add(a2);
        this.cardsInDeck.add(a3);
        this.cardsInDeck.add(a4);
        */
    }
}
