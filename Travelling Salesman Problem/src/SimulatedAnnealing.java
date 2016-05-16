import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class SimulatedAnnealing extends SharedParameters
{
	private static final double INITIAL_TEMPERATURE = 10000.0;
	private static final double FINAL_TEMPERATURE = 0.1;
	private static final double ALPHA = 0.99;
	private static  int ITERATIONS_AT_TEMPERATURE = 100;
	
    private static final int REPEAT = 30;


	private static Solution currentSolution;
	private static Solution workingSolution;
	private static Solution bestSolution;
	    
	
	    
//	private static final double TARGET = 86.63;                // correct answer.
//	private static int XLocs[] = new int[] {30, 40, 40, 29, 19, 9, 9, 20};
//	private static int YLocs[] = new int[] {5, 10, 20, 25, 25, 19, 9, 5};
	    
//	    private static final int CITY_COUNT = 14;
//	    private static final double TARGET = 85.97;                // correct answer.
//	    private static int XLocs[] = new int[] {25, 30, 35, 39, 39, 35, 30, 25, 20, 15, 11, 11, 15, 20};
//	    private static int YLocs[] = new int[] {4, 5, 8, 15, 20, 27, 30, 31, 30, 27, 20, 15, 8, 5};
	    
	private static double XLocs[];
    private static double YLocs[];  
    double TARGET = 10628;
	
	
	public SimulatedAnnealing() 
	{
		currentSolution = new Solution();
		workingSolution = new Solution();
		bestSolution = new Solution();
		
		map = new ArrayList<City>();
		
		try {
			initializeLocations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
	}    

	
	private void initializeLocations() throws IOException
	{
		File f = new File("city.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		XLocs = new double[CITY_COUNT];
		YLocs = new double[CITY_COUNT];
		int i = 0;
		
		
		while((line = br.readLine()) != null)
		{
			StringTokenizer tokenizer = new StringTokenizer(line, " ");			
			
//			tokenizer.nextToken();
			
			XLocs[i] = Double.parseDouble(tokenizer.nextToken());
			YLocs[i] = Double.parseDouble(tokenizer.nextToken());
			
			i++;
			
		}

	}
	
	
	public void simulatedAnnealingAlgorithm()
	{
	   boolean useNew = false;
	   double temperature = INITIAL_TEMPERATURE;
    
	   initializeSolution();

	   currentSolution.computeCost();
       bestSolution.equate(currentSolution);
       workingSolution.equate(currentSolution);
       
       System.out.println("Best Solution Energy: " + bestSolution.getCost());

       try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       int repeatCounter = 0;
       
       while(repeatCounter < 1)
       {
    	   repeatCounter++;
    	   
		   while(temperature > FINAL_TEMPERATURE)
		   {
			  for(int i = 0; i < ITERATIONS_AT_TEMPERATURE; i++)
			  {
			  
			    useNew = false;
			    
			    workingSolution.randomChange();
			    workingSolution.computeCost();
			     
	
			    if(workingSolution.getCost() <= currentSolution.getCost())
			    {
			         useNew = true;
			        
			    }else
			     {
			         double randomTest = new Random().nextDouble(); //  Get random value between 0.0 and 1.0
			         double delta = workingSolution.getCost() - currentSolution.getCost();
			         double calc = Math.exp(-delta / temperature);
		    
			         if(calc > randomTest)
			             useNew = true;
			         
			     }
	
			     if(useNew)
			     {
			       useNew = false;
			       currentSolution.equate(workingSolution);
			           
			       if(currentSolution.getCost() < bestSolution.getCost())
			       {
			          bestSolution.equate(currentSolution);
			       }
			              
			     }else{
			          workingSolution.equate(currentSolution);
			     }
			     
			     System.out.println("Current Solution Energy: " + currentSolution.getCost());
	             System.out.println("Working Solution Energy: " + workingSolution.getCost());
	             System.out.println("Best Solution Energy: " + bestSolution.getCost());
	
			    }
			  
	//		        try {
	//					Thread.sleep(500);
	//				} catch (InterruptedException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
		            temperature *= ALPHA;
		            System.out.println("Temperature: " + temperature);
		            
//		            if(temperature < INITIAL_TEMPERATURE / 3)
//				    	  ITERATIONS_AT_TEMPERATURE = 500;
	        }
		   
		   temperature = INITIAL_TEMPERATURE;
       }
	        
	            for(int j = 0; j < CITY_COUNT; j++)
	            {
	                System.out.print(bestSolution.cities[j] + ", ");
	            }
	            System.out.print("\n");
	            if(bestSolution.getCost() <= TARGET){
	                System.out.println("Best solution is: Correct");
	            }else{
	                System.out.println("Best solution is: Not Correct");
	            }
	     
	    }
	    
	    
	    public void initializeMap()
	    {
	        City city = null;

	        for(int i = 0; i < CITY_COUNT; i++)
	        {
	            city = new City();
	            city.setXLoc(XLocs[i]);
	            city.setYLoc(YLocs[i]);
	            map.add(city);
	        }
	        return;
	    }
	    
	    private void initializeSolution()
	    {
	        // Initial setup of the solution.
	        for(int i = 0; i < CITY_COUNT; i++)
	        {
	            currentSolution.initializeCities(i, i);
	        }

	        // Randomly perturb the solution.
	        for(int i = 0; i < CITY_COUNT; i++)
	        {
	            currentSolution.randomChange();
	        }
	        return;
	    }
	 

	}


