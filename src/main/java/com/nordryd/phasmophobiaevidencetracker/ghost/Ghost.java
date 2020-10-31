package com.nordryd.phasmophobiaevidencetracker.ghost;

import static java.util.Arrays.asList;

import java.util.List;

import com.nordryd.phasmophobiaevidencetracker.evidence.Evidence;

/**
 * The types of ghosts as well as their determining {@link Evidence}.
 */
public enum Ghost
{
    BANSHEE("Banshee", Evidence.EMF_5, Evidence.FINGERPRINTS, Evidence.FREEZING_TEMPERATURES),
    DEMON("Demon", Evidence.FREEZING_TEMPERATURES, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX),
    JINN("Jinn", Evidence.EMF_5, Evidence.GHOST_ORB, Evidence.SPIRIT_BOX),
    MARE("Mare", Evidence.FREEZING_TEMPERATURES, Evidence.GHOST_ORB, Evidence.SPIRIT_BOX),
    ONI("Oni", Evidence.EMF_5, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX),
    PHANTOM("Phantom", Evidence.EMF_5, Evidence.FREEZING_TEMPERATURES, Evidence.GHOST_ORB),
    POLTERGEIST("Poltergeist", Evidence.FINGERPRINTS, Evidence.GHOST_ORB, Evidence.SPIRIT_BOX),
    REVENANT("Revenant", Evidence.FINGERPRINTS, Evidence.EMF_5, Evidence.GHOST_WRITING),
    SHADE("Shade", Evidence.EMF_5, Evidence.GHOST_ORB, Evidence.GHOST_ORB),
    SPIRIT("Spirit", Evidence.FINGERPRINTS, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX),
    WRAITH("Wraith", Evidence.FINGERPRINTS, Evidence.FREEZING_TEMPERATURES, Evidence.SPIRIT_BOX);

    private final String name;
    private final Evidence evidence1, evidence2, evidence3;

    Ghost(final String name, final Evidence evidence1, final Evidence evidence2, final Evidence evidence3) {
        this.name = name;
        this.evidence1 = evidence1;
        this.evidence2 = evidence2;
        this.evidence3 = evidence3;
    }

    public final String getName() {
        return name;
    }

    public final List<Evidence> getEvidence() {
        return asList(evidence1, evidence2, evidence3);
    }
}
