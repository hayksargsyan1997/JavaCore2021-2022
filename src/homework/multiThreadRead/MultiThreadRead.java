package homework.multiThreadRead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadRead implements Runnable {
    public static String path = "C:\\Users\\Hayk\\IdeaProjects\\JavaCore\\src\\homework\\sample.txt";
    public static AtomicInteger countOfKeyword = new AtomicInteger();
    List<String> string;
    Thread t;

    MultiThreadRead(List<String> string) {
        this.string = string;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        String keyword = "barev";
        for (String strings : string) {
            if (strings.contains(keyword)) {
                countOfKeyword.incrementAndGet();

            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        long count = Files.lines(Paths.get(path)).count();
        System.out.println("all lines:" + count);
        long currentTime = System.currentTimeMillis();

        List<String> string = Files.readAllLines(Paths.get(path));
        List<String> list1 = string.subList(0, 499999);
        List<String> list2 = string.subList(500000, 999999);
        List<String> list3 = string.subList(1000000, 1499999);
        List<String> list4 = string.subList(1500000, 1999999);
        List<String> list5 = string.subList(2000000, 3448632);

        MultiThreadRead multiThreadRead = new MultiThreadRead(list1);
        MultiThreadRead multiThreadRead1 = new MultiThreadRead(list2);
        MultiThreadRead multiThreadRead2 = new MultiThreadRead(list3);
        MultiThreadRead multiThreadRead3 = new MultiThreadRead(list4);
        MultiThreadRead multiThreadRead4 = new MultiThreadRead(list5);

        multiThreadRead.t.join();
        multiThreadRead1.t.join();
        multiThreadRead2.t.join();
        multiThreadRead3.t.join();
        multiThreadRead4.t.join();


        System.out.println("found lines :" + countOfKeyword);

        long finishTime = System.currentTimeMillis();
        System.out.println("time: " + (finishTime - currentTime));
    }


}