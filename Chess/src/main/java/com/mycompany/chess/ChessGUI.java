/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chess;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
/**
 *
 * @author luigi
 */
public class ChessGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] boardButtons;
    private HashMap<String, BufferedImage> pieceImages;

    public ChessGUI() {
        // Configuración de la ventana principal
        frame = new JFrame("Juego de Ajedrez");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        // Crear el panel del tablero de ajedrez
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardButtons = new JButton[8][8];

        // Inicializar las imágenes de las piezas
        loadPieceImages();

        // Inicializar los botones del tablero
        initializeBoard();

        // Añadir el panel del tablero a la ventana
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void loadPieceImages() {
        pieceImages = new HashMap<>();
        try {
            pieceImages.put("white_pawn", ImageIO.read(new File("resources/white_pawn.png")));
            pieceImages.put("white_rook", ImageIO.read(new File("resources/white_rook.png")));
            pieceImages.put("white_knight", ImageIO.read(new File("resources/white_knight.png")));
            pieceImages.put("white_bishop", ImageIO.read(new File("resources/white_bishop.png")));
            pieceImages.put("white_queen", ImageIO.read(new File("resources/white_queen.png")));
            pieceImages.put("white_king", ImageIO.read(new File("resources/white_king.png")));
            pieceImages.put("black_pawn", ImageIO.read(new File("resources/black_pawn.png")));
            pieceImages.put("black_rook", ImageIO.read(new File("resources/black_rook.png")));
            pieceImages.put("black_knight", ImageIO.read(new File("resources/black_knight.png")));
            pieceImages.put("black_bishop", ImageIO.read(new File("resources/black_bishop.png")));
            pieceImages.put("black_queen", ImageIO.read(new File("resources/black_queen.png")));
            pieceImages.put("black_king", ImageIO.read(new File("resources/black_king.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeBoard() {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            //Inicializar el botón
            boardButtons[i][j] = new JButton();
            boardButtons[i][j].setPreferredSize(new Dimension(100, 100));
            //Establecer el color del botón
            if ((i + j) % 2 == 0) {
                boardButtons[i][j].setBackground(Color.WHITE);
            } else {
                boardButtons[i][j].setBackground(Color.GRAY);
            }

            //Colocar las piezas iniciales
            if (i == 1) {
                boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get("black_pawn")));
            } else if (i == 6) {
                boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get("white_pawn")));
            }

            //Colocar torres
            if (i == 0 || i == 7) {
                String color = (i == 0) ? "black" : "white";
                if (j == 0 || j == 7) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get(color + "_rook")));
                }
            }

            //Colocar caballos
            if (i == 0 || i == 7) {
                String color = (i == 0) ? "black" : "white";
                if (j == 1 || j == 6) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get(color + "_knight")));
                }
            }

            //Colocar alfiles
            if (i == 0 || i == 7) {
                String color = (i == 0) ? "black" : "white";
                if (j == 2 || j == 5) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get(color + "_bishop")));
                }
            }

             //Colocar reinas y reyes
            if (i == 0) { // Fila de piezas negras
                if (j == 3) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get("black_queen")));
                } else if (j == 4) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get("black_king")));
                }
            } else if (i == 7) { // Fila de piezas blancas
                if (j == 3) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get("white_queen")));
                } else if (j == 4) {
                    boardButtons[i][j].setIcon(new ImageIcon(pieceImages.get("white_king")));
                }
            }

            // Añadir el botón al panel
            boardPanel.add(boardButtons[i][j]);
        }
    }
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessGUI());
    }
}
