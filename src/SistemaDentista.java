import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Paciente{
   String nombre;
   int edad;
   String padecimiento;

   public Paciente(String nombre, int edad, String padecimiento) {
      this.nombre = nombre;
      this.edad = edad;
      this.padecimiento = padecimiento;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public int getEdad() {
      return edad;
   }

   public void setEdad(int edad) {
      this.edad = edad;
   }

   public String getPadecimiento() {
      return padecimiento;
   }

   public void setPadecimiento(String padecimiento) {
      this.padecimiento = padecimiento;
   }

   @Override
   public String toString() {
      return "Paciente{" + "nombre=" + nombre + ", edad=" + edad + ", padecimiento=" + padecimiento + '}';
   }
}

class Cola{
   private int maxSize;
   private int front;
   private int rear;
   public Paciente[] queue;

   public Cola(int size) {
      maxSize = size;
      front = -1;
      rear = -1;
      queue = new Paciente[maxSize];
   }

   public boolean isEmpty() {
      return front == -1;
   }

   public boolean isFull() {
      return (front == 0 && rear == maxSize - 1) || (front == rear + 1);
   }

   public void enqueue(Paciente data) {
      if (isFull()) {
         System.out.println("La cola está llena. No se puede agregar más elementos.");
      } else {
         if (front == -1) {
               front = 0;
         }
         rear = (rear + 1) % maxSize;
         queue[rear] = data;
         System.out.println("Se agregó el elemento " + data + " a la cola.");
      }
   }

   public Paciente dequeue() {
      Paciente data;
      if (isEmpty()) {
         System.out.println("La cola está vacía. No se puede eliminar ningún elemento.");
         return null;
      } else {
         data = queue[front];
         if (front == rear) {
               front = rear = -1;
         } else {
               front = (front + 1) % maxSize;
         }
         System.out.println("Se eliminó el elemento " + data + " de la cola.");
         return data;
      }
   }

   
   private static void printQueue(Cola queue) {
      if (queue.isEmpty()) {
         System.out.println("La cola está vacía.");
      } else {
         System.out.println("La cola contiene los siguientes elementos:");
         if (queue.front <= queue.rear) {
               for (int i = queue.front; i <= queue.rear; i++) {
                  System.out.print(queue.queue[i]+", ");
               }
         } else {
               for (int i = queue.front; i < queue.maxSize; i++) {
                  System.out.print(queue.queue[i]+", ");
               }
               for (int i = 0; i <= queue.rear; i++) {
                  System.out.print(queue.queue[i]+", ");
               }
         }
         System.out.println();
      }
   }

   public Paciente[] getRealQueue(){
      Paciente[] realQueue = new Paciente[maxSize];
      if (front <= rear) {
            for (int i = front; i <= rear; i++) {
               if(front < 0 || rear < 0){
                  break;
               }
               realQueue[i] = queue[i];
            }
      } else {
            for (int i = front; i < maxSize; i++) {
               realQueue[i] = queue[i];
            }
            for (int i = 0; i <= rear; i++) {
               realQueue[i] = queue[i];
            }
      }
      return realQueue;
   }
}

public class SistemaDentista {

   public static void clearScreen(){
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        // Si estás en Windows, utiliza "cls" para limpiar la consola
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
          // En otros sistemas, utiliza "clear" para limpiar la consola
          Runtime.getRuntime().exec("clear");
      }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
   
   

   public static void main(String[] args) {
      boolean salir = false;
      int opcion; //Guardaremos la opcion del usuario
      Scanner sc = new Scanner(System.in);

      Cola cola = new Cola(2);

      JFrame ventana = new JFrame("Sistema de Dentista");
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Sistema de Dentista");
      JButton boton1 = new JButton("Agregar Paciente");
      JButton boton2 = new JButton("Atender Paciente");

      panel.setBackground(Color.decode("#69b4ff"));
      label.setFont(new Font("Poppins", Font.BOLD, 20));
      label.setForeground(Color.decode("#ffffff"));
      panel.setLayout(null);
      label.setBounds(200, 10, 500, 20);
      panel.add(label);

      boton1.setBounds(50, 500, 200, 30);
      boton1.setBackground(Color.decode("#ffffff"));
      boton1.setFont(new Font("Poppins", Font.PLAIN, 16));

      boton2.setBounds(350, 500, 200, 30);
      boton2.setBackground(Color.decode("#ffffff"));
      boton2.setFont(new Font("Poppins", Font.PLAIN, 16));

      panel.add(boton1);
      panel.add(boton2);

      
      

      JTextField[] textFields = new JTextField[9];
      for (int i = 0; i < textFields.length; i++) {
         textFields[i] = new JTextField();
         textFields[i].setEditable(false);
         textFields[i].setBackground(Color.decode("#ffffff"));
         textFields[i].setFont(new Font("Poppins", Font.PLAIN, 16));
         textFields[i].setBounds(50, 50 + (i * 40), 500, 30);
         panel.add(textFields[i]);
      }
      boton1.addActionListener(e -> {
         if (cola.isFull()) {
               JOptionPane.showMessageDialog(null, "La cola está llena. No se puede agregar más elementos.");
         } else {
               String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
               int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente"));
               String padecimiento = JOptionPane.showInputDialog("Ingrese el padecimiento del paciente");
               Paciente paciente = new Paciente(nombre, edad, padecimiento);
               cola.enqueue(paciente);
               JOptionPane.showMessageDialog(null, "Se agregó el paciente " + paciente + " a la cola.");
               updateData(cola, textFields);
         }
      });
      boton2.addActionListener(e -> {
         if (cola.isEmpty()) {
               JOptionPane.showMessageDialog(null, "La cola está vacía. No se puede atender ningún paciente.");
         } else {
               Paciente paciente = cola.dequeue();
               JOptionPane.showMessageDialog(null, "Se atendió el paciente " + paciente + " de la cola.");
               updateData(cola, textFields);
         }
      });
      ventana.setSize(600, 600);
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setLocationRelativeTo(null);
      ventana.setContentPane(panel);
      ventana.setVisible(true);

   }

   public static void updateData(Cola cola, JTextField[] textFields){
      for(int i = 0; i < textFields.length; i++){
         textFields[i].setText("");
      }
      int i = 0;
      for(Paciente paciente : cola.getRealQueue()){
         if(paciente != null){
               textFields[i].setText(paciente.getNombre());
               i++;
         }
      }
   }
}