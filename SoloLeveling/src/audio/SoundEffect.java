package audio;

import untilz.LoadSave;

public enum SoundEffect {
    TAP("tap",LoadSave.SFX_TAP),
    COIN_TAP("coin_tap",LoadSave.SFX_COIN_TAP),
    GOT_HIT("got_hit",LoadSave.SFX_GOT_HIT),
    GAME_OVER("game_over",LoadSave.SFX_GAME_OVER),
    HAPPY_MOMENT("happy_moment",LoadSave.SFX_HAPPY_MOMMENT);

    private String name;
    private String filePath;

    SoundEffect(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    

}
