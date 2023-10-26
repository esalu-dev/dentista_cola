public class App {
    private int maxSize;
    private int front;
    private int rear;
    private int[] queue;

    public App(int size) {
        maxSize = size;
        front = -1;
        rear = -1;
        queue = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (front == 0 && rear == maxSize - 1) || (front == rear + 1);
    }

    public void enqueue(int data) {
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

    public int dequeue() {
        int data;
        if (isEmpty()) {
            System.out.println("La cola está vacía. No se puede eliminar ningún elemento.");
            return -1;
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

    
    private static void printQueue(App queue) {
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
    public static void main(String[] args) {
        App queue = new App(5);

        queue.enqueue(1);
        printQueue(queue);
        queue.enqueue(2);
        printQueue(queue);
        queue.enqueue(3); 
        printQueue(queue);
        queue.enqueue(4);
        printQueue(queue);
        queue.enqueue(5);
        printQueue(queue);

        System.out.println(queue.dequeue());
        printQueue(queue);
        System.out.println(queue.dequeue());
        printQueue(queue);
        System.out.println(queue.dequeue());
        printQueue(queue);
        System.out.println(queue.dequeue());
        printQueue(queue);

        queue.enqueue(6);
        printQueue(queue);
        queue.enqueue(7);
        printQueue(queue);
        queue.enqueue(8);
        printQueue(queue);
        queue.enqueue(9);
        printQueue(queue);
        queue.enqueue(10);
        printQueue(queue);

        System.out.println(queue.dequeue());
        printQueue(queue);
        System.out.println(queue.dequeue());
        printQueue(queue);
        System.out.println(queue.dequeue());
        printQueue(queue);
        System.out.println(queue.dequeue());
        printQueue(queue);
    }
}
