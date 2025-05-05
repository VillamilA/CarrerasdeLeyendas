import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new VentanaCarrera();
                }
            });
        }
    }

class Hilo implements Runnable{
    Thread t;

    String nombre;

    JLabel personaje;

    JLabel labeFinal;

    public static int lugar;
    public Hilo(String nombre, JLabel personaje, JLabel labeFinal){
        this.nombre = nombre;
        this.labeFinal = labeFinal;
        this.personaje = personaje;
        t = new Thread(this, nombre);
        t.start();
    }

    @Override
    public void run(){
        int retardo;
        try{
            lugar = 1;
            retardo = (int) (Math.random()*15)+1;
            labeFinal.setVisible(false);
            personaje.setVisible(true);

            for(int i=50; i <= 500; i++){
                personaje.setLocation(i,personaje.getY());
                Thread.sleep(retardo);
            }
            personaje.setVisible(false);
            labeFinal.setVisible(true);
            labeFinal.setText(nombre + " Llego en la posicion: "+lugar);
            lugar++;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}




class VentanaCarrera extends JFrame{
    public VentanaCarrera() {
        super("Carrera de Leyendas");
        JLabel mario, luigi, sonic, mario_posic, luigi_posic, sonic_posic;
        JButton botonIniciarCarrrera;

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Image imagen_mario = new ImageIcon("src/img/mario.gif").getImage();
        ImageIcon Icon_mario = new ImageIcon(imagen_mario.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        mario = new JLabel();
        mario.setIcon(Icon_mario);
        mario.setBounds(50, 50, 50, 50);

        Image imagen_luigi = new ImageIcon("src/img/luigi.gif").getImage();
        ImageIcon Icon_luigi = new ImageIcon(imagen_luigi.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        luigi = new JLabel();
        luigi.setIcon(Icon_luigi);
        luigi.setBounds(50, 125, 50, 50);


        Image imagen_sonic = new ImageIcon("src/img/sonic.gif").getImage();
        ImageIcon Icon_sonic = new ImageIcon(imagen_sonic.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        sonic = new JLabel();
        sonic.setIcon(Icon_sonic);
        sonic.setBounds(50, 200, 50, 50);

        //POSICIONES

        mario_posic = new JLabel();
        mario_posic.setBounds(50, 50, 350, 50);

        luigi_posic = new JLabel();
        luigi_posic.setBounds(50, 100, 350, 50);

        sonic_posic = new JLabel();
        sonic_posic.setBounds(50, 150, 350, 50);

        //BOTON INICIAR CARRERA
        botonIniciarCarrrera = new JButton("INICIAR CARRRERA");
        botonIniciarCarrrera.setBounds(150, 300, 150, 50);
        botonIniciarCarrrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hilo tmario = new Hilo("Mario", mario, mario_posic);
                Hilo tluigi = new Hilo("Luigi", luigi, luigi_posic);
                Hilo tsonic = new Hilo("Sonic", sonic, sonic_posic);

            }
        });

       panel.add(mario);
       panel.add(mario_posic);
       panel.add(luigi);
       panel.add(luigi_posic);
       panel.add(sonic);
       panel.add(sonic_posic);
       panel.add(botonIniciarCarrrera);

       add(panel);
       setVisible(true);
        }
    }