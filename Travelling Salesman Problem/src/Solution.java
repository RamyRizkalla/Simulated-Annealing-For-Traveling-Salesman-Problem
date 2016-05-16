import java.util.Random;

public class Solution extends SharedParameters
{
	private double cost = 0;
    public int cities[];
    
    
    public Solution()
    {
    	cities = new int[CITY_COUNT];
    }

        
    public void equate(Solution solution)
    {
       for(int i = 0; i < CITY_COUNT; i++)
       {
          this.cities[i] = solution.cities[i];
       }
           
       this.cost = solution.cost;

    }
        
   
    public void initializeCities(int index, int value)
    {
       cities[index] = value;

    }

        
    public double getCost()
    {
       return cost;

    }

        
    public void randomChange()
    {
       int temp = 0;
       int x1 = 0;
       int x2 = 0;

       // Get two different random numbers.
       x1 = new Random().nextInt(CITY_COUNT);
       x2 = getExclusiveRandomNumber(x1);
       
//       System.out.println("x1: " + x1 + ", x2: " + x2);
//       try {
//		Thread.sleep(2000);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
            
       temp = cities[x1];
       cities[x1] = cities[x2];
       cities[x2] = temp;

    }
    
    private static int getExclusiveRandomNumber(int except)
    {
       boolean done = false;
       int getRand = 0;

       while(!done)
       {
          getRand = new Random().nextInt(CITY_COUNT);
           
          if(getRand != except)
                done = true;
            
        }

        return getRand;
    }
        
      
    public void computeCost()
    {
       cost= 0;
        
       // Find the round-trip distance. 
       for(int i = 0; i < CITY_COUNT; i++)
       {
    	   
//          if(i == CITY_COUNT - 1)	// Difference between last city and first city.
//          {
//             cost += getDistance(cities[CITY_COUNT - 1], cities[0]); 
//             
//          }else
//          {
             cost += getDistance(cities[i], cities[ (i + 1) % CITY_COUNT ]);
//          }
       }

    }
        
    
    private static double getDistance(int firstCity, int secondCity)
    {
        City city1 = null;
        City city2 = null;
        double A2 = 0;
        double B2 = 0;
        
        city1 = map.get(firstCity);
        city2 = map.get(secondCity);
        
        A2 = Math.pow(Math.abs(city1.x() - city2.x()), 2);
        B2 = Math.pow(Math.abs(city1.y() - city2.y()), 2);

        return Math.sqrt(A2 + B2);
    }
            

}
