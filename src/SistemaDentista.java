import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

   
   private void printQueue() {
      if (this.isEmpty()) {
         System.out.println("La cola está vacía.");
      } else {
         System.out.println("La cola contiene los siguientes elementos:");
         if (this.front <= this.rear) {
               for (int i = this.front; i <= this.rear; i++) {
                  System.out.print(this.queue[i]+", ");
               }
         } else {
               for (int i = this.front; i < this.maxSize; i++) {
                  System.out.print(this.queue[i]+", ");
               }
               for (int i = 0; i <= this.rear; i++) {
                  System.out.print(this.queue[i]+", ");
               }
         }
         System.out.println();
      }
   }

   public Paciente[] getRealQueue(){
      Paciente[] realQueue = new Paciente[this.maxSize];
      if (this.front <= this.rear) {
            for (int i = this.front; i <= this.rear; i++) {
               if(this.front < 0 || this.rear < 0){
                  break;
               }
               realQueue[i] = this.queue[i];
            }
      } else {
            for (int i = this.front; i < this.maxSize; i++) {
               realQueue[i] = this.queue[i];
            }
            for (int i = 0; i <= this.rear; i++) {
               realQueue[i] = this.queue[i];
            }
      }
      for(int i = 0; i < realQueue.length; i++){
         System.out.println(realQueue[i]);
      }
      return realQueue;
   }
}

class Boton extends JButton{
      Paciente paciente;
      public Boton(){
         super();
      }
      public void setPaciente(Paciente paciente){
         this.paciente = paciente;
      }
   }

public class SistemaDentista {
   public static void main(String[] args) {

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

      
      ActionListener actionListener = e -> {
         if(e.getSource() instanceof Boton){
            Boton boton = (Boton) e.getSource();
            if(boton.paciente == null){
               JOptionPane.showMessageDialog(null, "No hay paciente en este botón.", "Información del paciente", JOptionPane.INFORMATION_MESSAGE);
               return;
            }
            JOptionPane.showMessageDialog(null, "Nombre: " + boton.paciente.getNombre() + "\nEdad: " + boton.paciente.getEdad() + "\nPadecimiento: " + boton.paciente.getPadecimiento(), "Información del paciente", JOptionPane.INFORMATION_MESSAGE);
         }
      };

      Boton[] pacientesButton = new Boton[9];
      for (int i = 0; i < pacientesButton.length; i++) {
         pacientesButton[i] = new Boton();
         // pacientes[i].setEditable(false);
         pacientesButton[i].setBackground(Color.decode("#ffffff"));
         pacientesButton[i].setFont(new Font("Poppins", Font.PLAIN, 16));
         pacientesButton[i].setBounds(50, 50 + (i * 40), 500, 30);
         pacientesButton[i].addActionListener(actionListener);
         panel.add(pacientesButton[i]);
      }
      boton1.addActionListener(e -> {
         if (cola.isFull()) {
               JOptionPane.showMessageDialog(null, "La cola está llena. No se puede agregar más elementos.");
         } else {
               String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
               if(nombre == null || nombre.equals("")){
                  JOptionPane.showMessageDialog(null, "No se ingresó ningún nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                  return;
               }
               int edad;
               try{
                  edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente"));
               } catch (NumberFormatException exception){
                  JOptionPane.showMessageDialog(null, "No se ingresó una edad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                  return;
               }
               String padecimiento = JOptionPane.showInputDialog("Ingrese el padecimiento del paciente");
               if(padecimiento == null || padecimiento.equals("")){
                  JOptionPane.showMessageDialog(null, "No se ingresó ningún padecimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                  return;
               }
               Paciente paciente = new Paciente(nombre, edad, padecimiento);
               cola.enqueue(paciente);
               JOptionPane.showMessageDialog(null, "Se agregó el paciente " + paciente.nombre + " a la cola.");
               updateData(cola, pacientesButton);
         }
      });
      boton2.addActionListener(e -> {
         if (cola.isEmpty()) {
               JOptionPane.showMessageDialog(null, "La cola está vacía. No se puede atender ningún paciente.");
         } else {
               Paciente paciente = cola.dequeue();
               JOptionPane.showMessageDialog(null, "Se atendió el paciente " + paciente + " de la cola.");
               updateData(cola, pacientesButton);
         }
      });
      ventana.setSize(600, 600);
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setLocationRelativeTo(null);
      ventana.setContentPane(panel);
      ventana.setVisible(true);

   }

   public static void updateData(Cola cola, Boton[] pacientes){
      for(int i = 0; i < pacientes.length; i++){
         pacientes[i].setText("");
         pacientes[i].setPaciente(null);
      }
      int i = 0;
      for(Paciente paciente : cola.getRealQueue()){
         if(paciente != null){
               pacientes[i].setText(paciente.getNombre());
               pacientes[i].setPaciente(paciente);
               i++;
         }
      }
   }
}