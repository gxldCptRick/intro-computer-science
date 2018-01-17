package interfaces;

import java.util.concurrent.Callable;

public interface Awesomeable extends AutoCloseable,  Callable<Integer>, Runnable{

}
