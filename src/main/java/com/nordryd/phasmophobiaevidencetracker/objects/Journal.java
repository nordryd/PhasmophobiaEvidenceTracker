package com.nordryd.phasmophobiaevidencetracker.objects;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Journal extends Frame implements ActionListener
{
    private final List<Evidence> currentEvidence;
    private final List<Button> buttons;
    private final Label label;

    public Journal() {
        setLayout(new FlowLayout());
        this.currentEvidence = new LinkedList<>();
        this.buttons = new ArrayList<>();
        this.label = new Label("Click a piece of evidence when you discover it.");

        for (final Evidence evidence : Evidence.values()) {

        }
    }

    private List<Ghost> deducePossibleGhosts() {
        return null;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {

    }

    private void render() {

    }
}
