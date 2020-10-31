package com.nordryd.phasmophobiaevidencetracker.objects;

public enum Evidence
{
    EMF_5("EMF 5"),
    FREEZING_TEMPERATURES("Freezing Temperatures"),
    FINGERPRINTS("Fingerprints"),
    SPIRIT_BOX("Spirit Box"),
    GHOST_WRITING("Ghost Writing"),
    GHOST_ORB("Ghost Orb");

    private final String name;

    Evidence(final String name) {
        this.name = name;
    }
}
