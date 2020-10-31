package com.nordryd.phasmophobiaevidencetracker.journal;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.nordryd.phasmophobiaevidencetracker.evidence.Evidence;
import com.nordryd.phasmophobiaevidencetracker.evidence.EvidenceButton;
import com.nordryd.phasmophobiaevidencetracker.ghost.Ghost;

public class Journal extends JFrame implements ActionListener
{
    private static final String TITLE = "Phasmophobia Evidence Tracker";

    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;

    private final List<Evidence> currentEvidence;
    private final JTextArea survivalGuideDisplay, ghostDisplay, evidenceDisplay;
    private final List<EvidenceButton> buttons;

    private Set<Evidence> possibleEvidence;
    private Set<Ghost> possibleGhosts;

    public Journal() {
        this.possibleEvidence = new HashSet<>();
        this.possibleGhosts = new HashSet<>();

        final Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        this.currentEvidence = new LinkedList<>();
        this.survivalGuideDisplay = new JTextArea();
        this.survivalGuideDisplay.setLineWrap(true);
        this.survivalGuideDisplay.setWrapStyleWord(true);
        this.ghostDisplay = new JTextArea();
        this.ghostDisplay.setLineWrap(true);
        this.ghostDisplay.setWrapStyleWord(true);
        this.evidenceDisplay = new JTextArea();
        this.evidenceDisplay.setLineWrap(true);
        this.evidenceDisplay.setWrapStyleWord(true);

        final Panel buttonPanel = new Panel(new GridLayout(3, 2, 5, 5));
        this.buttons = new ArrayList<>();
        for (final Evidence evidence : Evidence.values()) {
            final EvidenceButton button = new EvidenceButton(evidence, this);
            buttons.add(button);
            buttonPanel.add(button);
        }

        contentPane.add(buttonPanel);
        contentPane.add(ghostDisplay);
        contentPane.add(evidenceDisplay);
        contentPane.add(survivalGuideDisplay);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source instanceof EvidenceButton) {
            render((EvidenceButton) source);
        }
    }

    private void updateEvidence(final EvidenceButton pressedEvidenceButton) {
        if (pressedEvidenceButton.isSelected()) {
            pressedEvidenceButton.isSelected(false);
            currentEvidence.remove(pressedEvidenceButton.getEvidence());
        }
        else if (currentEvidence.size() < 3) {
            pressedEvidenceButton.isSelected(true);
            currentEvidence.add(pressedEvidenceButton.getEvidence());
        }
    }

    private void deducePossibleGhosts() {
        possibleGhosts = stream(Ghost.values()).filter(ghost -> ghost.getEvidence().containsAll(currentEvidence))
                .collect(toSet());
    }

    private void deducePossibleEvidence() {
        final Set<Evidence> possibleEvidence = possibleGhosts.stream().map(Ghost::getEvidence)
                .flatMap(Collection::parallelStream).collect(toSet());
        possibleEvidence.removeAll(currentEvidence);
        this.possibleEvidence = possibleEvidence;
    }

    private void render(final EvidenceButton pressedEvidenceButton) {
        updateEvidence(pressedEvidenceButton);
        deducePossibleGhosts();
        deducePossibleEvidence();
        // change button colors and display possible ghost types as well
        // as a survival guide entry based on the possible ghosts
        // for example: if WRAITH is present - put salt down
        // if SHADE is present - hug a friend at all times to prevent a hunt
        // if POLTERGEIST - bring a camera and emf to get Interaction money (bring a crucifix and smudge stick for protection)

        // if no ghosts match the criteria, print a message that says so

        if (currentEvidence.isEmpty()) {
            ghostDisplay.setText("");
            evidenceDisplay.setText("Click on a piece of evidence when you discover it :)");
            buttons.forEach(button -> button.setBackground(Color.LIGHT_GRAY));
        }
        else {
            if (currentEvidence.size() < 3) {
                buttons.forEach(button -> button.setBackground(button.isSelected() ? Color.PINK : Color.LIGHT_GRAY));
                if (possibleGhosts.isEmpty()) {
                    ghostDisplay.setText("This is not a valid combination of evidence.");
                    evidenceDisplay.setText("");
                }
                else {
                    ghostDisplay.setText("The ghost is either a: " +
                            possibleGhosts.stream().map(ghost -> ghost.getName() + ", ").collect(joining())
                                    .replaceAll(", $", ""));
                    evidenceDisplay.setText("Possible Evidence: " +
                            possibleEvidence.stream().map(evidence -> evidence.getName() + ", ").collect(joining())
                                    .replaceAll(", $", ""));
                }
            }
            else {
                buttons.forEach(button -> button.setBackground(button.isSelected() ? Color.PINK : Color.BLACK));
                evidenceDisplay.setText("");
                if (possibleGhosts.isEmpty()) {
                    ghostDisplay.setText("This is not a valid combination of evidence.");
                }
                else {
                    ghostDisplay.setText("The ghost is a " + possibleGhosts.iterator().next());
                }
            }
        }

        if (!currentEvidence.isEmpty() && possibleGhosts.isEmpty()) {
            ghostDisplay.setText("This is not a valid combination of evidence.");
            evidenceDisplay.setText("");
        }
    }
}
