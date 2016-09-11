/*
 * Copyright 2016 Patrik Karlsson.
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

import java.awt.Component;
import java.util.prefs.PreferenceChangeEvent;
import javax.swing.border.EmptyBorder;
import se.trixon.almond.util.swing.SwingHelper;
import se.trixon.almond.util.swing.dialogs.FileChooserPanel;
import se.trixon.photokml.Options;
import se.trixon.photokml.Profile;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ConfigPanel extends javax.swing.JPanel {

    private final ModuleSourcePanel mModuleSourcePanel = new ModuleSourcePanel();
    private final ModuleFoldersPanel mModuleFoldersPanel = new ModuleFoldersPanel();
    private final ModulePlacemarkPanel mModulePlacemarksPanel = new ModulePlacemarkPanel();
    private final ModuleDescriptionPanel mModuleDescriptionPanel = new ModuleDescriptionPanel();
    private final ModulePhotoPanel mModulePhotoPanel = new ModulePhotoPanel();

    /**
     * Creates new form ConfigPanel
     */
    public ConfigPanel() {
        initComponents();
        init();
    }

    public void loadProfile(Profile profile) {
        for (Component component : tabbedPane.getComponents()) {
            if (component instanceof ModulePanel) {
                ModulePanel modulePanel = (ModulePanel) component;
                modulePanel.load(profile);
            }
        }
    }

    public FileChooserPanel getSourceChooserPanel() {
        return mModuleSourcePanel.getSourceChooserPanel();
    }

    public StringBuilder getHeaderBuilder() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(mModuleSourcePanel.getHeaderBuilder());
        sb.append(mModuleFoldersPanel.getHeaderBuilder());
        sb.append(mModulePlacemarksPanel.getHeaderBuilder());
        sb.append(mModuleDescriptionPanel.getHeaderBuilder());
        sb.append(mModulePhotoPanel.getHeaderBuilder());

        return sb;
    }

    public boolean hasValidSettings() {
        boolean validSettings = true;

        for (Component component : tabbedPane.getComponents()) {
            if (component instanceof ModulePanel) {
                ModulePanel modulePanel = (ModulePanel) component;
                if (!modulePanel.hasValidSettings()) {
                    validSettings = false;
                    break;
                }
            }
        }

        return validSettings;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SwingHelper.enableComponents(this, enabled);

        if (enabled) {
            for (Component component : tabbedPane.getComponents()) {
                if (component instanceof ModulePanel) {
                    ModulePanel modulePanel = (ModulePanel) component;
                    modulePanel.restoreEnabledStates();
                }
            }
        }
    }

    private Component addModulePanel(ModulePanel modulePanel) {
        return tabbedPane.add(modulePanel.getTitle(), modulePanel);
    }

    private boolean disableTab(Component tab) {
        try {
            tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(tab), false);
            return true;
        } catch (IndexOutOfBoundsException e) {
            //Tab not found
            return false;
        }
    }

    private void init() {
        addModulePanel(mModuleSourcePanel);
        addModulePanel(mModuleFoldersPanel);
        addModulePanel(mModulePlacemarksPanel);
        addModulePanel(mModuleDescriptionPanel);
        addModulePanel(mModulePhotoPanel);

        for (Component component : tabbedPane.getComponents()) {
            if (component instanceof ModulePanel) {
                ModulePanel modulePanel = (ModulePanel) component;
                modulePanel.setBorder(new EmptyBorder(8, 8, 8, 8));
            }
        }

        Options.getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (evt.getKey().equalsIgnoreCase(Options.KEY_DESCRIPTION_PHOTO)) {
                updatePhotoTabState();
            }
        });
        updatePhotoTabState();
        setEnabled(true);
    }

    private void updatePhotoTabState() {
        tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(mModulePhotoPanel), Options.INSTANCE.isDescriptionPhoto());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
