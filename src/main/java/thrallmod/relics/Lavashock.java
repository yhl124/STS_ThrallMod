package thrallmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thrallmod.powers.OverloadPower;

public class Lavashock extends AbstractThrallRelic
{
    public static final String ID = "Lavashock";
    private static final AbstractRelic.RelicTier TIER = RelicTier.RARE;
    private static final String IMG = "relics/lavashock.png";
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.FLAT;

    public Lavashock()
    {
        super(ID, IMG, TIER, SOUND);
        this.counter = 5;
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw()
    {
        this.flash();
        setCounter(5);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m)
    {
        AbstractPlayer p = AbstractDungeon.player;

        if(card.cardID == "Crackle" || card.cardID == "Ancestralknow" || card.cardID == "Elementaldestruction" || card.cardID == "Forkedlightning" || card.cardID == "Lavaburst"
                || card.cardID == "Lightningbolt" || card.cardID == "Lightningstorm" || card.cardID == "Volcano" || card.cardID == "Doomhammer" || card.cardID == "Earthelemental"
                || card.cardID == "Drakkaridefender" || card.cardID == "Jinyu" || card.cardID == "Fireguard" || card.cardID == "Finderskeepers" || card.cardID == "Beakeredlightning"
                || card.cardID == "Zap" || card.cardID == "Dustdevil" || card.cardID == "Dunemaul")
        {
            if(this.counter > 0 && !p.hasPower("Artifact"))
            {
                flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new OverloadPower(p, -1)));
                this.counter--;
            }
        }
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new Lavashock();
    }
}
