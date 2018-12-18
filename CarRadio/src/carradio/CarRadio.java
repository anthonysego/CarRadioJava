/*
Program Name:           CarRadio
Author:                 Anthony Sego
Date:                   4/25/2018
Purpose of Program:     Driver for CarRadioPanel.java
                        Sets up and displays panel for car radio simulation     */
package carradio;

import javax.swing.*;

public class CarRadio
{
   //-----------------------------------------------------------------
   //  Creates and displays the controls for a juke box.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Car Radio");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(new CarRadioPanel());

      frame.pack();
      frame.setVisible(true);
   }
}

