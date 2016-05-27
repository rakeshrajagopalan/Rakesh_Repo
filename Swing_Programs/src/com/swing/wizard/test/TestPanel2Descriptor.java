package com.swing.wizard.test;

import com.swing.wizard.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TestPanel2Descriptor extends WizardPanelDescriptor implements ActionListener {
    
    public static final String IDENTIFIER = "CONNECTOR_CHOOSE_PANEL";
    
    TestPanel2 panel2;
    
    public TestPanel2Descriptor() {
        
        panel2 = new TestPanel2();
        panel2.addCheckBoxActionListener(this);
        
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel2);
        
    }
    
    public Object getNextPanelDescriptor() {
        return TestPanel3Descriptor.IDENTIFIER;
    }
    
    public Object getBackPanelDescriptor() {
        return TestPanel1Descriptor.IDENTIFIER;
    }
    
    
    public void aboutToDisplayPanel() {
        setNextButtonAccordingToCheckBox();
    }    

    public void actionPerformed(ActionEvent e) {
        setNextButtonAccordingToCheckBox();
    }
            
    
    private void setNextButtonAccordingToCheckBox() {
         if (panel2.isCheckBoxSelected())
            getWizard().setNextFinishButtonEnabled(true);
         else
            getWizard().setNextFinishButtonEnabled(false);           
    
    }
}
