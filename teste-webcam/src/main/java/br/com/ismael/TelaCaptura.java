package br.com.ismael;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class TelaCaptura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel painelVideo;
	private JPanel painelFoto;
	private JPanel painelBotoes;
	private JLabel labelFoto;
	private Webcam webcam;
	private WebcamPanel painelWebcam;
	private JButton botaoCaptura;

	public TelaCaptura() {
		super();
		getContentPane().setLayout(new BorderLayout());
		if (Webcam.getWebcams() == null && Webcam.getWebcams().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma câmera.");
			System.exit(0);
		}
		webcam = Webcam.getDefault();
		Dimension[] viewSizes = webcam.getViewSizes();
		Dimension tamanhoFoto = viewSizes[viewSizes.length - 1];
		webcam.setViewSize(tamanhoFoto);
		setTitle(webcam.getName());
		painelVideo = new JPanel();
		painelVideo.setBorder(BorderFactory.createTitledBorder("Video"));
		painelWebcam = new WebcamPanel(webcam);
		painelVideo.add(painelWebcam);
		painelFoto = new JPanel();
		painelFoto.setBorder(BorderFactory.createTitledBorder("Foto"));
		labelFoto = new JLabel();
		labelFoto.setPreferredSize(tamanhoFoto);
		painelFoto.add(labelFoto);
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botaoCaptura = new JButton("Capturar");
		painelBotoes.add(botaoCaptura);
		painelBotoes.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(painelVideo, BorderLayout.WEST);
		getContentPane().add(painelFoto, BorderLayout.EAST);
		getContentPane().add(painelBotoes, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void exibeTela() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		botaoCaptura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage webcamImage = webcam.getImage();
				labelFoto.setIcon(new ImageIcon(webcamImage));
				try {
					ImageIO.write(webcamImage, "JPG", new File("foto.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		setVisible(true);
	}

}
