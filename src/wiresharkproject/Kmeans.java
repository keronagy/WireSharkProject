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
    double [][] u; //K*n matrix
    int [] c; //closest centroid for each row in x
    int m;
    int n;
    int K; //number of clusters
    public Kmeans(double X[][], int K) {
       this.X = X;
       n = X[0].length;
       m = X.length;
       this.K = K;
       u = new double[K][n];
       c = new int [m];
       
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
           c[i] = clusterNumber;
        }
        
        
    }
    
    double [] getNearestCenter(int c)
    {
       return X[c];
        
    }
    
//    double sumVector(double [] vect)
//    {
//        double sum = 0;
//        
//        for(int i=0; i<vect.length; i++)
//            sum += vect[i];
//        
//        return sum;
//    }
    
    void moveCentroids()
    {
     
        //reset Centroid
        for(int i=0; i<K;i++)
            for(int j=0; j<n;j++)
                u[i][j] = 0;
        
        
        //move centroid
        for(int i=0; i<K; i++)
            {
             int clusterSize=0; //number of points that belongs to this cluster
             for(int j=0; j<m; j++)
                 if(c[j] == i) //point belongs to this cluster
                    {
                     clusterSize++;
                     //u  first contains the sum of all points which belong to it
                     for(int k =0; k<n;k++)
                         u[i][K] += X[i][K];
                         
                    }
             //divide each index with the number of points to find the mean
             for(int tmp=0; tmp<n;i++)
                 u[i][tmp] /= clusterSize;
            }

    }
        
    
    
    
    void start(int[]x, int[]u)
    {
        
        for(int i=0; i<100; i++) //random number of iterations, it should be modified
       {
        assignPointsToCentroid();
        moveCentroids();
       }
    }
    
    
    
}
