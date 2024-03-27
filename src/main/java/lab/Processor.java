//package lab;
//
//public class Processor implements Runnable {
//
//    public static final long TICK_TIME = 100L;
//    private int tickCounter = 0;
//
//    @Override
//    public void run() {
//        while(true) {
//            try {
//                Thread.sleep(TICK_TIME);
//                tickCounter++;
//                System.out.println("tick #" + tickCounter);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
