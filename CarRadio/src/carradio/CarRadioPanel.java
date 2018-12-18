/*
Program Name:           CarRadio
Author:                 Anthony Sego
Date:                   4/25/2018
Purpose of Program:     Use java GUI to simulate a car radio.
                        Radio buttons used for radio off and 5 radio stations
                        check box used for windshield wipers.  On or off
                        Slider used to simulate volume control.  Changes size of text instead.*/
package carradio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.AudioClip;
import java.net.URL;
import javax.swing.event.*;

public class CarRadioPanel extends JPanel
{   
   //-----------------------------------------------------------------
   //  instantiate all buttons, labels, audioclip, check box and slider
   //-----------------------------------------------------------------
   private JRadioButton offButton;
   private JRadioButton classical;
   private JRadioButton hitchcock;
   private JRadioButton newAge;
   private JRadioButton countryWestern;
   private JRadioButton eighties;
   private AudioClip[] music;
   private AudioClip current;
   
   private JLabel wSWipersLabel;
   private JCheckBox wSWipersCheck;
   
   private JLabel volumeLabel;
   private JSlider volumeSlider;

   //-----------------------------------------------------------------
   //  Sets up the GUI for the radio
   //-----------------------------------------------------------------
   public CarRadioPanel()
   {
      setLayout(new BoxLayout (this, BoxLayout.PAGE_AXIS));
          
      URL url1, url2, url3, url4, url5;
      url1 = url2 = url3 = url4 = url5 = null;

      try
      {
         url1 = new URL ("file", "localhost", "classical.wav");
         url2 = new URL ("file", "localhost", "eightiesJam.wav");
         url3 = new URL ("file", "localhost", "hitchcock.wav");
         url4 = new URL ("file", "localhost", "newAgeRythm.wav");
         url5 = new URL ("file", "localhost", "westernBeat.wav");
      }
      catch (Exception exception) {}
      
      //-----------------------------------------------------------------
      //Puts the music in an array based on url variables
      //-----------------------------------------------------------------
      music = new AudioClip[7];
      music[0] = null;  // Corresponds to "Make a Selection..."
      music[1] = JApplet.newAudioClip (url1);
      music[2] = JApplet.newAudioClip (url2);
      music[3] = JApplet.newAudioClip (url3);
      music[4] = JApplet.newAudioClip (url4);
      music[5] = JApplet.newAudioClip (url5);

      //-----------------------------------------------------------------
      //Setting up radio buttons.  Set Radio Off on default
      //-----------------------------------------------------------------
      offButton = new JRadioButton ("Radio Off", true);
      classical = new JRadioButton ("Classical Station");
      hitchcock = new JRadioButton ("Hitchcock Station");
      newAge = new JRadioButton ("New Age Station");
      countryWestern = new JRadioButton ("Country Western Station");
      eighties = new JRadioButton ("80s Station");
      
      //-----------------------------------------------------------------
      //Set up wiper check box and label
      //-----------------------------------------------------------------
      wSWipersLabel = new JLabel ("Windshield Wipers Off");
      wSWipersCheck = new JCheckBox ("");
      
      //-----------------------------------------------------------------
      //Set up volume slider and label.  1 tick intervals, 4 ticks total
      //-----------------------------------------------------------------
      volumeLabel = new JLabel("Volume");
      volumeSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 2);
      volumeSlider.setMajorTickSpacing(1);
      volumeSlider.setPaintTicks(true);
      volumeSlider.setPaintLabels(true);
      
      //-----------------------------------------------------------------
      //Set dimensions of panel and color
      //-----------------------------------------------------------------
      setPreferredSize (new Dimension (300, 350));
      setBackground (Color.magenta);
      
      //-----------------------------------------------------------------
      //Adds radio buttons to a group so only one can be selected at a time
      //-----------------------------------------------------------------
      ButtonGroup group = new ButtonGroup();
      group.add(offButton);
      group.add(classical);
      group.add(hitchcock);
      group.add(newAge);
      group.add(countryWestern);
      group.add(eighties);
      
      //-----------------------------------------------------------------
      //Add all buttons, labels, and sliders to panel
      //-----------------------------------------------------------------
      add(offButton);
      add(classical);
      add(hitchcock);
      add(newAge);
      add(countryWestern);
      add(eighties);
      
      add(wSWipersLabel);
      add(wSWipersCheck);
      
      add(volumeLabel);
      add(volumeSlider);
      
      //-----------------------------------------------------------------
      //Add actionlisenter to the radio buttons
      //-----------------------------------------------------------------
      offButton.addActionListener (new RadioListener());
      classical.addActionListener (new RadioListener());
      hitchcock.addActionListener (new RadioListener());
      newAge.addActionListener (new RadioListener());
      countryWestern.addActionListener (new RadioListener());
      eighties.addActionListener (new RadioListener());
      
      //-----------------------------------------------------------------
      //Add item listener to the windshield wiper check box
      //-----------------------------------------------------------------
      WiperListener listener = new WiperListener();
      wSWipersCheck.addItemListener(listener);
      
      //-----------------------------------------------------------------
      //add change listener to the volume slider
      //-----------------------------------------------------------------
      VolumeListener listener2 = new VolumeListener();
      volumeSlider.addChangeListener((ChangeListener) listener2);
      volumeLabel.setVisible(false);
      volumeSlider.setVisible(false);

      current = null;
   }

   //-----------------------------------------------------------------
   //RadioListener for the radio buttons
   //-----------------------------------------------------------------
   private class RadioListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Stops playing the current selection if there is one first
      //  Plays the song based on which button is chosen.
      //  Also sets volume label and slider to visible when the radio is "on"
      // or playing a song. Invisible when radio is off
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
        Object source = event.getSource();
        
         if (source == offButton)
         {
             if (current != null){current.stop();}
             current = music[0];
             volumeLabel.setVisible(false);
             volumeSlider.setVisible(false);
         }
         
         else if (source == classical)
         {
             if (current != null){current.stop();}
             current = music[1];
             current.play();
             volumeLabel.setVisible(true);
             volumeSlider.setVisible(true); 
         }
         
         else if (source == hitchcock)
         {
             if (current != null){current.stop();}
             current = music[2];
             current.play();
             volumeLabel.setVisible(true);
             volumeSlider.setVisible(true);
         }
         
         else if (source == newAge)
         {
             if (current != null){current.stop();}
             current = music[3];
             current.play();
             volumeLabel.setVisible(true);
             volumeSlider.setVisible(true);
         }
         
         else if (source == countryWestern)
         {
             if (current != null){current.stop();}
             current = music[4];
             current.play();
             volumeLabel.setVisible(true);
             volumeSlider.setVisible(true);
         }
         
         else if (source == eighties)
         {
             if (current != null){current.stop();}
             current = music[5];
             current.play();
             volumeLabel.setVisible(true);
             volumeSlider.setVisible(true);
         }
         
      }
    }

   //-----------------------------------------------------------------
   //Wiper Listener for the winshield wiper check box
   //-----------------------------------------------------------------
   private class WiperListener implements ItemListener
   {
      //--------------------------------------------------------------
      //  If box is checked, label text is changed to Windshield wipers on
      //  if box is unchecked, text set to Winshield wipers off
      //--------------------------------------------------------------
      public void itemStateChanged (ItemEvent event)
      {
          if(wSWipersCheck.isSelected()){
            wSWipersLabel.setText("Windshield Wipers On");  
          }
          else{
            wSWipersLabel.setText("Windshield Wipers Off");
          }
         
      }
   }
   
   //-----------------------------------------------------------------
   //Change listener for volume slider
   //-----------------------------------------------------------------
   private class VolumeListener implements ChangeListener
   {    
      
       private int volume;
       
       //-----------------------------------------------------------------
       // Set a value for slider position to a value for volume
       // Only 4 ticks.  Used switch statement for 1 - 4
       // sets font of volume label to 8, 10, 12, or 14 based on slider position/value
       //-----------------------------------------------------------------
       public void stateChanged(ChangeEvent event)
       {
           volume = volumeSlider.getValue();
           
           switch(volume)
           {
                case 1:
                    volumeLabel.setFont(volumeLabel.getFont().deriveFont(8f));
                    break;
                    
                case 2:
                    volumeLabel.setFont(volumeLabel.getFont().deriveFont(10f));
                    break;
                    
                case 3:
                    volumeLabel.setFont(volumeLabel.getFont().deriveFont(12f));
                    break;
                    
                case 4:
                    volumeLabel.setFont(volumeLabel.getFont().deriveFont(14f));
                    break;
                    
                default:
                    volumeLabel.setFont(volumeLabel.getFont().deriveFont(10f));
                    break;
                    
           }
       }
   }
}

