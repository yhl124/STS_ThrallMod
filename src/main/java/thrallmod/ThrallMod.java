package thrallmod;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Map;
/////card
import thrallmod.cards.*;
////////relics
import thrallmod.potions.KeyPotion;
import thrallmod.relics.Lavashock;
import thrallmod.relics.Omegamind;
import thrallmod.relics.Totem;
import thrallmod.relics.Totemicslam;

import thrallmod.patches.AbstractCardEnum;
import thrallmod.patches.ThrallEnum;
import thrallmod.characters.ThrallCharacter;



@SpireInitializer
public class ThrallMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, PostDrawSubscriber,OnStartBattleSubscriber,PreMonsterTurnSubscriber
{
    public static final Logger logger = LogManager.getLogger(ThrallMod.class);

    private static final String MODNAME = "TharllMod";
    private static final String AUTHOR = "PSTCO";
    private static final String DESCRIPTION = "v1.0\n Adds The Thrall";
    private static final Color THRALL_COLOR = CardHelper.getColor(48.0f, 52.0f, 97.0f);
    private static final String ASSETS_FOLDER = "images";

    private static final String ATTACK_CARD = "images/512/bg_attack_thrall.png";
    private static final String SKILL_CARD = "images/512/bg_skill_thrall.png";
    private static final String POWER_CARD = "images/512/bg_power_thrall.png";
    private static final String ENERGY_ORB = "images/512/card_thrall_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "images/1024/bg_attack_thrall.png";
    private static final String SKILL_CARD_PORTRAIT = "images/1024/bg_skill_thrall.png";
    private static final String POWER_CARD_PORTRAIT = "images/1024/bg_power_thrall.png";
    private static final String ENERGY_ORB_PORTRAIT = "images/1024/card_thrall_orb.png";

    public static final String CARD_ENERGY_ORB = "images/char/energyOrb.png";
    private static final String CHAR_BUTTON = "images/charSelect/button.png";
    private static final String CHAR_PORTRAIT = "images/charSelect/portrait.png";

    public static final String CHAR_SHOULDER_1 = "images/char/shoulder.png";
    public static final String CHAR_SHOULDER_2 = "images/char/shoulder2.png";
    public static final String CHAR_CORPSE = "images/char/corpse.png";

    public static Gson gson;
    private static Map<String, Keyword> keywords;
    public static final String BADGE_IMG = "images/badge.png";

    private static final String CARD_STRING = "localization/eng/card-strings.json";
    private static final String RELIC_STRING = "localization/eng/relic-strings.json";
    private static final String POWER_STRING= "localization/eng/power-strings.json";
    private static final String KEYWORD_STRING= "localization/eng/keyword-strings.json";
    private static final String CARD_STRING_KOR = "localization/kor/card-strings.json";
    private static final String RELIC_STRING_KOR = "localization//kor/relic-strings.json";
    private static final String POWER_STRING_KOR = "localization/kor/power-strings.json";
    private static final String KEYWORD_STRING_KOR = "localization/kor/keyword-strings.json";
    private static final String POTION_STRING = "localization/eng/potion-strings.json";
    private static final String POTION_STRING_KOR = "localization/kor/potion-strings.json";

    public static final String getResourcePath(String resource)
    {
        return ASSETS_FOLDER + "/" + resource;
    }

    public ThrallMod()
    {
        BaseMod.subscribe(this);
        logger.info("creating the color : THRALL_COLOR");
        BaseMod.addColor(AbstractCardEnum.THRALL,
                THRALL_COLOR, THRALL_COLOR, THRALL_COLOR, THRALL_COLOR, THRALL_COLOR, THRALL_COLOR, THRALL_COLOR,
                ATTACK_CARD, SKILL_CARD, POWER_CARD, ENERGY_ORB, ATTACK_CARD_PORTRAIT, SKILL_CARD_PORTRAIT, POWER_CARD_PORTRAIT, ENERGY_ORB_PORTRAIT, CARD_ENERGY_ORB);
    }

    public static void initialize()
    {
        logger.info("Initializing Thrall Mod");
        new ThrallMod();
    }

    @Override
    public void receivePostInitialize()
    {
        Texture badgeTexture = new Texture(BADGE_IMG);
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("No settings", 400.0f, 700.0f, settingsPanel, (me) -> {}));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        BaseMod.addPotion(KeyPotion.class, Color.GRAY, Color.PURPLE, Color.MAROON, KeyPotion.POTION_ID);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }

    @Override
    public void receiveEditCards()
    {
        //BASIC
        BaseMod.addCard(new Strike_Thrall());
        BaseMod.addCard(new Defend_Thrall());
        BaseMod.addCard(new Basicattack());
        BaseMod.addCard(new Basicstrength());
        BaseMod.addCard(new Basicheal());
        BaseMod.addCard(new Basicarmor());
        BaseMod.addCard(new Thestormguardian());
        //Attack-COMMON
        BaseMod.addCard(new Lightningbolt());
        BaseMod.addCard(new Earthshock());
        BaseMod.addCard(new Frostshock());
        BaseMod.addCard(new Forkedlightning());
        BaseMod.addCard(new Fireelemental());
        BaseMod.addCard(new Jadelightning());
        BaseMod.addCard(new Tidalsurge());
        BaseMod.addCard(new Beakeredlightning());
        BaseMod.addCard(new Zap());
        BaseMod.addCard(new Totemicsmash());
        BaseMod.addCard(new Dustdevil());
        //Attack-UNCOMMON
        BaseMod.addCard(new Lightningstorm());
        BaseMod.addCard(new Crackle());
        BaseMod.addCard(new Murksparkeel());
        BaseMod.addCard(new Avalanche());
        BaseMod.addCard(new Maelstromportal());
        BaseMod.addCard(new Volcano());
        BaseMod.addCard(new Airelemental());
        BaseMod.addCard(new Dunemaul());
        BaseMod.addCard(new Likkim());
        BaseMod.addCard(new Snowfurygiant());
        BaseMod.addCard(new Wartbringer());
        //Attack-RARE
        BaseMod.addCard(new Elementaldestruction());
        BaseMod.addCard(new Lavaburst());
        BaseMod.addCard(new Alakir());
        BaseMod.addCard(new Whiteeyes());
        //Skill-COMMON
        BaseMod.addCard(new Elementaryreaction());
        BaseMod.addCard(new Healingrain());
        BaseMod.addCard(new Drakkaridefender());
        BaseMod.addCard(new Rockbiterweapon());
        BaseMod.addCard(new Jadecheiftain());
        BaseMod.addCard(new Witchsapprentice());
        BaseMod.addCard(new Jinyu());
        BaseMod.addCard(new Menacingnimbus());
        BaseMod.addCard(new Thecoin());
        //Skill-UNCOMMON
        BaseMod.addCard(new Eureka());
        BaseMod.addCard(new Healingwave());
        BaseMod.addCard(new Hex());
        BaseMod.addCard(new Devolve());
        BaseMod.addCard(new Farsight());
        BaseMod.addCard(new Cryostasis());
        BaseMod.addCard(new Earthelemental());
        BaseMod.addCard(new Reincarnate());
        BaseMod.addCard(new ThingfromBelow());
        BaseMod.addCard(new Fireguard());
        //BaseMod.addCard(new Finderskeepers());
        BaseMod.addCard(new Evolve());
        BaseMod.addCard(new Blazing());
        BaseMod.addCard(new Ancestralknow());
        //Skill-RARE
        BaseMod.addCard(new Eternalsentinel());
        BaseMod.addCard(new Shudderwock());
        BaseMod.addCard(new Unstableevolution());
        BaseMod.addCard(new Thestormbringer());
        //Power-UNCOMMON
        BaseMod.addCard(new Manatidetotem());
        BaseMod.addCard(new Flametonguetotem());
        BaseMod.addCard(new Doomhammer());
        BaseMod.addCard(new Vitalitytotem());
        BaseMod.addCard(new Rumblingelemental());
        BaseMod.addCard(new Spiritofthefrog());
        //Power-RARE
        BaseMod.addCard(new Hallazeal());
        BaseMod.addCard(new Tunneltrogg());
        BaseMod.addCard(new Therunespear());
        BaseMod.addCard(new Hagatha());
        BaseMod.addCard(new Thralldeathseer());
    }

    @Override
    public void receiveEditStrings()
    {
        logger.info("start editing strings");
        String relicStrings, cardStrings, powerStrings, potionStrings, keywordStrings,
                relic, card, power, potion, keyword;

        if (Settings.language == Settings.GameLanguage.KOR)
        {
            logger.info("lang == kor");
            card = CARD_STRING_KOR;
            relic = RELIC_STRING_KOR;
            power = POWER_STRING_KOR;
            //keyword = KEYWORD_STRING_KOR;
            potion = POTION_STRING_KOR;
        }
        else {
            logger.info("lang == eng");
            card = CARD_STRING;
            relic = RELIC_STRING;
            power = POWER_STRING;
            //keyword = KEYWORD_STRING;
            potion = POTION_STRING;
        }

        relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

        //keywordStrings = Gdx.files.internal(keyword).readString(String.valueOf(StandardCharsets.UTF_8));
        //BaseMod.loadCustomStrings(KeywordStrings.class, keywordStrings);

        potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
    }

    @Override
    public void receiveEditCharacters()
    {
        BaseMod.addCharacter(new ThrallCharacter("Thrall"),
                CHAR_BUTTON,
                CHAR_PORTRAIT,
                ThrallEnum.THRALL);
    }

    @Override
    public void receiveEditKeywords()
    {
        String[] overload = {"과부하"};
        BaseMod.addKeyword(overload, "다음 턴에 얻는 에너지가 감소합니다.");

        String[] overload_e = {"Overload", "overload"};
        BaseMod.addKeyword(overload_e, "You have less energy next turn.");
        /*
        Gson gson = new Gson();

        String keywordStrings = Gdx.files.internal("localization/kor/keyword-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Type typeToken = new TypeToken<Map<String, Keyword>>() {}.getType();

        keywords = (Map)gson.fromJson(keywordStrings, typeToken);

        keywords.forEach((k,v)->{
            // Keyword word = (Keyword)v;
            logger.info("ThrallMod: Adding Keyword - " + v.NAMES[0]);
            BaseMod.addKeyword(v.NAMES, v.DESCRIPTION);
        });
        */
    }

    @Override
    public void receiveEditRelics()
    {
        RelicLibrary.add(new Totem());
        BaseMod.addRelicToCustomPool(new Totemicslam(), AbstractCardEnum.THRALL);
        BaseMod.addRelicToCustomPool(new Lavashock(), AbstractCardEnum.THRALL);
        BaseMod.addRelicToCustomPool(new Omegamind(), AbstractCardEnum.THRALL);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom)
    {

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        return true;
    }
}
