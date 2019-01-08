package br.com.ferreira.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("STYLE")
public class StyleDTO {

    @XStreamAlias("NAME")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("VERSION")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("CATEGORY")
    @XStreamAsAttribute
    private String category;

    @XStreamAlias("CATEGORY_NUMBER")
    @XStreamAsAttribute
    private String categoryNumber;

    @XStreamAlias("STYLE_LETTER")
    @XStreamAsAttribute
    private String styleLetter;

    @XStreamAlias("STYLE_GUIDE")
    @XStreamAsAttribute
    private String styleGuide;

    @XStreamAlias("TYPE")
    @XStreamAsAttribute
    private String type;

    @XStreamAlias("OG_MIN")
    @XStreamAsAttribute
    private String ogMin;

    @XStreamAlias("OG_MAX")
    @XStreamAsAttribute
    private String ogMax;

    @XStreamAlias("FG_MIN")
    @XStreamAsAttribute
    private String fgMin;

    @XStreamAlias("FG_MAX")
    @XStreamAsAttribute
    private String fgMax;

    @XStreamAlias("IBU_MIN")
    @XStreamAsAttribute
    private String ibuMin;

    @XStreamAlias("IBU_MAX")
    @XStreamAsAttribute
    private String ibuMax;

    @XStreamAlias("COLOR_MIN")
    @XStreamAsAttribute
    private String colorMin;

    @XStreamAlias("COLOR_MAX")
    @XStreamAsAttribute
    private String colorMax;

    @XStreamAlias("CARB_MIN")
    @XStreamAsAttribute
    private String carbMin;

    @XStreamAlias("CARB_MAX")
    @XStreamAsAttribute
    private String carbMax;

    @XStreamAlias("ABV_MAX")
    @XStreamAsAttribute
    private String abvMax;

    @XStreamAlias("ABV_MIN")
    @XStreamAsAttribute
    private String abvMin;

    @XStreamAlias("NOTES")
    @XStreamAsAttribute
    private String notes;

    @XStreamAlias("PROFILE")
    @XStreamAsAttribute
    private String profile;

    @XStreamAlias("INGREDIENTS")
    @XStreamAsAttribute
    private String ingredients;

    @XStreamAlias("EXAMPLES")
    @XStreamAsAttribute
    private String examples;

    @XStreamAlias("DISPLAY_OG_MIN")
    @XStreamAsAttribute
    private String displayOgMin;

    @XStreamAlias("DISPLAY_OG_MAX")
    @XStreamAsAttribute
    private String displayOgMax;

    @XStreamAlias("DISPLAY_FG_MIN")
    @XStreamAsAttribute
    private String displayFgMin;

    @XStreamAlias("DISPLAY_FG_MAX")
    @XStreamAsAttribute
    private String displayFgMax;

    @XStreamAlias("DISPLAY_COLOR_MIN")
    @XStreamAsAttribute
    private String displayColorMin;

    @XStreamAlias("DISPLAY_COLOR_MAX")
    @XStreamAsAttribute
    private String displayColorMax;

    @XStreamAlias("OG_RANGE")
    @XStreamAsAttribute
    private String ogRange;

    @XStreamAlias("FG_RANGE")
    @XStreamAsAttribute
    private String fgRange;

    @XStreamAlias("IBU_RANGE")
    @XStreamAsAttribute
    private String ibuRange;

    @XStreamAlias("CARB_RANGE")
    @XStreamAsAttribute
    private String carbRange;

    @XStreamAlias("COLOR_RANGE")
    @XStreamAsAttribute
    private String colorRange;

    @XStreamAlias("ABV_RANGE")
    @XStreamAsAttribute
    private String abvRange;

}
