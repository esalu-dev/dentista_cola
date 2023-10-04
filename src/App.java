public class App {
    static int[] queue = new int[5];
    static int front = -1;
    static int rear = -1;
    public static void main(String[] args) {
        enqueue(5);
        enqueue(10);
        enqueue(15);
        enqueue(20);
        enqueue(25);
        enqueue(30);

        dequeue();
        dequeue();
        dequeue();
        dequeue();
        dequeue();
        dequeue();

        enqueue(2);
        enqueue(4);
        enqueue(6);
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


