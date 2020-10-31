package com.nordryd.phasmophobiaevidencetracker.evidence;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;

public class EvidenceButton extends Button
{
    private final Evidence evidence;

    private boolean isSelected;

    public EvidenceButton(final Evidence evidence, final ActionListener actionListener) {
        super(evidence.getName());
        this.setBackground(Color.LIGHT_GRAY);
        this.evidence = evidence;
        this.addActionListener(actionListener);
        this.isSelected = false;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void isSelected(final boolean isSelected) {
        this.isSelected = isSelected;
    }
}
