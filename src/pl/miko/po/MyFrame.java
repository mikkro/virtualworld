
package pl.miko.po;

import java.awt.Color;
import javax.swing.*;
import java.io.*;

class MyFrame extends javax.swing.JFrame {

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private World world;
	private ImagePanel animalImages;
	private int worldSize;
	private JFileChooser fileChooser;

	public MyFrame(World world, int size) {
		worldSize = size;
		initComponents();
		getContentPane().setBackground(new Color(230, 255, 255));
		jTextArea1.setEditable(false);
		this.world = world;
		setVisible(true);
		fileChooser = new JFileChooser();
	}
	@SuppressWarnings("unchecked")
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        animalImages = new ImagePanel(new String[] {"pictures/Wolf.png", "pictures/Sheep.png", "pictures/Snail.png", "pictures/Lion.png",
				"pictures/Bacteria.png", "pictures/Grass.png", "pictures/Guarana.png", "pictures/DeadlyNightshade.png"}, world, worldSize);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(204, 255, 204));

        jButton1.setText("Nastepna tura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zapisz grę");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Wczytaj grę");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

         animalImages.setPreferredSize(new java.awt.Dimension(801, 801));
        animalImages.setBackground(new java.awt.Color(204, 255, 204));
        javax.swing.GroupLayout animalImagesLayout = new javax.swing.GroupLayout(animalImages);
        animalImages.setLayout(animalImagesLayout);
        animalImagesLayout.setHorizontalGroup(
            animalImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );
        animalImagesLayout.setVerticalGroup(
            animalImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(animalImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(animalImages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		resetLog();
		world.nextTour();
	}	

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
	{
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File file = fileChooser.getSelectedFile();
			printLog("Otwieram plik do zapisu: " + file.getPath());
			try
			{
				world.save(new BufferedOutputStream(new FileOutputStream(file)));
			}
			catch(Exception e)
			{
				world.printLog("Błąd przy otwieraniu pliku!");
			}
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) 
	{
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File file = fileChooser.getSelectedFile();
			printLog("Otwieram plik do czytania: " + file.getPath());
			try
			{
				world.load(new BufferedInputStream(new FileInputStream(file)));
			}
			catch(Exception e)
			{
				world.printLog("Błąd przy otwieraniu pliku!");
			}
		}
	}

	
	public void printLog(String string)
	{
		jTextArea1.append(string +"\n");
	}
	public void resetLog()
	{
		jTextArea1.setText("");
	}
	public ImagePanel getAnimalPanel()
	{
		return animalImages;
	}
	

}
