import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI(){
		super();
		GridBagConstraints constraints = new GridBagConstraints();

		GridBagLayout gbl = new GridBagLayout();
		
		JPanel main = new JPanel(gbl);
		
		constraints.gridheight = 1;
		constraints.gridwidth = 2; 
		final JSpinner nameCounter = new JSpinner(new SpinnerNumberModel(100,1,1000000,1));
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(nameCounter, "#");
		nameCounter.setEditor(editor);
//		nameCounter.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent arg0) {
//				if(nameCounter.getValue().toString().length()>7) {
//					String value = nameCounter.getValue().toString();
//					int overSize = nameCounter.getValue().toString().length()-7;
//					int newValue = Integer.parseInt(value.substring(overSize));
//					nameCounter.setValue(newValue);
//				}
//			}
//		});
		JButton generate = new JButton("generate");
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(nameCounter.getValue().toString());
				JFileChooser chooser = new JFileChooser();
		        // Dialog zum Oeffnen von Dateien anzeigen
		        int rueckgabeWert = chooser.showSaveDialog(null);
		        
		        /* Abfrage, ob auf "Öffnen" geklickt wurde */
		        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
		        {
		        	new ReadNames(Integer.valueOf(nameCounter.getValue().toString()), chooser.getSelectedFile().toString()+".csv");
		             // Ausgabe der ausgewaehlten Datei
		            System.out.println("Die zu öffnende Datei ist: " +
		                  chooser.getSelectedFile().getName());
		        }
				
			}
		});
		constraints.gridwidth = 0;
		main.add(nameCounter, constraints);
		constraints.gridwidth = 1;
		main.add(generate, constraints);
		add(main);
		setMinimumSize(new Dimension(300,100));
		pack();
		setVisible(true);
		
	}
}
