import java.util.Scanner;

public class App {
    static int[] queue = new int[5];
    static int front = -1;
    static int rear = -1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Queues");
        while(!exit){
            System.out.println("------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Exit");            
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    System.out.println("Enter item to enqueue");
                    int item = Integer.parseInt(sc.nextLine());
                    enqueue(item);
                    break;
                case 2:
                    dequeue();
                    break;
                default:
                    exit = true;
                    break;
            }
        }
        sc.close();
        
    }
    public static void enqueue(int item) {
        if (rear == queue.length - 1) {
            System.out.println("Queue is full");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear++;
            queue[rear] = item;
            System.out.println("Inserted " + item);
            showStatus();
        }
    }
    public static int dequeue() {
        int item = -1;
        if (front == -1) {
            System.out.println("Queue is empty");
        } else {
            item = queue[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            System.out.println("Deleted " + item);
            showStatus();
        }
        return item;
    }
    public static void showStatus(){
        System.out.println("Front: " + front + " Rear: " + rear);
        System.out.println("Queue: " + java.util.Arrays.toString(queue));

    }
}


