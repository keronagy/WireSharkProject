/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkproject;

import static java.lang.Math.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Kord
 */
public class Kmeans {
    
    double [][] X; //m*n matrix
    double [][] u; //1*n Vector
    int m;
    int n;
    int K; //number of clusters
    public Kmeans(double X[][], int K) {
       this.X = X;
       n = X[0].length;
       m = X.length;
       this.K = K;
       u = new double[K][n];
       
       //randomaly initialize u
       for(int i=0; i<K; i++)
       {
          for(int j=0; j<n; j++)
            u[i][K] = ThreadLocalRandom.current().nextDouble(0, 1000000);
       }
        
    }
    
    
    double calcDist(double [] Xrow, double [] urow)
    {
        int dist=0;
        for(int i=0; i<Xrow.length; i++)
            dist += sqrt(pow(Xrow[i],2) - pow(urow[i],2));
       
        return dist;
    }
    
    void assignPointsToCentroid()
    {
        
        double min = 999999;
        for(int i=0; i<m; i++)
        {
            double dist =0;
            int clusterNumber=-1;
           for(int j=0; j<K; j++)
           {
                dist = calcDist(X[i],u[j]);
               if(dist <= min)
               {
                   min = dist;
                   clusterNumber = j;
               }
            }
        }
        
        
    }
    
    
    void start(int[]x, int[]u)
    {
        
        
    }
    
    
    
}
