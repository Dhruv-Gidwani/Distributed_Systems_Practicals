//Message Passing Interface MPI
#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
#include <unistd.h>
#include <math.h>
#define n 8
int a[]= {1,2,3,4,5,6,7,8};
int a2[100];

int main(int argc, char* argv[]){
	
	int pid, np, elements_per_process, n_element_received;
	
	MPI_Status status;
	MPI_Init(&argc,&argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &pid);
	MPI_Comm_size(MPI_COMM_WORLD, &np);
	
	if(pid==0)
	{
		int index, i;
		elements_per_process= ceil((float)n / np);
		
		for(i=1;i<np-1;i++)
		{
			index = i*elements_per_process;
			MPI_Send(&elements_per_process, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
			MPI_Send(&a[index], elements_per_process, MPI_INT, i, 0, MPI_COMM_WORLD);
			printf("Sending elements to the process %d\n", i);
		}
		
		//Elements sent to process n-1
		index= i*elements_per_process;
		int elements_left= n-index;
		MPI_Send(&elements_left, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
		MPI_Send(&a[index], elements_left, MPI_INT, i, 0, MPI_COMM_WORLD);
		printf("Sending remaining elements to the last process %d\n", i);
		
		//Master process (process number 0)calculating its sum
		int sum=0;
		for(i=0;i<elements_per_process;i++)
		{
			sum+=a[i];
		}
		printf("Partial sum of the master process: %d\n", sum);
		
		// Master process is collecting sums from other processes
		int temp;
		for(i=1; i<np;i++)
		{
			MPI_Recv(&temp, 1, MPI_INT, MPI_ANY_SOURCE, 0, MPI_COMM_WORLD, &status);
			printf("Partial sum for %d: %d\n", i, temp);
			sum+=temp;
		}
		printf("Final sum is %d\n", sum);
	}
	
	else
	{
		MPI_Recv(&n_element_received, 1, MPI_INT, 0,0, MPI_COMM_WORLD, &status);
		MPI_Recv(&a2, n_element_received, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		printf("Slave process %d has received the array \n", pid );
		
		//Partial sum claculating
		int partial_sum=0;
		for(int i=0;i<n_element_received;i++)
		{
			partial_sum+=a2[i];
		}
		
		MPI_Send(&partial_sum, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
		MPI_Finalize();	
	}	
}
