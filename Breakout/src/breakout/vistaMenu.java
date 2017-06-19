package breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class vistaMenu extends JFrame{
	public static final int ANCHO = 600;
	public static final int ALTO = 800;
	JButton botonIniciar;

	DefaultTableModel tableModel = new DefaultTableModel();

	public  vistaMenu() {
		this.getContentPane().setBackground(Color.BLACK);
		this.setTitle("Break Out Juego");
		this.setSize(ANCHO, ALTO);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setLayout(null); 
		this.botonIniciar = new JButton("Iniciar");
		botonIniciar.setBounds(200, 100, 120, 50);

		botonIniciar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ModeloBreakout model = new ModeloBreakout();
				VistaBreakout  view  = new VistaBreakout();
				new Controlador( model, view );

				model.crearObjetos(40);      
				model.addObserver( view );       

				view.setVisible(true);           
				model.iniciarJuego();               
				dispose();
			}
		});


	
		this.add(botonIniciar);

	}

}
