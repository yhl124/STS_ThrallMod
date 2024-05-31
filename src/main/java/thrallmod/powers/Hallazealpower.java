package thrallmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thrallmod.ThrallMod;

public class Hallazealpower extends AbstractThrallPower{
    public static final String POWER_ID = "Hallazealpower";
    public static final String IMG = "powers/hallazeal.png";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final	String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Hallazealpower(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        //this.amount = amount;
        updateDescription();
        this.img = new Texture(ThrallMod.getResourcePath(IMG));
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
