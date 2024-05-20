package component;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.Color;

import javax.swing.Icon;

public class IconCustom {
    private String path;
    private float scale;
    private boolean isLogo;

    public IconCustom(String path, float scale, boolean isLogo) {
        this.path = path;
        this.scale = scale;
        this.isLogo = isLogo;
    }

    public Icon getIcon() {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();

        colorFilter.add(Color.decode("#e8eaed"), isLogo ? Color.decode("#552cf6") : Color.decode("#ffffff"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

}
