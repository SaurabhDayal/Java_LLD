package aMachineCoding.designPen.components;

import java.util.ArrayList;
import java.util.List;

public class Ink {
    private String color;
    private PenType type;
    private List<InkFeature> features;

    public Ink(String color, PenType type) {
        this.color = color;
        this.type = type;
        this.features = new ArrayList<>();
    }

    public Ink(String color, PenType type, List<InkFeature> features) {
        this.color = color;
        this.type = type;
        this.features = features;
    }

    public String getColor() {
        return color;
    }

    public PenType getType() {
        return type;
    }

    public List<InkFeature> getFeatures() {
        return features;
    }

    public void addFeature(InkFeature feature) {
        this.features.add(feature);
    }

    public enum InkFeature {
        WATERPROOF,
        FADE_RESISTANT,
        QUICK_DRYING,
        SMUDGE_PROOF,
        GLITTER,
        METALLIC,
        NEON,
        ERASABLE,
        UV_REACTIVE,
        PERMANENT,
        ARCHIVAL,
        ACID_FREE,
        HIGH_VISCOSITY,
        LOW_VISCOSITY,
        GEL_INK,
        BALLPOINT_INK,
        FOUNTAIN_INK,
        MARKER_INK,
        LIQUID_INK
    }
}