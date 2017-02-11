/*
 * Copyright 2017 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.photokml.ui.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.prefs.PreferenceChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import se.trixon.almond.util.Dict;
import se.trixon.photokml.profile.Profile;
import se.trixon.photokml.profile.ProfilePlacemark;

/**
 *
 * @author Patrik Karlsson
 */
public class ModulePlacemarkPanel extends ModulePanel {

    private boolean mInvalidDateFormat;
    private ProfilePlacemark mPlacemark = new ProfilePlacemark(mProfile);

    /**
     * Creates new form ModulePlacemarkPanel
     */
    public ModulePlacemarkPanel() {
        initComponents();
        mTitle = Dict.PLACEMARK.toString();
        init();
    }

    @Override
    public StringBuilder getHeaderBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append(Dict.PLACEMARK.toString()).append("\n");
        String nameBy = "";
        int value = mPlacemark.getNameBy();
        if (value == ProfilePlacemark.NAME_BY_FILE) {
            nameBy = nameByFileRadioButton.getText();
        } else if (value == ProfilePlacemark.NAME_BY_DATE) {
            nameBy = String.format("%s: %s", Dict.DATE_PATTERN.toString(), dateFormatTextField.getText());
        } else if (value == ProfilePlacemark.NAME_BY_NONE) {
            nameBy = nameByNoRadioButton.getText();
        }
        append(sb, nameByLabel.getText(), nameBy);
        optAppend(sb, mPlacemark.isIncludeNullCoordinate(), String.format(Locale.ENGLISH, "%s (%f, %f)",
                includeNullCoordinateCheckBox.getText(),
                mPlacemark.getLat(), mPlacemark.getLon()));
        sb.append("\n");

        return sb;
    }

    @Override
    public boolean hasValidSettings() {
        if (mInvalidDateFormat && mPlacemark.getNameBy() == 1) {
            invalidSettings(Dict.INVALID_DATE_PATTERN.toString());

            return false;
        }

        return true;
    }

    @Override
    public void onPreferenceChange(PreferenceChangeEvent evt) {
        previewDateFormat();
    }

    private void init() {
        dateFormatTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                previewDateFormat();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                previewDateFormat();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                previewDateFormat();
            }
        });
    }

    private void previewDateFormat() {
        String datePreview;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatTextField.getText(), getDateFormatLocale());
            datePreview = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            mInvalidDateFormat = false;
        } catch (IllegalArgumentException ex) {
            datePreview = Dict.Dialog.ERROR.toString();
            mInvalidDateFormat = true;
        }

        String dateLabel = String.format("%s (%s)", Dict.DATE_PATTERN.toString(), datePreview);
        nameByDateRadioButton.setText(dateLabel);
        mPlacemark.setDatePattern(dateFormatTextField.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        nameButtonGroup = new javax.swing.ButtonGroup();
        nameByLabel = new javax.swing.JLabel();
        nameByFileRadioButton = new javax.swing.JRadioButton();
        nameByDateRadioButton = new javax.swing.JRadioButton();
        dateFormatTextField = new javax.swing.JTextField();
        nameByNoRadioButton = new javax.swing.JRadioButton();
        nullCoordinatePanel = new javax.swing.JPanel();
        includeNullCoordinateCheckBox = new javax.swing.JCheckBox();
        latLabel = new javax.swing.JLabel();
        latSpinner = new javax.swing.JSpinner();
        lonLabel = new javax.swing.JLabel();
        lonSpinner = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("se/trixon/photokml/ui/config/Bundle"); // NOI18N
        nameByLabel.setText(bundle.getString("ModulePlacemarkPanel.nameByLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(nameByLabel, gridBagConstraints);

        nameButtonGroup.add(nameByFileRadioButton);
        nameByFileRadioButton.setText(Dict.FILENAME.getString());
        nameByFileRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameByFileRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 0, 0);
        add(nameByFileRadioButton, gridBagConstraints);

        nameButtonGroup.add(nameByDateRadioButton);
        nameByDateRadioButton.setText(Dict.DATE_PATTERN.getString());
        nameByDateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameByDateRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 0, 0);
        add(nameByDateRadioButton, gridBagConstraints);

        dateFormatTextField.setToolTipText("<html>  <h3>Date and Time Patterns</h3>  <p>  Date and time formats are specified by <em>date and time pattern</em>  strings.  Within date and time pattern strings, unquoted letters from  <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to  <code>'z'</code> are interpreted as pattern letters representing the  components of a date or time string.  Text can be quoted using single quotes (<code>'</code>) to avoid  interpretation.  <code>\"''\"</code> represents a single quote.  All other characters are not interpreted; they're simply copied into the  output string during formatting or matched against the input string  during parsing.  <p> The following pattern letters are defined (all other characters from  <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to  <code>'z'</code> are reserved):  <blockquote>  <table border=0 cellspacing=3 cellpadding=0 summary=\"Chart shows pattern letters, date/time component, presentation, and examples.\">      <tr style=\"background-color: rgb(204, 204, 255);\">          <th align=left>Letter          <th align=left>Date or Time Component          <th align=left>Presentation          <th align=left>Examples      <tr>          <td><code>G</code>          <td>Era designator          <td><a href=\"#text\">Text</a>          <td><code>AD</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>y</code>          <td>Year          <td><a href=\"#year\">Year</a>          <td><code>1996</code>; <code>96</code>      <tr>          <td><code>Y</code>          <td>Week year          <td><a href=\"#year\">Year</a>          <td><code>2009</code>; <code>09</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>M</code>          <td>Month in year (context sensitive)          <td><a href=\"#month\">Month</a>          <td><code>July</code>; <code>Jul</code>; <code>07</code>      <tr>          <td><code>L</code>          <td>Month in year (standalone form)          <td><a href=\"#month\">Month</a>          <td><code>July</code>; <code>Jul</code>; <code>07</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>w</code>          <td>Week in year          <td><a href=\"#number\">Number</a>          <td><code>27</code>      <tr>          <td><code>W</code>          <td>Week in month          <td><a href=\"#number\">Number</a>          <td><code>2</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>D</code>          <td>Day in year          <td><a href=\"#number\">Number</a>          <td><code>189</code>      <tr>          <td><code>d</code>          <td>Day in month          <td><a href=\"#number\">Number</a>          <td><code>10</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>F</code>          <td>Day of week in month          <td><a href=\"#number\">Number</a>          <td><code>2</code>      <tr>          <td><code>E</code>          <td>Day name in week          <td><a href=\"#text\">Text</a>          <td><code>Tuesday</code>; <code>Tue</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>u</code>          <td>Day number of week (1 = Monday, ..., 7 = Sunday)          <td><a href=\"#number\">Number</a>          <td><code>1</code>      <tr>          <td><code>a</code>          <td>Am/pm marker          <td><a href=\"#text\">Text</a>          <td><code>PM</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>H</code>          <td>Hour in day (0-23)          <td><a href=\"#number\">Number</a>          <td><code>0</code>      <tr>          <td><code>k</code>          <td>Hour in day (1-24)          <td><a href=\"#number\">Number</a>          <td><code>24</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>K</code>          <td>Hour in am/pm (0-11)          <td><a href=\"#number\">Number</a>          <td><code>0</code>      <tr>          <td><code>h</code>          <td>Hour in am/pm (1-12)          <td><a href=\"#number\">Number</a>          <td><code>12</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>m</code>          <td>Minute in hour          <td><a href=\"#number\">Number</a>          <td><code>30</code>      <tr>          <td><code>s</code>          <td>Second in minute          <td><a href=\"#number\">Number</a>          <td><code>55</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>S</code>          <td>Millisecond          <td><a href=\"#number\">Number</a>          <td><code>978</code>      <tr>          <td><code>z</code>          <td>Time zone          <td><a href=\"#timezone\">General time zone</a>          <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code>      <tr style=\"background-color: rgb(238, 238, 255);\">          <td><code>Z</code>          <td>Time zone          <td><a href=\"#rfc822timezone\">RFC 822 time zone</a>          <td><code>-0800</code>      <tr>          <td><code>X</code>          <td>Time zone          <td><a href=\"#iso8601timezone\">ISO 8601 time zone</a>          <td><code>-08</code>; <code>-0800</code>;  <code>-08:00</code>  </table>  </blockquote>"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, nameByDateRadioButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), dateFormatTextField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        dateFormatTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFormatTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 28, 0, 0);
        add(dateFormatTextField, gridBagConstraints);

        nameButtonGroup.add(nameByNoRadioButton);
        nameByNoRadioButton.setText(bundle.getString("ModulePlacemarkPanel.nameByNoRadioButton.text")); // NOI18N
        nameByNoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameByNoRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 0, 0);
        add(nameByNoRadioButton, gridBagConstraints);

        nullCoordinatePanel.setLayout(new java.awt.GridBagLayout());

        includeNullCoordinateCheckBox.setText(bundle.getString("ModulePlacemarkPanel.includeNullCoordinateCheckBox.text")); // NOI18N
        includeNullCoordinateCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeNullCoordinateCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        nullCoordinatePanel.add(includeNullCoordinateCheckBox, gridBagConstraints);

        latLabel.setText(Dict.LATITUDE.getString());
        latLabel.setPreferredSize(new java.awt.Dimension(100, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        nullCoordinatePanel.add(latLabel, gridBagConstraints);

        latSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -90.0d, 90.0d, 0.01d));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, includeNullCoordinateCheckBox, org.jdesktop.beansbinding.ELProperty.create("${selected}"), latSpinner, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        latSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                latSpinnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        nullCoordinatePanel.add(latSpinner, gridBagConstraints);

        lonLabel.setText(Dict.LONGITUDE.getString());
        lonLabel.setPreferredSize(new java.awt.Dimension(100, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        nullCoordinatePanel.add(lonLabel, gridBagConstraints);

        lonSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -180.0d, 180.0d, 0.01d));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, includeNullCoordinateCheckBox, org.jdesktop.beansbinding.ELProperty.create("${selected}"), lonSpinner, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        lonSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lonSpinnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        nullCoordinatePanel.add(lonSpinner, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        nullCoordinatePanel.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        add(nullCoordinatePanel, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void dateFormatTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFormatTextFieldFocusLost
        mPlacemark.setDatePattern(dateFormatTextField.getText());
    }//GEN-LAST:event_dateFormatTextFieldFocusLost

    private void nameByFileRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameByFileRadioButtonActionPerformed
        mPlacemark.setNameBy(ProfilePlacemark.NAME_BY_FILE);
    }//GEN-LAST:event_nameByFileRadioButtonActionPerformed

    private void nameByNoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameByNoRadioButtonActionPerformed
        mPlacemark.setNameBy(ProfilePlacemark.NAME_BY_NONE);
    }//GEN-LAST:event_nameByNoRadioButtonActionPerformed

    private void nameByDateRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameByDateRadioButtonActionPerformed
        mPlacemark.setNameBy(ProfilePlacemark.NAME_BY_DATE);
    }//GEN-LAST:event_nameByDateRadioButtonActionPerformed

    private void includeNullCoordinateCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includeNullCoordinateCheckBoxActionPerformed
        boolean state = includeNullCoordinateCheckBox.isSelected();
        latSpinner.setEnabled(state);
        lonSpinner.setEnabled(state);
        mPlacemark.setIncludeNullCoordinate(includeNullCoordinateCheckBox.isSelected());
    }//GEN-LAST:event_includeNullCoordinateCheckBoxActionPerformed

    private void latSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_latSpinnerStateChanged
        mPlacemark.setLat((Double) latSpinner.getModel().getValue());
    }//GEN-LAST:event_latSpinnerStateChanged

    private void lonSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lonSpinnerStateChanged
        mPlacemark.setLon((Double) lonSpinner.getModel().getValue());
    }//GEN-LAST:event_lonSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dateFormatTextField;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JCheckBox includeNullCoordinateCheckBox;
    private javax.swing.JLabel latLabel;
    private javax.swing.JSpinner latSpinner;
    private javax.swing.JLabel lonLabel;
    private javax.swing.JSpinner lonSpinner;
    private javax.swing.ButtonGroup nameButtonGroup;
    private javax.swing.JRadioButton nameByDateRadioButton;
    private javax.swing.JRadioButton nameByFileRadioButton;
    private javax.swing.JLabel nameByLabel;
    private javax.swing.JRadioButton nameByNoRadioButton;
    private javax.swing.JPanel nullCoordinatePanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void load(Profile profile) {
        mProfile = profile;
        mPlacemark = mProfile.getPlacemark();

        dateFormatTextField.setText(mPlacemark.getDatePattern());

        switch (mPlacemark.getNameBy()) {
            case ProfilePlacemark.NAME_BY_FILE:
                nameByFileRadioButton.setSelected(true);
                nameByFileRadioButtonActionPerformed(null);
                break;

            case ProfilePlacemark.NAME_BY_DATE:
                nameByDateRadioButton.setSelected(true);
                nameByDateRadioButtonActionPerformed(null);
                break;

            case ProfilePlacemark.NAME_BY_NONE:
                nameByNoRadioButton.setSelected(true);
                nameByNoRadioButtonActionPerformed(null);
                break;
        }

        includeNullCoordinateCheckBox.setSelected(mPlacemark.isIncludeNullCoordinate());
        latSpinner.setValue(mPlacemark.getLat());
        lonSpinner.setValue(mPlacemark.getLon());
    }
}
