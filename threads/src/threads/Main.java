package threads;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		//Creating an arraylist of threads
		ArrayList<Runnable> thread_arr = new ArrayList<Runnable>();
		Matrix matrix = new Matrix();
		thread_arr.add(new Thread(matrix, "thread 1"));
		thread_arr.add(new Thread(matrix, "thread 2"));
		thread_arr.add(new Thread(matrix, "thread 3"));
		//Starting all the threads
		for(Runnable e : thread_arr) {
			((Thread) e).start();
		}	
	}
}

class Matrix implements  Runnable{
	private int row_size = 3;
	private int column_size = 3;
	private int current_row = 0; 
	int[][] matrix = new int[column_size][row_size];
	int[] vector = new int[column_size];
	int[] vector_res = new int[column_size];
	
	//Constructor creates and outputs the matrice and vector
	Matrix(){
		System.out.println("Matrice with "+row_size +" rows and "+column_size+" columns");
		create();
		output();
	}
	
	//Creates a 3x3 matrice and a vector, fills the final vector with 0s
	public void create() {
		System.out.println("Creating the matrice.");
		for(int i = 0; i<column_size; i++) {
			for(int j = 0; j<row_size; j++) {
				matrix[i][j] = (int)(Math.random()*11);
			}
			vector[i] = (int)(Math.random()*11);
			vector_res[i] = 0;
		}
	}
	//Outputs the matrice and the vector
	public void output() {
		System.out.println("Matrice: ");
		for(int i = 0; i<column_size; i++) {
			for(int j = 0; j<row_size; j++) {
				System.out.print(matrix[i][j]+" ");
			}
		System.out.println();
		}
		System.out.println("vector: ");
		for(int i = 0; i<column_size;i++) {
			System.out.println(vector[i]);
		}
	}
	
	//Multiplies the matrice by the vector and fills results into vector_res
	 public void multiply() {	
		for(int j = 0; j < row_size;j++) {
			vector_res[current_row] += vector[j]*matrix[current_row][j];
			}
		System.out.println(vector_res[current_row]);
		current_row++; //Each thread takes a different row
	}
	 @Override
	public void run() {
		System.out.println("Running "+Thread.currentThread().getName());	
		try{multiply(); //Each thread mutiplies matrice by vector
		Thread.sleep(10); //The thread waits for 10 secs
		}catch(InterruptedException ie) {}	
	}
}
